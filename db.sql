create table auditorium
(
    id     int not null auto_increment primary key,
    number int NOT NULL unique
);

create table user
(
    id         int auto_increment,
    email      varchar(320) not null unique,
    password   varchar(30)  not null,
    first_name varchar(30)  not null,
    last_name  varchar(30)  not null,
    role       varchar(10)  not null
);

create table discussion
(
    id            int auto_increment primary key,
    topic         varchar(40),
    auditorium_id int not null references auditorium (id)
);

create table user_discussion
(
    user_id       int not null references user (id),
    discussion_id int not null references discussion (id),
    primary key (user_id, discussion_id)
);

insert into auditorium (number)
values (4);
insert into auditorium (number)
values (7);

insert into discussion (topic, auditorium_id)
values ('Topic 1', 1);
insert into discussion (topic, auditorium_id)
values ('Topic 2', 3);
insert into discussion (topic, auditorium_id)
values ('Topic 3', 1);
insert into discussion (topic, auditorium_id)
values ('Topic 4', 6);
insert into discussion (topic, auditorium_id)
values ('Topic 5', 6);
insert into discussion (topic, auditorium_id)
values ('Topic 6', 1);