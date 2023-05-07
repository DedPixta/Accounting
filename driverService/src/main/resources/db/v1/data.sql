INSERT INTO drivers(full_name, date_of_birth, passport)
VALUES ('John Doe', '1990-01-01', '1234567890'),
       ('Michael Derek', '1976-05-02', '4504234123'),
       ('Simon Falcon', '1963-11-15', '3415266443'),
       ('Salma Lams', '1983-07-18', '6105455677'),
       ('Helma Folmes', '1997-09-13', '4006741852');

INSERT INTO accounts(balance, driver_id)
VALUES (-15000, 1),
       (15000, 2),
       (100000, 3),
       (0, 4),
       (-23400, 5);

INSERT INTO categories(category, driver_id)
VALUES ('A', 1),
       ('B', 1),
       ('C', 2),
       ('B', 3),
       ('D', 5);

INSERT INTO cars(id, driver_id)
VALUES (1, 1),
       (2, 1),
       (3, 2);