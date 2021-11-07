drop table if exists planet;

create table planet
(
    id      bigint       not null
        constraint planet_pkey
            primary key,
    name    varchar(255) not null
        constraint uk_dhelj2sd5e5spyo2flmdhxo6o
            unique,
    lord_id bigint
        constraint fkawlv3ynxrwxgdjh5cw51s6h54
            references lord
);

alter table planet
    owner to postgres;