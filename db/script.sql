create table car_showroom (
vin numeric primary key,
model varchar(255),
color text,
year_of_manufacture numeric);

insert into car_showroom(vin, model, color,year_of_manufacture) values(12345, 'BMW', 'white', 1990);

update car_showroom set model ='Opel';

delete from car_showroom;