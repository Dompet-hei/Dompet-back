UPDATE transaction
SET amount = ABS(amount)
WHERE transaction_id IN ('transaction2', 'transaction4');