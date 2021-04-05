-- Data for testing
-- users passwords: 100 (bcrypt hash)
insert into users(username, password)
values
    ('master2', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'),   --id 2
    ('master3', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'),   --id 3
    ('master4', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'),   --id 4
    ('master5', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'),   --id 5
    ('master6', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'),   --id 6
    ('master7', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'),   --id 7
    ('master8', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'),   --id 8
    ('master9', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'),   --id 9
    ('employee10', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 10
    ('employee11', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 11
    ('employee12', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 12
    ('employee13', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 13
    ('employee14', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 14
    ('employee15', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 15
    ('employee16', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 16
    ('employee17', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 17
    ('employee18', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 18
    ('employee19', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'), --id 19
    ('employee20', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem'); --id 20

insert into users_roles(user_id, role_id)
values
    (2, 2), (3, 2), (4, 2), (5, 2), (6, 2), (7, 2), (8, 2), (9, 2),
    (10, 3), (11, 3), (12, 3), (13, 3), (14, 3), (15, 3), (16, 3), (17, 3), (18, 3), (19, 3), (20, 3);

insert into positions(position)
values
    ('position1'),   --id 1
    ('position2'),   --id 2
    ('position3'),   --id 3
    ('position4'),   --id 4
    ('position5'),   --id 5
    ('position6'),   --id 6
    ('position7'),   --id 7
    ('position8'),   --id 8
    ('position9'),   --id 9
    ('position10'),  --id 10
    ('position11');  --id 11

insert into departments(title)
values
    ('Департамент 1'),    --id 1
    ('Департамент 2'),    --id 2
    ('Департамент 3'),    --id 3
    ('Департамент 4'),    --id 4
    ('Департамент 5'),    --id 5
    ('Департамент 6'),    --id 6
    ('Департамент 7'),    --id 7
    ('Департамент 8'),    --id 8
    ('Департамент 9'),    --id 9
    ('Департамент 10'),   --id 10
    ('Департамент 11');   --id 11



insert into employees(last_name, first_name, middle_name, position_id, department_id, user_id)
values
    ('Админ', 'Админ', 'Админ', 11, 11, 1),                   --id 1
    ('Фамилия2', 'Имя2', 'Отчество2', 2, 2, 2),                --id 2
    ('Фамилия3', 'Имя3', 'Отчество3', 3, 3, 3),                --id 3
    ('Фамилия4', 'Имя4', 'Отчество4', 4, 4, 4),                --id 4
    ('Фамилия5', 'Имя5', 'Отчество5', 5, 5, 5),                --id 5
    ('Фамилия6', 'Имя6', 'Отчество6', 6, 6, 6),                --id 6
    ('Фамилия7', 'Имя7', 'Отчество7', 7, 7, 7),                --id 7
    ('Фамилия8', 'Имя8', 'Отчество8', 8, 3, 8),                --id 8
    ('Фамилия9', 'Имя9', 'Отчество9', 9, 4, 9),                --id 9
    ('Фамилия10', 'Имя10', 'Отчество10', 10, 2, 10),          --id 10
    ('Фамилия11', 'Имя11', 'Отчество11', 10, 3, 11),          --id 11
    ('Фамилия12', 'Имя12', 'Отчество12', 10, 4, 12),          --id 12
    ('Фамилия13', 'Имя13', 'Отчество13', 10, 5, 13),          --id 13
    ('Фамилия14', 'Имя14', 'Отчество14', 10, 6, 14),          --id 14
    ('Фамилия15', 'Имя15', 'Отчество15', 10, 7, 15),          --id 15
    ('Фамилия16', 'Имя16', 'Отчество16', 10, 2, 16),          --id 16
    ('Фамилия17', 'Имя17', 'Отчество17', 10, 3, 17),          --id 17
    ('Фамилия18', 'Имя18', 'Отчество18', 10, 4, 18),          --id 18
    ('Фамилия19', 'Имя19', 'Отчество19', 10, 5, 19),          --id 19
    ('Фамилия20', 'Имя20', 'Отчество20', 10, 6, 20);          --id 20

insert into employees_details(mail, employee_id)
values
    ('email1@net.com', 1),     --id 1
    ('email2@net.com', 2),     --id 2
    ('email3@net.com', 3),     --id 3
    ('email4@net.com', 4),     --id 4
    ('email5@net.com', 5),     --id 5
    ('email6@net.com', 6),     --id 6
    ('email7@net.com', 7),     --id 7
    ('email8@net.com', 8),     --id 8
    ('email9@net.com', 9),     --id 9
    ('email10@net.com', 10),    --id 10
    ('email11@net.com', 11),    --id 11
    ('email12@net.com', 12),    --id 12
    ('email13@net.com', 13),    --id 13
    ('email14@net.com', 14),    --id 14
    ('email15@net.com', 15),    --id 15
    ('email16@net.com', 16),    --id 16
    ('email17@net.com', 17),    --id 17
    ('email18@net.com', 18),    --id 18
    ('email19@net.com', 19),    --id 19
    ('email20@net.com', 20);    --id 20

update departments set master_id = 2 where id = 2;
update departments set master_id = 3 , master_department_id = 2 where id = 3;
update departments set master_id = 4 , master_department_id = 2 where id = 4;
update departments set master_id = 5 , master_department_id = 3 where id = 5;
update departments set master_id = 6 , master_department_id = 4 where id = 6;
update departments set master_id = 7 , master_department_id = 5 where id = 7;

insert into places_types(type)
values
    ('place_type1'),    --id 1
    ('place_type2'),    --id 2 
    ('place_type3'),    --id 3 
    ('place_type4'),    --id 4
    ('place_type5'),    --id 5
    ('place_type6'),    --id 6
    ('place_type7'),    --id 7
    ('place_type8'),    --id 8
    ('place_type9'),    --id 9
    ('place_type10');   --id 10

insert into places(place_type_id, title)
values
    (1, 'Место1'),          --id 1
    (2, 'Место2'),          --id 2
    (3, 'Место3'),          --id 3
    (4, 'Место4'),          --id 4
    (5, 'Место5'),          --id 5
    (6, 'Место6'),          --id 6
    (7, 'Место7'),          --id 7
    (8, 'Место8'),          --id 8
    (9, 'Место9'),          --id 9
    (10, 'Место10');        --id 10

-- id 1 ('LOCAL'), id 2 ('PRIVATE'), id 3 ('LONG')
insert into errands_details(matter_id, place_id, comment, created_by, confirmed_or_rejected_by, created)
values
    (1, 1, 'Комментарий1', 2, 2, '2021-03-18'),     --id 1
    (3, 2, 'Комментарий2', 2, 2, '2021-03-17'),     --id 2
    (3, 3, 'Комментарий3', 4, 2, '2021-03-16'),     --id 3
    (1, 4, 'Комментарий4', 5, 2, '2021-03-15'),     --id 4
    (3, 5, 'Комментарий5', 13, 2, '2021-03-14'),     --id 5
    (1, 6, 'Комментарий6', 14, 2, '2021-03-13'),    --id 6
    (1, 7, 'Комментарий7', 15, 2, '2021-03-12'),     --id 7
    (1, 8, 'Комментарий8', 20, 2, '2021-03-11'),     --id 8
    (3, 9, 'Комментарий9', 20, 2, '2021-03-10'),     --id 9
    (2, 10, 'Комментарий10', 1, 2, '2021-03-09');  --id 10

insert into errands(status_id, employee_id, errand_details_id, date_start, date_end)
values
    (1, 2, 1, '2021-03-18', '2021-03-19'),        --id 1
    (2, 2, 2, '2021-03-18', '2021-03-19'),        --id 2
    (3, 4, 3, '2021-03-18', '2021-03-19'),        --id 3
    (1, 5, 4, '2021-03-18', '2021-03-19'),        --id 4
    (2, 13, 5, '2021-03-18', '2021-03-19'),        --id 5
    (3, 14, 6, '2021-03-18', '2021-03-19'),        --id 6
    (1, 15, 7, '2021-03-18', '2021-03-19'),        --id 7
    (2, 20, 8, '2021-03-18', '2021-03-19'),        --id 8
    (3, 20, 9, '2021-03-18', '2021-03-19'),       --id 9
    (1, 11, 10, '2021-03-18', '2021-03-19');      --id 10

insert into errands(status_id, employee_id, errand_details_id, date_start, date_end, created, updated)
values
    (1, 5, 4, '2021-03-18', '2021-03-19', '2021-03-19', '2021-03-19');        --id 11
