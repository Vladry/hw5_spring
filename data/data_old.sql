# INSERT INTO regions
# VALUES (
#            1,
#            'Europe'
#        );
#
#
# COMMIT;
#
# /* ***************************FOREIGN KEYS****************************/
#
# ALTER TABLE countries ADD FOREIGN KEY (region_id) REFERENCES regions(region_id);
#
#
# INSERT INTO TBL_EMPLOYEES (name, surname, email) VALUES
#                                                      ('Lokesh', 'Gupta', 'abc@gmail.com'),
#                                                      ('Deja', 'Vu', 'xyz@email.com'),
#                                                      ('Caption', 'America', 'cap@marvel.com');