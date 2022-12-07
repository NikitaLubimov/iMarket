create table if not exists products (id bigserial primary key, title varchar(255), cost int);
insert into products (title, cost) values ('milk', 100), ('bread', 200), ('cola', 250), ('pivko',150),('pepsi',230),('apple',78),('banan',160),('kit-kat',50),('orange',135);
create table if not exists users (id bigserial primary key, firstname varchar(255));
insert into users (firstname) values ('Mike'), ('Bred'), ('Jon');


