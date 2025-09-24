CREATE TABLE car_bodies (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) 
);

CREATE TABLE car_engines (
   id SERIAL PRIMARY KEY,
    name VARCHAR(50) 
);

CREATE TABLE car_transmissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) 
);

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
	body_id  INT  REFERENCES car_bodies(id),
	engine_id INT  REFERENCES car_engines(id),
	transmission_id INT  REFERENCES car_transmissions(id)    
);


INSERT INTO car_bodies (name) VALUES
('Седан'),
('Хэтчбек'),
('Универсал'),
('Кроссовер'),
('Купе');

INSERT INTO car_engines (name) VALUES
('Бензиновый 1.6'),
('Бензиновый 2.0'),
('Дизель'),
('Гибрид'),
('Электро');

INSERT INTO car_transmissions (name) VALUES
('Механика'),
('Автомат'),
('Робот');


INSERT INTO cars (name, body_id, engine_id, transmission_id) VALUES
('Toyota Corolla', 1, 1, 2),   
('Volkswagen Polo', 2, 1, 1),    
('Lada Granta', 3, 1, 2),
('Volkswagen Tiguan ', 4, 4, 2), 
('Tesla Model', 1, 5, 3); 

/*Вывести список всех машин и все привязанные к ним детали. Нужно учесть, что каких-то деталей машина может и не содержать.
В таком случае значение может быть null при выводе (например, название двигателя null);*/
SELECT 
    c.id AS id,
    c.name AS car_name,
    b.name AS body_name,
    e.name AS engine_name,
    t.name AS transmission_name
FROM cars c
    LEFT JOIN car_bodies b ON c.body_id = b.id
    LEFT JOIN car_engines e ON c.engine_id = e.id
    LEFT JOIN car_transmissions t ON c.transmission_id = t.id;
	
/*Вывести кузова, которые не используются НИ в одной машине.
"Не используются" значит, что среди записей таблицы cars отсутствует внешние ключи,
ссылающие на таблицу car_bodies. Например, Вы добавили в car_bodies "седан", "хэтчбек" и "пикап", 
а при добавлении в таблицу cars указали только внешние ключи на записи "седан" и "хэтчбек". 
Запрос, касающийся этого пункта, должен вывести "пикап", т.к. среди машин нет тех, что обладают таким кузовом;*/

	SELECT 
    b.id,
    b.name AS body_name
FROM car_bodies b
    LEFT JOIN cars c ON b.id = c.body_id
WHERE c.id IS NULL;

/*Вывести двигатели, которые не используются НИ в одной машине*/

SELECT 
    e.id,
    e.name AS engine_name
FROM car_engines e
    LEFT JOIN cars c ON e.id = c.engine_id
WHERE c.id IS NULL;

/*Вывести коробки передач, которые не используются НИ в одной машине*/
SELECT 
    t.id,
    t.name AS transmission_name
FROM car_transmissions t
    LEFT JOIN cars c ON t.id = c.transmission_id
WHERE c.id IS NULL;


