--1
CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);
/* 1)  Триггер должен срабатывать после вставки данных, для любого товара
и просто насчитывать налог на товар (нужно прибавить налог к цене товара). 
Действовать он должен не на каждый ряд, а на запрос (statement уровень)*/
CREATE OR REPLACE FUNCTION add_tax_to_products()
RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price * 1.2
    WHERE id IN (SELECT id FROM products);
    RETURN NULL;
END;
$$ 
LANGUAGE plpgsql;

CREATE TRIGGER trg_add_tax
AFTER INSERT ON products
FOR EACH STATEMENT
EXECUTE FUNCTION add_tax_to_products();

/* 2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара).
Здесь используем row уровень.*/

CREATE OR REPLACE FUNCTION add_tax_before_insert()
RETURNS trigger AS
$$
BEGIN
    NEW.price := NEW.price * 1.2;  
    RETURN NEW;
	END;
$$
LANGUAGE plpgsql;


CREATE TRIGGER trg_add_tax_row
BEFORE INSERT ON products
FOR EACH ROW
EXECUTE FUNCTION add_tax_before_insert();

--2
CREATE TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

/*   Нужно написать триггер на row уровне, который сразу после вставки продукта в таблицу products,
будет заносить имя, цену и текущую дату в таблицу history_of_price. 
CREATE OR REPLACE FUNCTION info_product_insert()*/
RETURNS trigger AS
$$
BEGIN
    INSERT INTO history_of_price
	(name, price, date)VALUES (NEW.name, NEW.price, NOW());
    RETURN NEW;
END;
$$ 
LANGUAGE plpgsql;

CREATE TRIGGER product_insert
AFTER INSERT ON products
FOR EACH ROW
EXECUTE FUNCTION info_product_insert();

