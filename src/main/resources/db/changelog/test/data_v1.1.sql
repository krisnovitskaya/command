-- username: admin password: DLda#|d7~c?Q (bcrypt hash)
insert into errands.users(username, password)
values
    ('admin', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K'),
    ('user1', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K'),
    ('user2', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K'),
    ('user3', '$2y$12$ThsVE9vmV/bRHpvX4fVKmuoFVR9uWWqjMDZLjmP/.9Qm9L1sWup5K');

insert into errands.roles(role_name)
values
    ('ROLE_ADMIN'),
    ('ROLE_MASTER'),
    ('ROLE_EMPLOYEE');

insert into errands.users_roles(user_id, role_id)
values
    (1, 1),
    (1, 2),
    (2, 3),
    (3, 3);

insert into errands.errands_status_types (status)
values
    ('REQUESTED'),      
    ('CONFIRMED'),      
    ('REJECTED');

insert into errands.errands_matter_types (matter)
values
    ('LOCAL'), 
    ('PRIVATE'),     
    ('LONG');