INSERT INTO client (client_id, last_name, first_name, birth_date)
VALUES
    ('client1', 'Smith', 'John', '1990-05-15'),
    ('client2', 'Johnson', 'Emily', '1985-12-02'),
    ('client3', 'Williams', 'Michael', '1978-09-20'),
    ('client4', 'Brown', 'Sarah', '1992-03-10'),
    ('client5', 'Davis', 'David', '1988-07-25');

INSERT INTO operation_category (category_id, name, operation_type)
VALUES
    ('category1', 'Salary', 'income'),
    ('category2', 'Groceries', 'expense'),
    ('category3', 'Investment', 'income'),
    ('category4', 'Dining', 'expense'),
    ('category5', 'Gift', 'income');

INSERT INTO "bank" ("bank_id", "name") VALUES
    ('bank1', 'Bank of Madagascar'),
    ('bank2', 'BMOI'),
    ('bank3', 'BFV');

INSERT INTO account (account_id, account_number, monthly_net_salary, default_currency_code, client_id, bank_id,
                     interest_day1to7, interest_after7, creation_date)
VALUES
    ('account1', 1234567890, 2500.00, 'USD', 'client1', 'bank1', 0.01, 0.02, CURRENT_TIMESTAMP - INTERVAL '5 days'),
    ('account2', 9876543210, 3800.75, 'EUR', 'client2', 'bank2', 0.015, 0.025, CURRENT_TIMESTAMP - INTERVAL '5 days'),
    ('account3', 5555555555, 1200.50, 'GBP', 'client3', 'bank1', 0.01, 0.02, CURRENT_TIMESTAMP - INTERVAL '5 days'),
    ('account4', 1111111111, 4000.00, 'USD', 'client4', 'bank3', 0.012, 0.1, CURRENT_TIMESTAMP - INTERVAL '5 days'),
    ('account5', 1112222111, 4000.00, 'USD', 'client2', 'bank3', 0.01, 0.02, CURRENT_TIMESTAMP - INTERVAL '5 days'),
    ('account6', 8888888888, 2800.25, 'EUR', 'client5', 'bank2', 0.015, 0.025, CURRENT_TIMESTAMP - INTERVAL '5 days');


INSERT INTO balance (account_id, balance)
SELECT account_id, 0
FROM account;

INSERT INTO transaction (transaction_id, account_id, category_id, effective_date, amount, description)
VALUES
    ('transaction1', 'account1', 'category1', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 500.00, 'Salary'),
    ('transaction2', 'account2', 'category2', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 75.50, 'Groceries'),
    ('transaction3', 'account3', 'category3', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 2000.00, 'Investment'),
    ('transaction4', 'account4', 'category4', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 120.75, 'Dining'),
    ('transaction5', 'account5', 'category5', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 300.00, 'Gift');

INSERT INTO transfer (transfer_id, unique_reference, scheduled_effective_date, description, sender_account_id, is_internal, effective_date)
VALUES
    ('transfer1', 'REF001', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 'Friend Transfer', 'account1', TRUE, (CURRENT_TIMESTAMP + INTERVAL '2 days')),
    ('transfer2', 'REF002', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 'Family Transfer', 'account2', TRUE, (CURRENT_TIMESTAMP +INTERVAL '2 days')),
    ('transfer4', 'REF004', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 'Group Transfer', 'account4', TRUE, (CURRENT_TIMESTAMP + INTERVAL '2 days')),
    ('transfer5', 'REF005', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 'Business Transfer', 'account5', TRUE, (CURRENT_TIMESTAMP + INTERVAL '2 days'));

INSERT INTO transfer (transfer_id, unique_reference, scheduled_effective_date, description, sender_account_id, is_internal)
VALUES
    ('transfer3', 'REF003', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 'External Transfer', 'account3', FALSE);

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

INSERT INTO overdraft (overdraft_id, account_id, overdraft_allowed, overdraft_balance, overdraft_start_date, overdraft_reimbursement_date)
VALUES
    ('overdraft1', 'account1', TRUE, 500.00, (CURRENT_TIMESTAMP + INTERVAL '2 days'), (CURRENT_TIMESTAMP + INTERVAL '16 days')),
    ('overdraft2', 'account2', FALSE, 0.00, NULL, NULL),
    ('overdraft3', 'account3', TRUE, 1000.00, (CURRENT_TIMESTAMP + INTERVAL '2 days'), (CURRENT_TIMESTAMP + INTERVAL '18 days')),
    ('overdraft4', 'account4', FALSE, 0.00, NULL, NULL),
    ('overdraft5', 'account5', TRUE, 750.00, (CURRENT_TIMESTAMP + INTERVAL '2 days'), (CURRENT_TIMESTAMP + INTERVAL '14 days'));