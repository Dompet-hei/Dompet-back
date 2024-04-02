CREATE OR REPLACE VIEW vw_account_statement AS
WITH transactions AS (
    SELECT
        a.account_id,
        a.account_number,
        t.effective_date,
        oc.name AS category,
        CASE
            WHEN oc.operation_type_id = 1 THEN t.amount
            ELSE 0
            END AS credit_amount,
        CASE
            WHEN oc.operation_type_id = 2 THEN t.amount
            ELSE 0
            END AS debit_amount,
        t.description,
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
            ) THEN tr.amount / (
                SELECT COUNT(*)
                FROM transfer_recipient
                WHERE transfer_id = tr.transfer_id
            )
            ELSE 0
            END AS credit_amount,
        CASE
            WHEN a.account_id = tr.sender_account_id THEN tr.amount
            ELSE 0
            END AS debit_amount,
        tr.description,
        'Transfer' AS operation_type
    FROM
        account a
            JOIN transfer tr ON a.account_id = tr.sender_account_id OR a.account_id IN (
            SELECT recipient_account_id
            FROM transfer_recipient
            WHERE transfer_id = tr.transfer_id
        )
)
SELECT
    account_id,
    account_number,
    effective_date,
    category,
    credit_amount,
    debit_amount,
    description AS reason,
    operation_type,
    (SELECT balance
     FROM balance ab
     WHERE ab.account_id = vw_account_statement.account_id
       AND ab.last_updated <= vw_account_statement.effective_date
     ORDER BY ab.last_updated DESC
     LIMIT 1) AS balance
FROM
    (SELECT * FROM transactions
     UNION ALL
     SELECT * FROM transfers) AS vw_account_statement
ORDER BY
    account_id, effective_date DESC;