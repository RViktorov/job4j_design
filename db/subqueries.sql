/*Выполните запрос, который вернет список клиентов, возраст которых является минимальным.*/
CREATE TABLE customers
(   id         SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    age        INT,
    country    TEXT);
INSERT INTO customers (first_name, last_name, age, country)
VALUES ('Anna', 'Ivanova', 25, 'Russia'),
('Ivan', 'Petrov', 30, 'Russia'),
('Maria', 'Sidorova', 22, 'Belarus');

SELECT *FROM customers
WHERE age = (SELECT MIN(age) FROM customers);


/*Необходимо выполнить запрос, который вернет список пользователей, которые еще не выполнили ни одного заказа.*/
CREATE TABLE orders
(  id          SERIAL PRIMARY KEY,
    amount      INT,
    customer_id INT REFERENCES customers (id));

INSERT INTO orders (amount, customer_id)VALUES (120, 1), (250, 2),  (300, 2);

SELECT * FROM customers
WHERE id NOT IN (SELECT customer_id FROM orders);

