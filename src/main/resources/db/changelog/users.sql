--liquibase formatted sql

CREATE SEQUENCE user_id_sequence
START WITH 1
INCREMENT BY 1;

CREATE TABLE users (
    id INT PRIMARY KEY DEFAULT NEXTVAL('user_id_sequence'),
    name VARCHAR(250) NOT NULL,
    lastName VARCHAR(250) NOT NULL,
    secondName VARCHAR(250) NOT NULL
);