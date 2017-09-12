drop table users if exists;

create table users (
  id int,
  name varchar(20),
  city varchar(20)
);

insert into users (id, name, city) values(1, 'jim', 'zhuhai');
insert into users (id, name, city) values(1, 'jim', 'guangzhou');