CREATE OR REPLACE VIEW account_view AS
SELECT
    a.account_id,
    a.account_number,
    a.creation_date,
    a.monthly_net_salary,
    a.default_currency_code,
    a.is_active,
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