TRUNCATE TABLE transaction, operation_category RESTART IDENTITY;

INSERT INTO operation_category (category_id, name, operation_type)
VALUES
    ('income_others', 'Others', 'income'),
    ('income_dividends', 'Dividends', 'income'),
    ('income_freelance', 'Freelance Work', 'income'),
    ('income_gifts', 'Gifts/Donations', 'income'),
    ('income_interest', 'Interest', 'income'),
    ('income_investments', 'Investments', 'income'),
    ('income_rental', 'Rental Income', 'income'),
    ('income_salary', 'Salary/Wages', 'income'),
    ('income_sales', 'Sales', 'income')
ON CONFLICT (category_id) DO NOTHING;

INSERT INTO operation_category (category_id, name, operation_type)
VALUES
    ('expense_others', 'Others', 'expense'),
    ('expense_business', 'Business', 'expense'),
    ('expense_child_care', 'Child Care', 'expense'),
    ('expense_communications', 'Communications', 'expense'),
    ('expense_debt', 'Debt Payments', 'expense'),
    ('expense_education', 'Education', 'expense'),
    ('expense_entertainment', 'Entertainment', 'expense'),
    ('expense_food_drink', 'Food and Drink', 'expense'),
    ('expense_gifts', 'Gifts/Donations', 'expense'),
    ('expense_groceries', 'Groceries', 'expense'),
    ('expense_healthcare', 'Healthcare', 'expense'),
    ('expense_personal', 'Personal Care', 'expense'),
    ('expense_pets', 'Pets', 'expense'),
    ('expense_rent', 'Rent/Mortgage', 'expense'),
    ('expense_shopping', 'Shopping', 'expense'),
    ('expense_subscriptions', 'Subscriptions', 'expense'),
    ('expense_travel_vacation', 'Travel/Vacation', 'expense'),
    ('expense_transportation', 'Transportation', 'expense'),
    ('expense_utilities', 'Utilities', 'expense')
ON CONFLICT (category_id) DO NOTHING;