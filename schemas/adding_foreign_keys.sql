CREATE INDEX IN_Customers on customers(id);
CREATE INDEX IN_Employees on employers(id);
CREATE INDEX IN_Accounts  on  accounts(id);

ALTER TABLE accounts ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id)  REFERENCES customers(id);
ALTER TABLE customers ADD CONSTRAINT fk_employer FOREIGN KEY (employer_id) REFERENCES employers(id);
ALTER TABLE customers ADD CONSTRAINT fk_account  FOREIGN KEY (account_id)  REFERENCES accounts(id);


SELECT TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, CONSTRAINT_NAME
    FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE
    WHERE REFERENCED_TABLE_SCHEMA IS NOT NULL;

SET foreign_key_checks = 1;
