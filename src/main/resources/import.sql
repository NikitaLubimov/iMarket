-- DROP TABLE products IF EXISTS;
-- CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), cost int, PRIMARY KEY (id));
INSERT INTO products (title, cost) VALUES ('Milk', 810), ('Bread', 180), ('Cola', 500);

-- DROP TABLE users IF EXISTS;
-- CREATE TABLE IF NOT EXISTS users (id bigserial, firstname VARCHAR(255), PRIMARY KEY (id));
INSERT INTO users (firstname) VALUES ('Vova'), ('Sasha'), ('Misha');

-- DROP TABLE users_products IF EXISTS;
-- CREATE  TABLE IF NOT EXISTS users_products (user_id bigserial REFERENCES users (id), product_id bigserial REFERENCES products (id));
INSERT INTO users_products (user_id, product_id) VALUES (1,2), (1,3), (2,1),(2,3),(3,2),(3,1);
