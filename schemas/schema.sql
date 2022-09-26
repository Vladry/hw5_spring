DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts
(
    id       BIGINT  AUTO_INCREMENT UNIQUE NOT NULL,
    number   VARCHAR(255),
    currency INTEGER,
    balance  DOUBLE,
    customer_id BIGINT UNSIGNED,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

DROP TABLE IF EXISTS customers;
CREATE TABLE customers
(
    id    BIGINT  AUTO_INCREMENT UNIQUE NOT NULL,
    name  VARCHAR(100)        NOT NULL,
    email VARCHAR(100)        NOT NULL,
    age   INT UNSIGNED        NOT NULL CHECK (age > 18),

    employer_id BIGINT UNSIGNED,
    account_id BIGINT UNSIGNED,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

DROP TABLE IF EXISTS customers_employers;
CREATE TABLE customers_employers
(
    customers_id BIGINT,
    employers_id BIGINT,
    employer_id BIGINT,
    customer_id BIGINT
);

DROP TABLE IF EXISTS employers;
CREATE TABLE employers
(
    id      BIGINT AUTO_INCREMENT UNIQUE NOT NULL,
    name    VARCHAR(30),
    street VARCHAR(100),
    address VARCHAR(100),
    customer_id BIGINT,
    CONSTRAINT pk_employers PRIMARY KEY (id)
);

DROP TABLE IF EXISTS hibernate_sequence;
CREATE TABLE hibernate_sequence (next_val BIGINT(20));






