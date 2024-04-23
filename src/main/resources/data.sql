CREATE TABLE IF NOT EXISTS exchange (
    id SERIAL PRIMARY KEY,
    origin_currency VARCHAR(255),
    final_currency VARCHAR(255),
    date VARCHAR(255),
    value VARCHAR(255)
);