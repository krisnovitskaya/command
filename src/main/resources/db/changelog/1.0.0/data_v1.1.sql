-- Prod init database data
-- username: admin password: 100 (bcrypt hash)
insert into users(username, password)
values
    ('admin', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem');

insert into roles(name)
values
    ('ROLE_ADMIN'),
    ('ROLE_MASTER'),
    ('ROLE_EMPLOYEE');

insert into errands_status_types (status)
values
    ('REQUESTED'),      
    ('CONFIRMED'),      
    ('REJECTED');

insert into errands_matter_types (matter)
values
    ('LOCAL'), 
    ('PRIVATE'),     
    ('LONG');

insert into users_roles (user_id, role_id)
values
(1, 1);   -- admin    