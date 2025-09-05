
create table address(
    id serial primary key,
    registration_address varchar(255)
    );

create table people(
    id serial primary key,
    name varchar(255),
    address_id int references address(id) unique
);

insert into address(registration_address) values ('Москва, Тверская, д.7');
insert into address(registration_address) values ('Москва, Ленинский прт, д.17');
insert into address(registration_address) values ('Москва, Ивановская площадь, д.1');

insert into people(name, address_id) values ('Semen', 1);
insert into people(name, address_id) values ('Roman', 2);
insert into people(name, address_id) values ('Anna', 3);

select p.name, a.registration_address 
from people as p join address as a on p.address_id = a.id;

select p.name, a.registration_address 
from people as p join address as a on p.address_id = a.id and
registration_address is not null;

select p.name, a.registration_address 
from people as p join address as a on p.address_id = a.id and
registration_address= 'Москва, Ивановская площадь, д.1';
