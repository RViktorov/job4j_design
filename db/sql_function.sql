create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);
insert into devices(name, price) values ('телевизор', 30000);
insert into devices(name, price) values ('телевизор', 6000);
insert into devices(name, price) values ('телевизор', 10000);
insert into devices(name, price) values ('чайник', 10000);
insert into devices(name, price) values ('чайник', 40000);
insert into devices(name, price) values ('фен', 1500);
insert into devices(name, price) values ('фен', 2500);

insert into people(name) values ('Вася');
insert into people(name) values ('Петя');
insert into people(name) values ('Оля');
insert into people(name) values ('Аня');

insert into devices_people(device_id, people_id) values (1,1);
insert into devices_people(device_id, people_id) values (1,2);
insert into devices_people(device_id, people_id) values (1,4);
insert into devices_people(device_id, people_id) values (2,1);
insert into devices_people(device_id, people_id) values (3,1);
insert into devices_people(device_id, people_id) values (3,4);
insert into devices_people(device_id, people_id) values (4,4);

select avg(price) avg_price_devices from devices;

select p.name, avg(d.price) as avg_price
from people p
join devices_people dp on p.id = dp.people_id
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) as avg_price
from people p
join devices_people dp on p.id = dp.people_id
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price)>5000;

