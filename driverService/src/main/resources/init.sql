DROP TABLE IF EXISTS cars CASCADE;
DROP TABLE IF EXISTS drivers CASCADE;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS categories;
DROP TYPE IF EXISTS dl_category;

CREATE TABLE drivers
(
    id            bigserial,
    full_name     varchar(255) NOT NULL,
    date_of_birth date         NOT NULL,
    passport      varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cars
(
    id    bigint,
    driver_id bigint,
    PRIMARY KEY (id),
    CONSTRAINT fk_cars_driver_id
        FOREIGN KEY (id) REFERENCES drivers ON DELETE CASCADE
);

CREATE TABLE accounts
(
    id        bigserial,
    balance   decimal(15, 2),
    driver_id bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_accounts_driver_id
        UNIQUE (driver_id),
    CONSTRAINT fk_accounts_driver_id
        FOREIGN KEY (id) REFERENCES drivers ON DELETE CASCADE
);

CREATE TYPE dl_category AS ENUM ('A', 'B', 'C', 'D');

CREATE TABLE categories
(
    id       bigserial,
    category dl_category,
    driver_id   bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_categories_driver_id
        FOREIGN KEY (id) REFERENCES drivers ON DELETE CASCADE
);


