create table contact_relationship_type
(
    id           bigint       not null auto_increment
        primary key,
    description  varchar(255) null,
    date_created datetime(6)  null
);

create table employee
(
    id           bigint       not null auto_increment
        primary key,
    date_created datetime(6)  null,
    date_hired   datetime(6)  null,
    first_name   varchar(255) null,
    last_name    varchar(255) null,
    salary       double       null
);

create table contact_Info
(
    id                bigint       not null auto_increment
        primary key,
    phone_number      varchar(30)  null,
    email             varchar(200) null,
    date_created      date         null,
    employee_id       bigint       null,
    relationship_type bigint       null,
    constraint contact_Info_contact_relationship_type_id_fk
        foreign key (relationship_type) references contact_relationship_type (id),
    constraint contact_Info_employee_id_fk
        foreign key (employee_id) references employee (id)
);

create table hibernate_sequence
(
    next_val bigint null
);

