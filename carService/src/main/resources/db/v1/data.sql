INSERT INTO cars (brand, manufacturer, plate_number, production_date, vin)
VALUES ('BMW', 'BMW', 'C065MK78', '2018-01-01', '2C4GJ453XYR693697'),
       ('Peugeot', 'Groupe PSA', 'M013JR66', '2012-05-03', '9KA3F0N0Q0R99WPQM'),
       ('Chrysler', 'FCA', 'P032DB96', '2010-11-10', '4ZJ705FMROGAQ289S'),
       ('Dodge', 'FCA', 'B170MM196', '2020-03-26', '03JYXKPFBGMBHJ2QD'),
       ('Acura', 'Honda Motor', 'A401BB26', '2022-07-17', 'MH1SEB4LCN75YMGGW');

INSERT INTO parts (serial_number, car_id)
VALUES ('q1mpMw88GO', 1),
       ('Su6nyxJgLJ', 1),
       ('6C3hUKDPCc', 2),
       ('TIag2eAHgy', 3);

INSERT INTO mufflers (length, type, id)
VALUES
    ('25', 'resonant', 2),
    ('13', 'direct-flow', 4);

INSERT INTO engines (capacity, id)
VALUES
    ('240', 1),
    ('300', 3);