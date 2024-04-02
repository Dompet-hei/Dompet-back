CREATE OR REPLACE FUNCTION update_account_balance()
    RETURNS TRIGGER AS
$$
BEGIN
    -- Update balance for transactions
    IF TG_OP = 'INSERT' AND TG_TABLE_NAME = 'transaction' THEN
        UPDATE balance
        SET balance = balance + CASE
                                    WHEN (SELECT operation_type_id FROM operation_category WHERE category_id = NEW.category_id) = 1 THEN NEW.amount
                                    ELSE -NEW.amount
            END,
            last_updated = CURRENT_TIMESTAMP
        WHERE account_id = NEW.account_id;

        -- Update balance for scheduled transfers
    ELSIF TG_OP = 'INSERT' AND TG_TABLE_NAME = 'transfer' AND NEW.status_id = 1 THEN
        -- Update balance for the sender account
        UPDATE balance
        SET balance = balance - NEW.amount,
            last_updated = CURRENT_TIMESTAMP
        WHERE account_id = NEW.sender_account_id;

        -- Update balance for recipient accounts
        WITH recipients AS (
            SELECT recipient_account_id
            FROM transfer_recipient
            WHERE transfer_id = NEW.transfer_id
        )
        UPDATE balance ab
        SET balance = balance + (NEW.amount / (SELECT COUNT(*) FROM recipients)),
            last_updated = CURRENT_TIMESTAMP
        FROM recipients
        WHERE ab.account_id = recipients.recipient_account_id;

        -- Update transfer status from scheduled to executed
    ELSIF TG_OP = 'UPDATE' AND TG_TABLE_NAME = 'transfer' AND NEW.status_id = 2 THEN
        -- Update balance for the sender account
        UPDATE balance
        SET balance = balance - NEW.amount,
            last_updated = CURRENT_TIMESTAMP
        WHERE account_id = NEW.sender_account_id;

        -- Update balance for recipient accounts
        WITH recipients AS (
            SELECT recipient_account_id
            FROM transfer_recipient
            WHERE transfer_id = NEW.transfer_id
        )
        UPDATE balance ab
        SET balance = balance + (NEW.amount / (SELECT COUNT(*) FROM recipients)),
            last_updated = CURRENT_TIMESTAMP
        FROM recipients
        WHERE ab.account_id = recipients.recipient_account_id;

    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_balance_after_transaction
    AFTER INSERT ON transaction
    FOR EACH ROW
EXECUTE PROCEDURE update_account_balance();

CREATE TRIGGER update_balance_after_transfer_insert
    AFTER INSERT ON transfer
    FOR EACH ROW
    WHEN (NEW.status_id = 1)
EXECUTE PROCEDURE update_account_balance();

CREATE TRIGGER update_balance_after_transfer_update
    AFTER UPDATE ON transfer
    FOR EACH ROW
    WHEN (NEW.status_id = 2)
EXECUTE PROCEDURE update_account_balance();