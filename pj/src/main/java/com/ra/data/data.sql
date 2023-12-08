create database project_module_04;
use project_module_04;

create table category
(
    id     int auto_increment primary key,
    name   varchar(255) not null unique,
    status bit(1) default 1
);

delimiter //
create procedure proc_show_list_category()
begin
    select * from category;
end //

delimiter //
create procedure proc_add_new_category(in cName varchar(255), cStatus bit(1))
begin
    insert into category(name, status) values (cName, cStatus);
end //

delimiter //
create procedure proc_update_category(in cName varchar(255), cStatus bit(1), cId int)
begin
    update category set name=cName, status=cStatus where id = cId;
end //

delimiter //
create procedure proc_change_status_category(in cId int)
begin
    update category set status=status ^ 1 where id = cId;
end //

delimiter //
create procedure proc_find_category_by_name(in cName varchar(255))
begin
    select * from category where name = cName;
end //

delimiter //
create procedure proc_find_category_by_id(in cId int)
begin
    select * from category where id = cId;
end //

delimiter //
create procedure proc_sort_category()
begin
    select * from category order by name;
end //


create table user
(
    id       int auto_increment primary key,
    name     varchar(255) not null,
    email    varchar(255) not null unique,
    password varchar(255) not null,
    role     bit(1) default 1,
    status   bit(1) default 1
);

delimiter //
create procedure proc_show_list_user()
begin
    select * from user;
end //

delimiter //
create procedure proc_add_new_user(in uName varchar(255), uEmail varchar(255), uPassword varchar(255))
begin
    insert into user(name, email, password ) values (uName, uEmail, uPassword);
end //

drop procedure proc_add_new_user;

delimiter //
create procedure proc_update_user(in uName varchar(255), uEmail varchar(255), uPassword varchar(255),
                                  uId int)
begin
    update user set name=uName, email=uEmail, password=uPassword where id = uId;
end //

delimiter //
create procedure proc_change_status_user(in uId int)
begin
    update user set status=status ^ 1 where id = uId;
end //

delimiter //
create procedure proc_change_role_user(in uId int)
begin
    update user set role=role ^ 1 where id = uId;
end //

delimiter //
create procedure proc_find_user_by_name(in uName varchar(255))
begin
    select * from user where name = uName;
end //

delimiter //
create procedure proc_find_user_by_id(in uId int)
begin
    select * from user where id = uId;
end //

delimiter //
create procedure  proc_find_user_by_email(in uEmail varchar(255))
begin
    select * from user where email = uEmail;
end //

