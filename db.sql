create table auditorium (
    id int not null AUTO_INCREMENT,
    number int NOT NULL,
    primary key (id),
    unique key unique_constraint_number (number)
);

insert into auditorium (number) values(4);
insert into auditorium (number) values(7);
