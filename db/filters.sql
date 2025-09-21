--type
CREATE TABLE type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

--product
CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type_id INT,
    expired_date DATE,
    price INT,
    fk_product_type INT  REFERENCES type(id)
);


INSERT INTO type (name) VALUES
('СЫР'),
('МОЛОКО'),
('МОРОЖЕНОЕ'),
('ХЛЕБ'),
('ЙОГУРТ');


INSERT INTO product (name, type_id, expired_date, price) VALUES
('Сыр моцарелла', 1, '2025-09-01', 200.00),
('Сыр плавленный', 1, '2025-09-10', 150.00),
('Молоко 2.5%', 2, '2025-09-05', 70.00),
('Молоко ультрапастеризованное', 2, '2025-09-12', 90.00),
('Мороженое пломбир', 3, '2025-12-01', 120.00),
('Мороженое эскимо', 3, '2025-11-15', 130.00),
('Хлеб бородинский', 4, '2025-09-02', 45.00),
('Йогурт клубничный', 5, '2025-09-08', 100.00);


--Написать запрос получение всех продуктов с типом "СЫР"
SELECT p.id,
       p.name,
       p.type_id,
       p.expired_date,
       p.price
FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name = 'СЫР';

--Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
SELECT *
FROM product
WHERE name LIKE '%мороженое%';

--Написать запрос, который выводит все продукты, срок годности которых уже истек

SELECT *
FROM product
WHERE expired_date < CURRENT_DATE;

/*Написать запрос, который выводит самый дорогой продукт. 
Запрос должен быть универсальный и находить все продукты с максимальной ценой.
Например, если в таблице есть продукт типа СЫР с ценой 200 и продукт типа МОЛОКО 
с ценой 200, и эта цена максимальная во всей таблице, то запрос должен вывести оба этих продукта*/

SELECT *
FROM product
WHERE price = (SELECT MAX(price) FROM product);

/*Написать запрос, который выводит 
для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество*/

SELECT t.name AS имя_типа,
       COUNT(p.id) AS количество
FROM type t
JOIN product p ON p.type_id = t.id
GROUP BY t.name;

--Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.id,
       p.name,
       p.expired_date,
       p.price,
       t.name AS type_name
FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name IN ('СЫР', 'МОЛОКО');

/*Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
Под количеством подразумевается количество продуктов определенного типа. Например,
если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат,
то количество продуктов типа "СЫР" будет 2.*/
SELECT t.name AS имя_типа,
       COUNT(p.id) AS количество
FROM type t
JOIN product p ON p.type_id = t.id
GROUP BY t.name
HAVING COUNT(p.id) < 10;

--Вывести все продукты и их тип

SELECT p.id,
       p.name AS product_name,
       p.expired_date,
       p.price,
       t.name AS type_name
FROM product p
JOIN type t ON p.type_id = t.id;
