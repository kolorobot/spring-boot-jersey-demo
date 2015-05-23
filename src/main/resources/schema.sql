DROP TABLE customer IF EXISTS;

CREATE TABLE customer (
  id        BIGINT IDENTITY PRIMARY KEY,
  firstname VARCHAR(30),
  lastname  VARCHAR(30),
  email     VARCHAR(30),
  UNIQUE (email)
);