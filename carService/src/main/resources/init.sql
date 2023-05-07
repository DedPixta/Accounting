DROP TABLE IF EXISTS cars CASCADE;
DROP TABLE IF EXISTS parts CASCADE;
DROP TABLE IF EXISTS engines;
DROP TABLE IF EXISTS mufflers;

CREATE TABLE cars
(
    id              bigserial,
    brand           varchar(255),
    manufacturer    varchar(255),
    plate_number    varchar(255) NOT NULL,
    production_date date,
    vin             varchar(17)  NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_cars_plate_number
        UNIQUE (plate_number),
    CONSTRAINT uk_cars_vin
        UNIQUE (vin)
);

CREATE TABLE parts
(
    id            bigserial,
    serial_number varchar(255) NOT NULL,
    car_id        bigint,
    PRIMARY KEY (id),
    CONSTRAINT uk_parts_serial_number
        UNIQUE (serial_number),
    CONSTRAINT fk_parts_car_id
        FOREIGN KEY (car_id) REFERENCES cars ON DELETE CASCADE
);

CREATE TABLE engines
(
    capacity int,
    id              bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_engines_id
        FOREIGN KEY (id) REFERENCES parts ON DELETE CASCADE
);

CREATE TABLE mufflers
(
    length int,
    type varchar(255),
    id            bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_mufflers_id
        FOREIGN KEY (id) REFERENCES parts ON DELETE CASCADE
);

