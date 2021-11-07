drop table if exists planet;
drop table if exists lord;

create table lord
(
    id   bigint       not null
        constraint lord_pkey
            primary key,
    age  integer      not null,
    name varchar(255) not null
        constraint uk5pagj2tgne27kuh0ho2i01ly2
            unique
);

alter table lord
    owner to postgres;