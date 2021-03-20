--Prod database creation
create schema if not exists errands;

create table errands.users(
    id                      bigint primary key auto_increment,
    username                varchar(100) not null,
    password                varchar(100) not null
);

create table errands.roles(
    id                      bigint primary key auto_increment,
    role_name               varchar(30) not null
);

create table errands.users_roles(
    user_id               bigint not null,
    role_id               bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references errands.users(id),
    foreign key (role_id) references errands.roles(id)
);

create table errands.positions(
	id 			bigint primary key auto_increment,
	position	varchar(255)
);

create table errands.departments(
    id                          bigint primary key auto_increment,
    title                       varchar(255) not null,
    master_id                   bigint,
    master_department_id        bigint,
    foreign key (master_department_id) references errands.departments(id)
);


create table errands.employees_details(
	id              			bigint primary key auto_increment,
	mail						varchar(100)
);

create table errands.employees(
    id              	bigint primary key auto_increment,
    last_name       	varchar(100) not null,
    first_name      	varchar(100) not null,
    middle_name     	varchar(100),
    position_id     	bigint not null,
    department_id   	bigint not null,
    user_id         	bigint not null,
    employee_details_id	bigint not null,
    foreign key (position_id) references errands.positions(id),
    foreign key (department_id) references errands.departments(id),
    foreign key (user_id) references errands.users(id),
    foreign key (employee_details_id) references errands.employees_details(id)
);

alter table errands.departments add constraint  fk_master_id foreign key (master_id) references errands.employees (id);

create table errands.errands_status_types(
    id       bigint primary key auto_increment,
    status   varchar(100)
);

create table errands.errands_matter_types(
    id       bigint primary key auto_increment,
    matter   varchar(100)
);

create table errands.place_types(
    id       bigint primary key auto_increment,
    type     varchar(100)
);


create table errands.places(
    id                  	bigint primary key auto_increment,
    place_type_id       	bigint not null,
    title               	varchar(255),
    foreign key (place_type_id) references errands.place_types(id)
);

create table errands.errands_details(
    id							bigint primary key auto_increment,
    matter_id                   bigint not null,
    place_id                    bigint not null,
    comment                     varchar(255),
    created_by                  bigint not null,
    confirmed_or_rejected_by    bigint not null,
    created_at                  timestamp not null,
    foreign key (matter_id) references errands.errands_matter_types(id),
    foreign key (place_id)  references errands.places(id),
    foreign key (created_by) references errands.employees(id),
    foreign key (confirmed_or_rejected_by)  references errands.employees(id)
);

create table errands.errands(
    id                          bigint primary key auto_increment,
    status_id                   bigint not null,
    employee_id                 bigint not null,
    errands_details_id			bigint not null,
    date_start                  timestamp not null,
    date_end                    timestamp,
    foreign key (status_id) references errands.errands_status_types(id),
    foreign key (employee_id) references errands.employees(id), 
    foreign key (errands_details_id) references errands.errands_details(id)
);