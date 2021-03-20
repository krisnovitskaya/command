-- Test init database data
-- username: admin password: DLda#|d7~c?Q (bcrypt hash)
insert into errands.users(username, password)
values
    ('admin', '$2a$12$n6KY4mpI2RdcBQWwyM4msudGmPD6gXxQDjKDQiXojmzykVYhtvaem');

insert into errands.roles(name)
values
    ('ROLE_ADMIN'),
    ('ROLE_MASTER'),
    ('ROLE_EMPLOYEE');

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