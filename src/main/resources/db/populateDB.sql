DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2016-12-20 09:00','Завтрак', 500, 100000),
  ('2016-12-21 13:00','Обед', 1000, 100000),
  ('2016-12-22 09:00','Завтрак', 1000, 100001),
  ('2016-12-23 13:00','Обед', 1001, 100001);
