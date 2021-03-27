--default database creation
create table users(
    id          bigserial primary key,
    username    varchar(100) not null,
    password    varchar(100) not null,
    created     timestamp default current_timestamp,
    updated     timestamp default current_timestamp
);

create table roles(
    id      bigserial primary key,
    name    varchar(30) not null,
    created     timestamp default current_timestamp,
    updated     timestamp default current_timestamp
);

create table users_roles(
    user_id               bigint not null,
    role_id               bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table positions(
    id 		bigserial primary key,
    position	varchar(255),
    created     timestamp default current_timestamp,
    updated     timestamp default current_timestamp
);

create table departments(
    id                      bigserial primary key,
    title                   varchar(255) not null,
    master_id               bigint,
    master_department_id    bigint,
    created                 timestamp default current_timestamp,
    updated                 timestamp default current_timestamp,
    foreign key (master_department_id) references departments (id)
);

create table employees(
    id              	bigserial primary key,
    last_name       	varchar(100) not null,
    first_name      	varchar(100) not null,
    middle_name     	varchar(100),
    position_id     	bigint not null,
    department_id   	bigint not null,
    user_id         	bigint not null,
    created     timestamp default current_timestamp,
    updated     timestamp default current_timestamp,
    foreign key (position_id) references positions (id),
    foreign key (department_id) references departments (id),
    foreign key (user_id) references users (id)
);

create table employees_details(
    id      	bigserial primary key,
    mail	    varchar(100),
    employee_id bigint not null,
    created     timestamp default current_timestamp,
    updated     timestamp default current_timestamp,
    foreign key (employee_id) references employees (id)
);

alter table departments add constraint  fk_master_id foreign key (master_id) references employees (id);

create table errands_status_types(
    id      bigserial primary key,
    status  varchar(100),
    created timestamp default current_timestamp,
    updated timestamp default current_timestamp
);

create table errands_matter_types(
    id      bigserial primary key,
    matter  varchar(100),
    created timestamp default current_timestamp,
    updated timestamp default current_timestamp
);

create table place_types(
    id       bigserial primary key,
    type     varchar(100),
    created  timestamp default current_timestamp,
    updated  timestamp default current_timestamp
);


create table places(
    id              bigserial primary key,
    place_type_id   bigint not null,
    title           varchar(255),
    created         timestamp default current_timestamp,
    updated         timestamp default current_timestamp,
    foreign key (place_type_id) references place_types(id)
);


create table errands(
    id                  bigserial primary key,
    status_id           bigint not null,
    employee_id         bigint not null,
    date_start          timestamp not null,
    date_end            timestamp,
    created             timestamp default current_timestamp,
    updated             timestamp default current_timestamp,
    foreign key (status_id) references errands_status_types(id),
    foreign key (employee_id) references employees(id)
);

create table errands_details(
    id				            bigserial primary key,
    matter_id                   bigint not null,
    place_id                    bigint not null,
    comment                     varchar(255),
    created_by                  bigint not null,
    confirmed_or_rejected_by    bigint not null,
    created                     timestamp default current_timestamp,
    updated                     timestamp default current_timestamp,
    errand_id                   bigint not null,
    foreign key (matter_id) references errands_matter_types(id),
    foreign key (place_id)  references places(id),
    foreign key (created_by) references employees(id),
    foreign key (confirmed_or_rejected_by)  references employees(id),
    foreign key (errand_id) references errands (id)
);