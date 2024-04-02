CREATE OR REPLACE VIEW vw_account_statement AS
WITH transactions AS (
    SELECT
        a.account_id,
        a.account_number,
        t.effective_date,
        oc.name AS category,
        CASE
            WHEN oc.operation_type = 'income' THEN t.amount
            ELSE 0
            END AS credit_amount,
        CASE
            WHEN oc.operation_type = 'expense' THEN t.amount
            ELSE 0
            END AS debit_amount,
        t.description AS reason,
        'Transaction' AS operation_type
    FROM
        account a
            JOIN transaction t ON a.account_id = t.account_id
            JOIN operation_category oc ON t.category_id = oc.category_id
), transfers AS (
    SELECT
        a.account_id,
        a.account_number,
        tr.effective_date,
        'Transfer' AS category,
        CASE
            WHEN a.account_id IN (
                SELECT recipient_account_id
                FROM transfer_recipient
                WHERE transfer_id = tr.transfer_id
            ) THEN
                (SELECT SUM(amount)
                 FROM transfer_recipient
                 WHERE transfer_id = tr.transfer_id
                   AND recipient_account_id = a.account_id)
            ELSE 0
            END AS credit_amount,
        CASE
            WHEN a.account_id = tr.sender_account_id THEN
                (SELECT SUM(amount) FROM transfer_recipient WHERE transfer_id = tr.transfer_id)
            ELSE 0
            END AS debit_amount,
        CASE
            WHEN tr.status_id = 1 THEN tr.description || ' (Scheduled)'
            WHEN tr.status_id = 2 THEN tr.description || ' (Executed)'
            WHEN tr.status_id = 3 THEN tr.description || ' (Cancelled)'
            ELSE tr.description
            END AS reason,
        'Transfer' AS operation_type
    FROM
        account a
            JOIN transfer tr ON a.account_id = tr.sender_account_id OR a.account_id IN (
            SELECT recipient_account_id
            FROM transfer_recipient
            WHERE transfer_id = tr.transfer_id
        )
), balance_subquery AS (
    SELECT
        account_id,
        balance,
        last_updated,
        ROW_NUMBER() OVER (PARTITION BY account_id, DATE_TRUNC('day', last_updated) ORDER BY last_updated DESC) AS rn
    FROM
        balance
)
SELECT
    vw_account_statement.account_id,
    vw_account_statement.account_number,
    vw_account_statement.effective_date,
    vw_account_statement.category,
    vw_account_statement.credit_amount,
    vw_account_statement.debit_amount,
    vw_account_statement.reason,
    vw_account_statement.operation_type,
    COALESCE((
                 SELECT balance
                 FROM balance_subquery
                 WHERE account_id = vw_account_statement.account_id
                   AND last_updated <= vw_account_statement.effective_date
                   AND rn = 1
             ), 0) AS balance
FROM
    (SELECT * FROM transactions
     UNION ALL
     SELECT * FROM transfers) AS vw_account_statement
ORDER BY
    account_id, effective_date DESC;