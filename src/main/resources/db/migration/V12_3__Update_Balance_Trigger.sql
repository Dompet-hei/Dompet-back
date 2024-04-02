CREATE OR REPLACE FUNCTION update_balance_after_transaction()
    RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT operation_type FROM operation_category WHERE category_id = NEW.category_id) = 'income' THEN
        UPDATE balance
        SET balance = balance + NEW.amount
        WHERE account_id = NEW.account_id;
    ELSIF (SELECT operation_type FROM operation_category WHERE category_id = NEW.category_id) = 'expense' THEN
        UPDATE balance
        SET balance = balance - NEW.amount
        WHERE account_id = NEW.account_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_balance_after_transaction
    AFTER INSERT OR UPDATE ON transaction
    FOR EACH ROW
EXECUTE FUNCTION update_balance_after_transaction();

CREATE OR REPLACE FUNCTION update_balance_after_transfer_insert()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.status_id = 2 THEN -- Assuming 2 is the status_id for 'executed'
        UPDATE balance
        SET balance = balance - NEW.amount
        WHERE account_id = NEW.sender_account_id;

        UPDATE balance
        SET balance = balance + NEW.amount
        WHERE account_id = (SELECT recipient_account_id FROM transfer_recipient WHERE transfer_id = NEW.transfer_id);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_balance_after_transfer_insert
    AFTER INSERT ON transfer
    FOR EACH ROW
EXECUTE FUNCTION update_balance_after_transfer_insert();

CREATE OR REPLACE FUNCTION update_balance_after_transfer_update()
    RETURNS TRIGGER AS $$
BEGIN
    IF NEW.status_id = 2 AND OLD.status_id != 2 THEN -- Transition from scheduled to executed
        UPDATE balance
        SET balance = balance - NEW.amount
        WHERE account_id = NEW.sender_account_id;

        UPDATE balance
        SET balance = balance + NEW.amount
        WHERE account_id = (SELECT recipient_account_id FROM transfer_recipient WHERE transfer_id = NEW.transfer_id);
    ELSIF NEW.status_id = 3 AND OLD.status_id != 3 THEN -- Transition to cancelled
    -- Refund the sender account
        UPDATE balance
        SET balance = balance + NEW.amount
        WHERE account_id = NEW.sender_account_id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_balance_after_transfer_update
    AFTER UPDATE ON transfer
    FOR EACH ROW
EXECUTE FUNCTION update_balance_after_transfer_update();

CREATE OR REPLACE FUNCTION update_scheduled_transfers_to_executed()
    RETURNS VOID AS $$
BEGIN
    UPDATE transfer
    SET status_id = 2 -- status_id for 'executed'
    WHERE status_id = 1 -- status_id for 'scheduled'
      AND scheduled_effective_date <= CURRENT_DATE;
END;
$$ LANGUAGE plpgsql;