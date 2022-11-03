create table if not exists products (id bigserial primary key, title varchar(255), cost int);
insert into products (title, cost) values ('milk', 100), ('bread', 200), ('cola', 250);
