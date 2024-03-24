INSERT INTO client (client_id, last_name, first_name, birth_date)
VALUES
    ('client1', 'Smith', 'John', '1990-05-15'),
    ('client2', 'Johnson', 'Emily', '1985-12-02'),
    ('client3', 'Williams', 'Michael', '1978-09-20'),
    ('client4', 'Brown', 'Sarah', '1992-03-10'),
    ('client5', 'Davis', 'David', '1988-07-25');

INSERT INTO operation_category (category_id, name, operation_type_id)
VALUES
    ('category1', 'Salary', 1),
    ('category2', 'Groceries', 2),
    ('category3', 'Investment', 1),
    ('category4', 'Dining', 2),
    ('category5', 'Gift', 1);

INSERT INTO "bank" ("bank_id", "name") VALUES
    ('bank1', 'Bank of America'),
    ('bank2', 'Chase Bank'),
    ('bank3', 'Wells Fargo');

INSERT INTO account (account_id, account_number, monthly_net_salary, default_currency_code, client_id, bank_id)
VALUES
    ('account1', 1234567890, 2500.00, 'USD', 'client1', 'bank1'),
    ('account2', 9876543210, 3800.75, 'EUR', 'client2', 'bank2'),
    ('account3', 5555555555, 1200.50, 'GBP', 'client3', 'bank1'),
    ('account4', 1111111111, 4000.00, 'USD', 'client4', 'bank3'),
    ('account5', 1112222111, 4000.00, 'USD', 'client2', 'bank3'),
    ('account6', 8888888888, 2800.25, 'EUR', 'client5', 'bank2');

INSERT INTO transaction (transaction_id, account_id, category_id, effective_date, amount, description)
VALUES
    ('transaction1', 'account1', 'category1', '2023-05-01 10:30:00', 500.00, 'Salary'),
    ('transaction2', 'account2', 'category2', '2023-05-02 15:45:00', -75.50, 'Groceries'),
    ('transaction3', 'account3', 'category3', '2023-05-03 09:15:00', 2000.00, 'Investment'),
    ('transaction4', 'account4', 'category4', '2023-05-04 18:20:00', -120.75, 'Dining'),
    ('transaction5', 'account5', 'category5', '2023-05-05 13:00:00', 300.00, 'Gift');

INSERT INTO transfer (transfer_id, unique_reference, scheduled_effective_date, amount, description, sender_account_id, is_internal, effective_date)
VALUES
    ('transfer1', 'REF001', '2023-05-06', 1000.00, 'Friend Transfer', 'account1', TRUE, '2023-05-05'),
    ('transfer2', 'REF002', '2023-05-07', 500.00, 'Family Transfer', 'account2', TRUE, '2023-05-03'),
    ('transfer4', 'REF004', '2023-05-09', 750.00, 'Group Transfer', 'account4', TRUE, '2023-05-06'),
    ('transfer5', 'REF005', '2023-05-10', 1200.00, 'Business Transfer', 'account5', TRUE , '2023-05-08');

INSERT INTO transfer (transfer_id, unique_reference, scheduled_effective_date, amount, description, sender_account_id, is_internal)
VALUES
    ('transfer3', 'REF003', '2023-05-08', 2500.00, 'External Transfer', 'account3', FALSE);

INSERT INTO transfer_recipient (transfer_id, recipient_account_id)
VALUES
    ('transfer1', 'account2'),
    ('transfer2', 'account3'),
    ('transfer2', 'account4'),
    ('transfer4', 'account1'),
    ('transfer4', 'account2'),
    ('transfer4', 'account3');

INSERT INTO overdraft (overdraft_id, account_id, overdraft_allowed, overdraft_balance, overdraft_start_date, overdraft_reimbursement_date, overdraft_interest_day1to7, overdraft_interest_after7)
VALUES
    ('overdraft1', 'account1', TRUE, 500.00, '2023-05-01', '2023-05-15', 0.01, 0.02),
    ('overdraft2', 'account2', FALSE, 0.00, NULL, NULL, NULL, NULL),
    ('overdraft3', 'account3', TRUE, 1000.00, '2023-05-03', '2023-05-20', 0.015, 0.025),
    ('overdraft4', 'account4', FALSE, 0.00, NULL, NULL, NULL, NULL),
    ('overdraft5', 'account5', TRUE, 750.00, '2023-05-05', '2023-05-18', 0.01, 0.02);

