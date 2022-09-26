ALTER TABLE accounts DROP FOREIGN KEY fk_accounts;
ALTER TABLE accounts DROP FOREIGN KEY fk_customer_id;
ALTER TABLE customers MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE customers DROP FOREIGN KEY `fk_employer`;
ALTER TABLE employers MODIFY COLUMN id BIGINT  AUTO_INCREMENT NOT NULL;

ALTER TABLE customers DROP FOREIGN KEY fk_account;
ALTER TABLE accounts MODIFY COLUMN id BIGINT AUTO_INCREMENT NOT NULL;

/*
больше информации по удалению связанных таблиц:
https://postgrespro.ru/docs/postgresql/9.6/sql-droptable
https://postgrespro.ru/docs/postgresql/9.6/ddl-depend   (отслеживание зависимостей)
*/

