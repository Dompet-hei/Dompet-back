CREATE OR REPLACE FUNCTION set_initial_balance()
    RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO balance (account_id, balance)
    VALUES (NEW.account_id, 0);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_initial_balance
    AFTER INSERT ON account
    FOR EACH ROW
EXECUTE FUNCTION set_initial_balance();