create sequence carta_seq start 1 increment 1;
create sequence role_seq start 1 increment 1;
create sequence user_all_seq start 1 increment 1;
create table card (id int8 not null, titulo varchar(255), aprovado boolean not null, data_criacao timestamp, imagem BYTEA, publico boolean not null, thumb BYTEA, views int4, primary key (id));
create table role (id int8 not null, description varchar(255), role_name varchar(255), primary key (id));
create table user_all (id int8 not null, email varchar(255), favorite_meme varchar(255), name varchar(255), password varchar(255), primary key (id));
create table user_all_role (user_all_id int8 not null, role_id int8 not null);
alter table user_all add constraint UK_myu9ilf2vosesn25g0x2j4dae unique (email);
alter table user_all_role add constraint FKramtuja22yq9adt7fywr9xqdp foreign key (role_id) references role;
alter table user_all_role add constraint FKbe1h09e8glmp06fwy6s6h3x1g foreign key (user_all_id) references user_all;


insert into role values (nextval('role_seq'),'administrador','ROLE_ADMIN');
insert into role values (nextval('role_seq'),'visitante','ROLE_GUEST');