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
    cost int
    );

insert into products (title, cost)
values ('milk', 100),
       ('bread', 200),
       ('cola', 250),
       ('pivko', 150),
       ('pepsi', 230),
       ('apple', 78),
       ('banan', 160),
       ('kit-kat', 50),
       ('orange', 135);

create table orders
(
    id          bigserial primary key,
    user_name   varchar(255) not null,
    total_price int    not null,
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
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
)


