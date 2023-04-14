create table if not exists products
(
    id
    bigserial
    primary
    key,
    title
    varchar
(
    255
),
    cost numeric(8,2)
    );

insert into products (title, cost)
values ('milk', 100.00),
       ('bread', 200.00),
       ('cola', 250.00),
       ('pivko', 150.00),
       ('pepsi', 230.00),
       ('apple', 78.00),
       ('banan', 160.00),
       ('kit-kat', 50.00),
       ('orange', 135.00);

create table orders
(
    id          bigserial primary key,
    user_name   varchar(255) not null,
    total_price numeric(8,2),
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product numeric(8,2)    not null,
    price             numeric(8,2)    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
)


