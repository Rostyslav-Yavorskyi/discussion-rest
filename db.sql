create table auditorium (
    id int not null auto_increment,
    number int NOT NULL,
    primary key (id),
    unique key (number)
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

create table discussion (
    id int not null auto_increment,
    topic varchar(40),
    auditorium_id int not null,
    primary key (id),
    foreign key (auditorium_id) references auditorium(id)
);

insert into auditorium (number) values(4);
insert into auditorium (number) values(7);

insert into discussion (topic, auditorium_id) values ('Topic 1', 1);
insert into discussion (topic, auditorium_id) values ('Topic 2', 3);
insert into discussion (topic, auditorium_id) values ('Topic 3', 1);
insert into discussion (topic, auditorium_id) values ('Topic 4', 6);
insert into discussion (topic, auditorium_id) values ('Topic 5', 6);
insert into discussion (topic, auditorium_id) values ('Topic 6', 1);