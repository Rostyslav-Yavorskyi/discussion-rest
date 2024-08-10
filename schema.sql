CREATE DATABASE IF NOT EXISTS discussion;
USE discussion;

CREATE TABLE IF NOT EXISTS auditorium
(
    id     INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    number INT UNSIGNED NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user
(
    id         INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(320) NOT NULL UNIQUE,
    password   VARCHAR(30)  NOT NULL,
    first_name VARCHAR(30)  NOT NULL,
    last_name  VARCHAR(30)  NOT NULL,
    role       ENUM('ADMIN', 'USER')
);

CREATE TABLE IF NOT EXISTS discussion
(
    id            INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    topic         VARCHAR(40),
    auditorium_id INT UNSIGNED NOT NULL REFERENCES auditorium (id)
);

CREATE TABLE IF NOT EXISTS user_discussion
(
    user_id       INT UNSIGNED NOT NULL REFERENCES user (id),
    discussion_id INT UNSIGNED NOT NULL REFERENCES discussion (id),
    PRIMARY KEY (user_id, discussion_id)
);

INSERT INTO user (email, password, first_name, last_name, role) VALUES ('admin@gmail.com', 'adminpass', 'Admin', 'Admin', 'ADMIN');