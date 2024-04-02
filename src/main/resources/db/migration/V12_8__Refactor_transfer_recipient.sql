TRUNCATE TABLE "transfer_recipient" RESTART IDENTITY;
DROP TABLE IF EXISTS "transfer_recipient" CASCADE;
CREATE TABLE IF NOT EXISTS "transfer_recipient" (
    "transfer_recipient_id" BIGSERIAL PRIMARY KEY,
    "transfer_id" VARCHAR(50) NOT NULL,
    "recipient_account_id" VARCHAR(50) NOT NULL,
    FOREIGN KEY ("transfer_id") REFERENCES "transfer"("transfer_id"),
    FOREIGN KEY ("recipient_account_id") REFERENCES "account"("account_id")
);

ALTER TABLE "transfer_recipient"
    ADD COLUMN "amount" DECIMAL(15, 3) DEFAULT 0,
    ADD CONSTRAINT check_positive_amount CHECK (amount >= 0);

INSERT INTO transfer_recipient (transfer_id, recipient_account_id, amount)
VALUES
    ('transfer1', 'account2', 1000.00),
    ('transfer2', 'account3', 250.00),
    ('transfer2', 'account4', 250.00),
    ('transfer4', 'account1', 250.00),
    ('transfer4', 'account2', 250.00),
    ('transfer4', 'account3', 250.00),
    ('transfer5', 'account1', 250.00),
    ('transfer3', 'account6', 1200.00);