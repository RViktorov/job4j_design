--one to many--
create table model_car (
    id serial primary key,
    name varchar(255)
 );

create table car (
    id serial primary key,
    car_manufacturer varchar(255),
    model_id int references model_car(id)
);

--- many to many---
create table clients(
    id serial primary key,
    name varchar(255)
);

create  products(
     id serial primary key,
     name varchar(255)
);

create table orders(
    id serial primary key,
    client_id int references clients(id),
    product_id int references products(id)
);

---one to one--
create table vin_car(
    id serial primary key,
    vin_number int,
    );

create table car(
    id serial primary key,
    number int,
	model varchar(255)
);

create table base_cars(
    id serial primary key,
    vin_car_id int references vin_car(id) unique,
    car_id int references car(id) unique
);




