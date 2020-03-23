DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS sessions CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS supplier CASCADE;
DROP TABLE IF EXISTS product_category CASCADE;
DROP TABLE IF EXISTS items CASCADE;

CREATE TABLE users(
    id SERIAL UNIQUE PRIMARY KEY NOT NULL,
    name VARCHAR,
    email VARCHAR,
    password VARCHAR

);

CREATE TABLE product(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR,
    description VARCHAR,
    default_price DECIMAL,
    supplier_id INTEGER,
    product_category_id INTEGER
);

CREATE TABLE supplier(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR,
    description VARCHAR
);

CREATE TABLE product_category(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR,
    description VARCHAR,
    department VARCHAR
);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier(id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_product_pro_category_id FOREIGN KEY (product_category_id) REFERENCES product_category(id);
