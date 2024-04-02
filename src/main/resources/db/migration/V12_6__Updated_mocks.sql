TRUNCATE TABLE balance RESTART IDENTITY;

INSERT INTO balance (account_id, balance)
SELECT account_id, 0
FROM account;

INSERT INTO transaction (transaction_id, account_id, category_id, effective_date, amount, description)
VALUES
    ('transaction1', 'account1', 'income_salary', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 500.00, 'Salary'),
    ('transaction2', 'account2', 'expense_groceries', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 75.50, 'Groceries'),
    ('transaction3', 'account3', 'income_investments', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 2000.00, 'Investment'),
    ('transaction4', 'account4', 'expense_food_drink', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 120.75, 'Dining'),
    ('transaction5', 'account5', 'income_gifts', (CURRENT_TIMESTAMP - INTERVAL '2 days'), 300.00, 'Gift');