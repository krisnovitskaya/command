-- Data for testing
-- users passwords: 100 (bcrypt hash)
insert into errands.users(username, password)
values
    ('master1', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K'),   --id 2
    ('master2', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K'),   --id 3
    ('master3', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K'),   --id 4
    ('employee1', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K'), --id 5
    ('employee2', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K'), --id 6
    ('employee3', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K'); --id 7

insert into errands.users_roles(user_id, role_id)
values
    (1, 1),   -- admin
    (2, 2),
    (3, 2),
    (4, 2),
    (5, 3),
    (6, 3),
    (7, 3);

insert into errands.positions(position)
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

insert into errands.departments(title, master_id, master_department_id)
values
    ('Департамент 1', null, null),    --id 1
    ('Департамент 2', null, null),    --id 2
    ('Департамент 3', null, null),    --id 3
    ('Департамент 4', null, null),    --id 4
    ('Департамент 5', null, null),    --id 5
    ('Департамент 6', null, null),    --id 6
    ('Департамент 7', null, null),    --id 7
    ('Департамент 8', null, null),    --id 8
    ('Департамент 9', null, null),    --id 9
    ('Департамент 10', null, null),   --id 10
    ('Департамент 11', null, null);   --id 11

insert into errands.employees_details(mail)
values
    ('email1@net.com'),     --id 1
    ('email2@net.com'),     --id 2
    ('email3@net.com'),     --id 3
    ('email4@net.com'),     --id 4
    ('email5@net.com'),     --id 5
    ('email6@net.com'),     --id 6
    ('email7@net.com'),     --id 7
    ('email8@net.com'),     --id 8
    ('email9@net.com'),     --id 9
    ('email10@net.com'),    --id 10
    ('email11@net.com');    --id 11

insert into errands.employees(last_name, first_name, middle_name, position_id, department_id, user_id, employee_details_id)
values
    ('Админ', 'Админ', 'Админ', 11, 11, 1, 11),                   --id 1
    ('Фамилия1', 'Имя1', 'Отчество1', 1, 1, 2, 1),                --id 2
    ('Фамилия2', 'Имя2', 'Отчество2', 2, 2, 3, 2),                --id 3
    ('Фамилия3', 'Имя3', 'Отчество3', 3, 3, 4, 3),                --id 4
    ('Фамилия4', 'Имя4', 'Отчество4', 4, 4, 5, 4),                --id 5
    ('Фамилия5', 'Имя5', 'Отчество5', 5, 5, 6, 5),                --id 6
    ('Фамилия6', 'Имя6', 'Отчество6', 6, 6, 7, 6),                --id 7
    ('Фамилия7', 'Имя7', 'Отчество7', 7, 7, 2, 7),                --id 8
    ('Фамилия8', 'Имя8', 'Отчество8', 8, 8, 3, 8),                --id 9
    ('Фамилия9', 'Имя9', 'Отчество9', 9, 9, 4, 9),                --id 10
    ('Фамилия10', 'Имя10', 'Отчество10', 10, 10, 5, 10);          --id 11

insert into errands.place_types(type)
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

insert into errands.places(place_type_id, title)
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
insert into errands.errands_details(matter_id, place_id, comment, created_by, confirmed_or_rejected_by, created_at)
values
    (1, 1, 'Комментарий1', 1, 2, '2021-03-18'),     --id 1
    (2, 2, 'Комментарий2', 2, 3, '2021-03-17'),     --id 2
    (3, 3, 'Комментарий3', 3, 4, '2021-03-16'),     --id 3
    (1, 4, 'Комментарий4', 4, 8, '2021-03-15'),     --id 4
    (2, 5, 'Комментарий5', 5, 9, '2021-03-14'),     --id 5
    (3, 6, 'Комментарий6', 6, 10, '2021-03-13'),    --id 6
    (1, 7, 'Комментарий7', 7, 2, '2021-03-12'),     --id 7
    (2, 8, 'Комментарий8', 8, 3, '2021-03-11'),     --id 8
    (3, 9, 'Комментарий9', 9, 4, '2021-03-10'),     --id 9
    (1, 10, 'Комментарий10', 10, 8, '2021-03-09');  --id 10

insert into errands.errands(status_id, employee_id, errands_details_id, date_start, date_end)
values
    (1, 2, 1, '2021-03-18', '2021-03-19'),        --id 1
    (2, 3, 2, '2021-03-18', '2021-03-19'),        --id 2
    (3, 4, 3, '2021-03-18', '2021-03-19'),        --id 3
    (1, 5, 4, '2021-03-18', '2021-03-19'),        --id 4
    (2, 6, 5, '2021-03-18', '2021-03-19'),        --id 5
    (3, 7, 6, '2021-03-18', '2021-03-19'),        --id 6
    (1, 8, 7, '2021-03-18', '2021-03-19'),        --id 7
    (2, 9, 8, '2021-03-18', '2021-03-19'),        --id 8
    (3, 10, 9, '2021-03-18', '2021-03-19'),       --id 9
    (1, 11, 10, '2021-03-18', '2021-03-19');      --id 10
