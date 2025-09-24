CREATE TABLE departments (
id SERIAL PRIMARY KEY,
 name VARCHAR(100));
	
CREATE TABLE employees(
id SERIAL PRIMARY KEY,
name VARCHAR(100),
department_id INT  REFERENCES departments(id));

INSERT INTO departments (name) VALUES
('Бухгалтерия'),
('IT'),
('Отдел продаж');

INSERT INTO employees (name, department_id) VALUES
('Семен Иванов', 1),
('Роман Петров', 1),
('Маша Смирнова', 2),
('Сергей Кузнецов', 2),
('Валя Орлова', 3);

-- запрос с left соединением
SELECT d.name AS department, e.name AS employee
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id;

-- запрос с right соединением
SELECT d.name AS department, e.name AS employee
FROM departments d
RIGHT JOIN employees e ON d.id = e.department_id;

--запрос full соединением
SELECT d.name AS department, e.name AS employee
FROM departments d
FULL JOIN employees e ON d.id = e.department_id;

--запрос с cross join соединением
SELECT d.name AS department, e.name AS employee
FROM departments d
CROSS JOIN employees e;

--Используя left join найти департаменты, у которых нет работников
SELECT d.id, d.name AS department_name
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
WHERE e.id IS NULL;

/*Используя left и right join написать запросы, которые
давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).*/
--1
SELECT d.id   AS department_id,
       d.name AS department_name,
       e.id   AS employee_id,
       e.name AS employee_name
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id;

--2
SELECT d.id   AS department_id,
       d.name AS department_name,
       e.id   AS employee_id,
       e.name AS employee_name
FROM employees e
RIGHT JOIN departments d ON d.id = e.department_id;



/*Создать таблицу teens с атрибутами name, gender и заполнить ее.
Используя cross join составить все возможные разнополые пары.
Исключите дублирование пар вида Вася-Маша и Маша-Вася.*/

CREATE TABLE teens (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    gender CHAR(1)
);

INSERT INTO teens (name, gender) VALUES
('Сеня', 'М'),
('Петя', 'М'),
('Вася', 'М'),
('Маша', 'Ж'),
('Оля', 'Ж'),
('Катя', 'Ж');


SELECT t1.name AS teen1,
       t2.name AS teen2
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender < t2.gender;


