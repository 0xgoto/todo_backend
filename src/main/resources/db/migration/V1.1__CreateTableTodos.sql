CREATE TABLE todos(
    id SERIAL PRIMARY KEY,
    title varchar(50) NOT NULL,
    completed boolean NOT NULL DEFAULT false
);