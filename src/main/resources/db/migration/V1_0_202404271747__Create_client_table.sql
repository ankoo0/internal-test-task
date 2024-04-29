CREATE TABLE client
(
    id         UUID PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    job        VARCHAR(50),
    position VARCHAR(50)
);