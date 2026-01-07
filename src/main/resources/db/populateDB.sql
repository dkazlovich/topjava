DELETE FROM user_role;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (userid, description,datetime,calories)
VALUES (100000, 'Подобед','2026-01-06 17:56:00', 1000),
       (100000, 'Завтрак','2026-01-07 17:57:00', 3000),
       (100001, 'Подужин','2026-01-06 17:58:00', 900);