CREATE TABLE transaction_history (
    id BIGINT,
    name VARCHAR(50),
    card_type VARCHAR(20),
    amount DOUBLE PRECISION
);

DELETE FROM transaction_history;

SELECT * FROM transaction_history;