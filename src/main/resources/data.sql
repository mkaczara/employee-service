-- Employees
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (1, 'Theresa', 'Carr', 25, 'FEMALE');
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (2, 'Ramona', 'Vega', 30, 'FEMALE');
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (3, 'Kristina', 'Hines', 35, 'CROSS');
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (4, 'Clint', 'Hunt', 40, 'MALE');
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (5, 'Gwendolyn', 'Buchanan', 45, 'MALE');
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (6, 'Cameron', 'Figueroa', 50, 'FEMALE');
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (7, 'Debra', 'Taylor', 55, 'FEMALE');
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (8, 'Geraldine', 'Smith', 60, 'FEMALE');
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (9, 'Shannon', 'Tran', 65, 'MALE');
INSERT INTO employee (id, first_name, last_name, age, gender) VALUES (10, 'Gabriel', 'Rowe', 70, 'MALE');

-- Addresses
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (1, 1, '73 Orange St.', 'San Francisco', '94122', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (2, 2, '8007 Pineknoll St.', 'Fairfield', '94533', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (3, 3, '5 Cobblestone Ave.', 'Oceanside', '92054', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (4, 4, '47 Jockey Hollow Street', 'Hacienda Heights', '91745', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (5, 5, '604 Roehampton Street', 'Tulare', '93274', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (6, 6, '7882 Golf St.', 'National City', '91950', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (7, 7, '720 Buckingham St.', 'Reseda', '91335', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (8, 8, '589 Glenlake Court', 'Fontana', '92335', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (9, 9, '567 Ocean St.', 'Los Angeles', '90063', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (10, 10, '8655 N. Bayberry Dr.', 'Lancaster', '93535', 'CA');
INSERT INTO address(id, employee_id, street_address, city, post_code, state) VALUES (11, 1, '555 Eagle Street', 'El Cajon', '92021', 'CA');

-- Adjust sequences
ALTER SEQUENCE employee_id_seq RESTART WITH 11;
ALTER SEQUENCE address_id_seq RESTART WITH 12;