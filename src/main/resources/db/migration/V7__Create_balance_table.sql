CREATE TABLE IF NOT EXISTS "balance" (
    ab_id SERIAL PRIMARY KEY,
    account_id VARCHAR(50) NOT NULL,
    balance DECIMAL(15, 3) NOT NULL DEFAULT 0,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account(account_id)
);