create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('Слоны', 3000, '1820-09-01');
insert into fauna(name, avg_age, discovery_date)
values ('Вороны', 10000, '1930-01-01');
insert into fauna(name, avg_age, discovery_date)
values ('Рыбы', 1000, '2000-01-01');
insert into fauna(name, avg_age)
values ('Птицы', 2000);

--1) Извлечение данных, у которых имя name содержит подстроку fish
select * FROM fauna
where name LIKE '%fish%';

--2) Извлечение данных, у которых сред. продолжительность жизни находится в диапазоне 10 000 и 21 000
select * FROM fauna
where avg_age BETWEEN 10000 AND 21000 ;

--3) Извлечение данных, у которых дата открытия не известна (null)
select * FROM fauna
where discovery_date is null ;
--4) Извлечение данных видов, у которых дата открытия раньше 1950 года
select * FROM fauna
where discovery_date<'1950-01-01' ;


