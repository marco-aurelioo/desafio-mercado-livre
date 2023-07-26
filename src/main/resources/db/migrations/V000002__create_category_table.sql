CREATE TABLE category(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(100) NOT NULL UNIQUE,
    parent_ID INT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL);