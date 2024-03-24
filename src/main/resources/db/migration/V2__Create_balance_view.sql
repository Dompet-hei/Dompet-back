CREATE OR REPLACE VIEW account_balances AS
SELECT
    a.account_id,
    a.account_number,
    COALESCE(
                SUM(CASE
                        WHEN oc.operation_type_id = (SELECT operation_type_id FROM operation_type WHERE name = 'income') THEN t.amount
                        ELSE -t.amount
                    END) +
                SUM(CASE
                        WHEN tr.sender_account_id = a.account_id THEN -tr.amount
                        ELSE 0
                    END) +
                SUM(CASE
                        WHEN tr.transfer_id IN (
                            SELECT transfer_id
                            FROM transfer_recipient
                            WHERE recipient_account_id = a.account_id
                        ) THEN tr.amount
                        ELSE 0
                    END),
                0) AS balance
FROM
    account a
        LEFT JOIN transaction t ON a.account_id = t.account_id
        LEFT JOIN operation_category oc ON t.category_id = oc.category_id
        LEFT JOIN transfer tr ON a.account_id = tr.sender_account_id OR a.account_id IN (
        SELECT recipient_account_id
        FROM transfer_recipient
        WHERE transfer_id = tr.transfer_id
    )
GROUP BY
    a.account_id, a.account_number;