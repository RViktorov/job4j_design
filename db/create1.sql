-- Роли
create table roles (
    id serial primary key, 
    name varchar(255) 
);

-- Пользователи
create table users (
    id serial primary key, 
    name varchar(255) ,
    roles_id int references roles(id)
);

-- Правила
create table rules (
    id serial primary key, 
    name_rules varchar(255) 
);

-- Связь ролей и правил 
create table roles_rules(
    id serial primary key,
    roles_id int references roles(id) ,
    rules_id int references rules(id)
);

-- Статусы 
create table states (
    id serial primary key, 
    name text 
);

-- Категории заявки
create table categories (
    id serial primary key, 
    name text 
);

-- Заявки
create table items (
    id serial primary key, 
    name_items varchar(255) not null,
    users_id int references users(id) ,
    categories_id int references categories(id) ,
    states_id int references states(id)
);

-- Комментарии
create table comments (
    id serial primary key, 
    name varchar(255) not null,
    items_id int references items(id) 
);

-- Файлы
create table attachs (
    id serial primary key, 
    name text,
    file bytea,
    items_id int references items(id)
);