CREATE OR REPLACE VIEW transfer_details AS
SELECT
    t.transfer_id,
    t.unique_reference,
    t.scheduled_effective_date,
    s.name AS transfer_status,
    t.description,
    t.effective_date,
    tr.amount,
    a.account_id AS sender_account_id,
    ar.account_id AS recipient_account_id,
    a.account_number AS sender_account,
    ar.account_number AS recipient_account
FROM transfer t
         INNER JOIN transfer_recipient tr ON t.transfer_id = tr.transfer_id
         INNER JOIN account a ON t.sender_account_id = a.account_id
         INNER JOIN account ar ON tr.recipient_account_id = ar.account_id
         LEFT JOIN status s ON t.status_id = s.status_id;