-- для гайда юзаем постгре

CREATE TABLE country (
    code char(3) NOT NULL,
    name text NOT NULL,
    population integer NOT NULL,
    gnp numeric(10, 2)
);

CREATE TABLE world (
    name varchar(50),
    continent varchar(50),
    population integer
);