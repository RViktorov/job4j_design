
insert into roles (name) values 
('Admin'),
('User');

insert into users (name, roles_id) values
('Иван Иванов', 1), 
('Петр Петров', 2);

insert into rules (name_rules) values
('Создание заявки'),
('Редактирование заявки'),
('Удаление заявки');

insert into roles_rules (roles_id, rules_id) values
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 2);


insert into states (name) values
('Новая'),
('В работе'),
('Закрыта');

insert into categories (name) values
('Проблемы с ПО'),
('Доступы'),
('Общие вопросы');

insert into items (name_items, users_id, categories_id, states_id) values
('Не работает принтер', 1, 1, 1),
('Нужен доступ к ПО', 2, 2, 1)
;

insert into comments (name, items_id) values
('Проверьте кабель принтера', 1),
('Необходимо уточнить какие доступы нужны', 2);

insert into attachs (name, file, items_id) values
('Скриншот ошибки', null, 1),
('Скрин закладки ПО', null, 2);
