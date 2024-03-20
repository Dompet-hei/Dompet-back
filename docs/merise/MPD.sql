--@conn
CREATE TABLE "client" (
    "client_id" VARCHAR(50) PRIMARY KEY,
    "last_name" VARCHAR(100) NOT NULL,
    "first_name" VARCHAR(100) NOT NULL,
    "birth_date" DATE NOT NULL,
    CONSTRAINT "check_client_age" CHECK (
        EXTRACT(
            YEAR
            FROM AGE(CURRENT_DATE, "birth_date")
        ) >= 21
    )
);
--@conn
CREATE TABLE "operation_type" (
    "operation_type_id" SERIAL PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL UNIQUE
);
INSERT INTO "operation_type" ("name")
VALUES ('income'),
    ('expense');
--@conn
CREATE TABLE "status" (
    "status_id" SERIAL PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL UNIQUE
);
INSERT INTO "status" ("name")
VALUES ('scheduled'),
    ('executed'),
    ('cancelled');
--@conn
CREATE TABLE "operation_category" (
    "category_id" VARCHAR(50) PRIMARY KEY,
    "name" VARCHAR(50) NOT NULL,
    "operation_type_id" INT NOT NULL,
    FOREIGN KEY ("operation_type_id") REFERENCES "operation_type"("operation_type_id")
);
--@conn
CREATE TABLE "bank" (
    "bank_id" VARCHAR(50) PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL
);
--@conn
CREATE TABLE "account" (
    "account_id" VARCHAR(50) PRIMARY KEY,
    "account_number" BIGINT UNIQUE NOT NULL,
    "creation_date" DATE DEFAULT CURRENT_DATE,
    "monthly_net_salary" DECIMAL(15, 3) DEFAULT 0,
    "default_currency_code" VARCHAR(3),
    "is_active" BOOLEAN DEFAULT TRUE,
    "client_id" VARCHAR(50) NOT NULL,
    "bank_id" VARCHAR(50) NOT NULL,
    FOREIGN KEY ("client_id") REFERENCES "client"("client_id"),
    FOREIGN KEY ("bank_id") REFERENCES "bank"("bank_id")
);
--@conn
CREATE TABLE "transaction" (
    "transaction_id" VARCHAR(50) PRIMARY KEY,
    "account_id" VARCHAR(50) NOT NULL,
    "category_id" VARCHAR(50) NOT NULL,
    "effective_date" TIMESTAMP NOT NULL,
    "record_date" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "amount" DECIMAL(15, 3) NOT NULL,
    "description" TEXT,
    FOREIGN KEY ("account_id") REFERENCES "account"("account_id"),
    FOREIGN KEY ("category_id") REFERENCES "operation_category"("category_id")
);
--@conn
CREATE TABLE "transfer" (
    "transfer_id" VARCHAR(50) PRIMARY KEY,
    "unique_reference" VARCHAR(20) UNIQUE NOT NULL,
    "scheduled_effective_date" DATE NOT NULL,
    "status_id" INT DEFAULT 1,    -- Default status_id set to "scheduled"
    "amount" DECIMAL(15, 3) NOT NULL,
    "description" TEXT,
    "effective_date" TIMESTAMP,
    "record_date" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "sender_account_id" VARCHAR(50) NOT NULL,
    "is_internal" BOOLEAN DEFAULT TRUE, -- Flag to indicate if the transfer is internal or external
    FOREIGN KEY ("sender_account_id") REFERENCES "account"("account_id"),
    FOREIGN KEY ("status_id") REFERENCES "status"("status_id"),
    CONSTRAINT "check_amount_positive" CHECK ("amount" > 0),
    CONSTRAINT "check_effective_date" CHECK (
        CASE
            WHEN "is_internal" = TRUE THEN "effective_date" IS NOT NULL
            ELSE "effective_date" IS NULL
        END
    )
);
--@conn
CREATE TABLE "transfer_recipient" (
    "transfer_id" VARCHAR(50) NOT NULL,
    "recipient_account_id" VARCHAR(50) NOT NULL,
    PRIMARY KEY ("transfer_id", "recipient_account_id"),
    FOREIGN KEY ("transfer_id") REFERENCES "transfer"("transfer_id"),
    FOREIGN KEY ("recipient_account_id") REFERENCES "account"("account_id")
);
--@conn
CREATE TABLE "statement" (
    "statement_id" VARCHAR(50) PRIMARY KEY,
    "account_id" VARCHAR(50) NOT NULL,
    "start_period" DATE NOT NULL,
    "end_period" DATE NOT NULL,
    FOREIGN KEY ("account_id") REFERENCES "account"("account_id")
);
--@conn
CREATE TABLE "overdraft" (
    "overdraft_id" VARCHAR(50) PRIMARY KEY,
    "account_id" VARCHAR(50) UNIQUE,
    "overdraft_allowed" BOOLEAN DEFAULT FALSE,
    "overdraft_balance" DECIMAL(15, 3) DEFAULT 0,
    "overdraft_start_date" DATE DEFAULT CURRENT_DATE,
    "overdraft_reimbursement_date" DATE,
    "overdraft_interest_day1to7" FLOAT,
    "overdraft_interest_after7" FLOAT,
    FOREIGN KEY ("account_id") REFERENCES "account"("account_id")
);