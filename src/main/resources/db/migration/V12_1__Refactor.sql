TRUNCATE TABLE client, operation_category, balance, bank, account, transaction, transfer, transfer_recipient, statement,
    overdraft RESTART IDENTITY;

DROP TRIGGER IF EXISTS update_balance_after_transaction ON transaction;
DROP TRIGGER IF EXISTS update_balance_after_transfer_insert ON transfer;
DROP TRIGGER IF EXISTS update_balance_after_transfer_update ON transfer;

DROP TABLE IF EXISTS "operation_type" CASCADE;

DROP VIEW IF EXISTS vw_account_statement, account_balances, account_view ;

ALTER TABLE "operation_category"
    DROP COLUMN operation_type_id,
    ADD COLUMN operation_type VARCHAR(50) NOT NULL CHECK (operation_type IN ('income','INCOME', 'expense', 'EXPENSE'));

ALTER TABLE "account"
    ADD COLUMN interest_day1to7 FLOAT DEFAULT 0,
    ADD COLUMN interest_after7 FLOAT DEFAULT 0,
    ADD CONSTRAINT check_interest
    CHECK (interest_day1to7 < interest_after7);

ALTER TABLE "transfer"
    DROP COLUMN amount;

ALTER TABLE "transfer_recipient"
    ADD COLUMN "amount" DECIMAL(15, 3) DEFAULT 0,
    ADD CONSTRAINT check_positive_amount CHECK (amount >= 0);

DROP TABLE IF EXISTS "statement";

ALTER TABLE "overdraft"
    DROP COLUMN overdraft_interest_day1to7,
    DROP COLUMN overdraft_interest_after7,
    ADD CONSTRAINT check_positive_balance CHECK (overdraft_balance >= 0);

CREATE OR REPLACE VIEW account_view AS
SELECT
    a.account_id,
    a.account_number,
    a.creation_date,
    a.monthly_net_salary,
    a.default_currency_code,
    a.is_active,
    a.interest_day1to7,
    a.interest_after7,
    c.client_id,
    c.last_name,
    c.first_name,
    c.birth_date,
    b.bank_id,
    b.name AS bank_name
FROM
    account a
        JOIN
    client c ON a.client_id = c.client_id
        JOIN
    bank b ON a.bank_id = b.bank_id;

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