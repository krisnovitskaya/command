--Default(Test) database H2 creation

create table if not exists users(
    id          bigserial primary key,
    username    varchar(100) not null,
    password    varchar(100) not null,
    created     timestamp default current_timestamp,
    updated     timestamp default current_timestamp
);

create table if not exists roles(
    id          bigserial primary key,
    name        varchar(30) not null,
    created     timestamp default current_timestamp,
    updated     timestamp default current_timestamp
);

create table if not exists users_roles(
    user_id               bigint not null,
    role_id               bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table if not exists positions(
    id 		bigserial primary key,
    position	varchar(255),
    created     timestamp default current_timestamp,
    updated     timestamp default current_timestamp
);

create table if not exists departments(
    id                      bigserial primary key,
    title                   varchar(255) not null,
    master_id               bigint,
    master_department_id    bigint,
    created                 timestamp default current_timestamp,
    updated                 timestamp default current_timestamp,
    foreign key (master_department_id) references departments (id)
);

create table if not exists employees(
    id              	bigserial primary key,
    first_name      	varchar(100) not null,
    middle_name     	varchar(100),
    last_name       	varchar(100) not null,
    position_id     	bigint not null,
    department_id   	bigint not null,
    user_id         	bigint not null,
    created             timestamp default current_timestamp,
    updated             timestamp default current_timestamp,
    foreign key (position_id) references positions (id),
    foreign key (department_id) references departments (id),
    foreign key (user_id) references users (id)
);

create table if not exists employees_details(
    id      	bigserial primary key,
    mail	varchar(100),
    employee_id bigint not null,
    created     timestamp default current_timestamp,
    updated     timestamp default current_timestamp,
    foreign key (employee_id) references employees (id)
);

alter table departments add constraint  fk_master_id foreign key (master_id) references employees (id);

create table if not exists errands_status_types(
    id      bigserial primary key,
    status  varchar(100),
    created timestamp default current_timestamp,
    updated timestamp default current_timestamp
);

create table if not exists errands_matter_types(
    id      bigserial primary key,
    matter  varchar(100),
    created timestamp default current_timestamp,
    updated timestamp default current_timestamp
);

create table if not exists places_types(
    id       bigserial primary key,
    type     varchar(100),
    created  timestamp default current_timestamp,
    updated  timestamp default current_timestamp
);


create table if not exists places(
    id              bigserial primary key,
    place_type_id   bigint not null,
    title           varchar(255),
    created         timestamp default current_timestamp,
    updated         timestamp default current_timestamp,
    foreign key (place_type_id) references places_types(id)
);

create table if not exists errands_details(
    id				bigserial primary key,
    matter_id                   bigint not null,
    place_id                    bigint not null,
    comment                     varchar(255),
    created_by                  bigint not null,
    confirmed_or_rejected_by    bigint not null,
    created                     timestamp default current_timestamp,
    updated                     timestamp default current_timestamp,
    foreign key (matter_id) references errands_matter_types(id),
    foreign key (place_id)  references places(id),
    foreign key (created_by) references employees(id),
    foreign key (confirmed_or_rejected_by)  references employees(id)
);

create table if not exists errands(
    id                  bigserial primary key,
    status_id           bigint,
    employee_id         bigint,
    errand_details_id    bigint,
    date_start          timestamp not null,
    date_end            timestamp,
    created             timestamp default current_timestamp,
    updated             timestamp default current_timestamp,
    deleted             boolean default 0,
    foreign key (status_id) references errands_status_types(id),
    foreign key (employee_id) references employees(id),
    foreign key (errand_details_id) references errands_details(id)
);

create table if not exists files(
    id                      bigserial primary key,
    file_name               varchar(255),
    file_data               clob,
    author_id               bigint not null,
    errand_id               bigint not null,
    created                 timestamp default current_timestamp,
    updated                 timestamp default current_timestamp,
    deleted                 boolean default 0,
    foreign key (author_id)        references users(id),
    foreign key (errand_id)     references errands(id)
);