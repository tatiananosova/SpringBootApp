drop table if exists hiber.product cascade;

create table hiber.product
(
    id serial not null,
    title varchar not null,
    cost int,
    deleted bool,
    version int not null
);

create unique index product_id_uindex
	on hiber.product (id);

create unique index product_title_uindex
	on hiber.product (title);

alter table hiber.product
    add constraint product_pk
        primary key (id);

insert into hiber.product (title, cost, deleted, version) values
                                                                 ('Potato',4,false,1),
                                                                 ('Tomato',5,false,1),
                                                                 ('Sweet Potato',6,false,1),
                                                                 ('Cucumber',7,false,1),
                                                                 ('Radish',8,false,1),
                                                                 ('Pepper',9,false,1),
                                                                 ('Pepper Bell',10,false,1),
                                                                 ('Horse Radish',1,false,1),
                                                                 ('Zucchini',2,false,1),
                                                                 ('Pumpkin',3,true,3);

drop table if exists hiber.customer cascade;

create table hiber.customer
(
    id serial not null,
    name varchar not null
);

create unique index customer_id_uindex
    on hiber.customer (id);

create unique index customer_name_uindex
    on hiber.customer (name);

alter table hiber.customer
    add constraint customer_pk
        primary key (id);

insert into hiber.customer (name) values ('Sasha'), ('Vasya'), ('Misha'), ('Petya');

drop table if exists hiber.customer_product cascade;

create table hiber.customer_product
(
    customer_id int not null
        constraint customer__fk
            references hiber.customer,
    product_id int not null
        constraint product__fk
            references hiber.product
);