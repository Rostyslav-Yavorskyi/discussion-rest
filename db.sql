create table auditorium (
    id int not null auto_increment,
    number int NOT NULL,
    primary key (id),
    unique key unique_constraint_number (number)
);

create table user (
    id int not null auto_increment,
    email varchar(320) not null,
    password varchar(30) not null,
    first_name varchar(30),
    last_name varchar(30),
    role varchar(10),
    primary key (id),
    unique key (email)
);

insert into auditorium (number) values(4);
insert into auditorium (number) values(7);
