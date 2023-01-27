DROP TABLE IF EXISTS users;

CREATE TABLE users (
                       id int AUTO_INCREMENT primary key,
                       username varchar(255),
                       password varchar(11),
                       created_at date,
                       created_by varchar(32)
);


INSERT INTO users (username, created_at, created_by, password) VALUES ("Tatjana", now(), "CONSOLE", "pass1");
INSERT INTO users (username, created_at, created_by, password) VALUES ("Dimitri", now(), "CONSOLE", "pass2");
INSERT INTO users (username, created_at, created_by, password) VALUES ("Kollega A", now(), "CONSOLE", "pass3");
INSERT INTO users (username, created_at, created_by, password) VALUES ("Kollega B", now(), "CONSOLE", "pass4");
INSERT INTO users (username, created_at, created_by, password) VALUES ("Kollega C", now(), "CONSOLE", "pass5");