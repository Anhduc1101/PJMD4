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
    phone    varchar(25),
    role     bit(1) default 1,
    status   bit(1) default 1
);
drop table user;
delimiter //
create procedure proc_show_list_user()
begin
    select * from user;
end //

delimiter //
create procedure proc_add_new_user(in uName varchar(255), uEmail varchar(255), uPassword varchar(255),
                                   uPhone varchar(25))
begin
    insert into user(name, email, password, phone) values (uName, uEmail, uPassword, uPhone);
end //

drop procedure proc_add_new_user;

delimiter //
create procedure proc_update_user(in uName varchar(255), uEmail varchar(255), uPassword varchar(255),
                                  uPhone varchar(25),
                                  uId int)
begin
    update user set name=uName, email=uEmail, password=uPassword, phone=uPhone where id = uId;
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
create procedure proc_find_user_by_email(in uEmail varchar(255))
begin
    select * from user where email = uEmail;
end //

create table product
(
    id          int auto_increment primary key,
    img         varchar(255),
    name        varchar(255) not null unique,
    category_id int,
    foreign key (category_id) references category (id),
    description varchar(255),
    price       double,
    stock       int,
    status      bit(1) default 1
);
drop table product;
delimiter //
create procedure proc_show_list_product()
begin
    select * from product;
end //

delimiter //
create procedure proc_add_new_product(in pImg varchar(255), pName varchar(255), pCatId int, des varchar(255),
                                      pPrice double, pStock int, pStatus bit(1))
begin
    insert into product(img, name, category_id, description, price, stock, status)
    values (pImg, pName, pCatId, des, pPrice, pStock, pStatus);
end //

delimiter //
create procedure proc_update_product(in pImg varchar(255), pName varchar(255), pCatId int, des varchar(255),
                                     pPrice double, pStock int,
                                     pStatus bit(1), pId int)
begin
    update product
    set img=pImg,
        name=pName,
        category_id=pCatId,
        description=des,
        price=pPrice,
        stock=pStock,
        status=pStatus
    where id = pId;
end //

delimiter //
create procedure proc_change_status_product(in pId int)
begin
    update product set status=status ^ 1 where id = pId;
end //

delimiter //
create procedure proc_find_product_by_name(in pName varchar(255))
begin
    select * from product where name = pName;
end //

delimiter //
create procedure proc_find_product_by_id(in pId int)
begin
    select * from category where id = pId;
end //

delimiter //
create procedure proc_sort_product()
begin
    select * from product order by name;
end //

delimiter //
create procedure proc_delete_product(in pId int)
begin
    delete from product where id = pId;
end //

create table cart
(
    id     int primary key auto_increment,
    userId int,
    foreign key (userId) references user (id)
);

delimiter //
create procedure proc_show_list_cart()
begin
    select * from cart;
end //

delimiter //
create procedure proc_find_cart_by_id(in cartId int)
begin
    select * from cart where id = cartId;
end //

delimiter //
create procedure proc_delete_cart(in cart_id int)
begin
    delete from cart where id = cart_id;
end //

delimiter //
create procedure proc_add_to_cart(in cartUserId int)
begin
    insert into cart(userId) values (cartUserId);
end //

delimiter //
create procedure proc_update_cart(in cartUserId int, cartId int)
begin
    update cart set userId=cartUserId where id = cartId;
end //

delimiter //
create procedure proc_find_cart_by_user_id(in user_id int)
begin
    select * from cart where userId = user_id;
end //

delimiter //
create procedure proc_clear_cart(in cartId int)
begin
    delete from cart where id = cartId;
end //

create table cart_item
(
    id       int auto_increment primary key,
    proId    int,
    foreign key (proId) references product (id),
    cartId   int,
    foreign key (cartId) references cart (id),
    quantity int
);

drop table cart_item;

delimiter //
create procedure proc_delete_cart_item(in cartItems_id int)
begin
    delete from cart_item where id = cartItems_id;
end //

delimiter //
create procedure proc_add_to_cart_item(in cItemProId int, cItemCartId int, cItemQuantity int)
begin
    insert into cart_item(proId, cartId, quantity) values (cItemProId, cItemCartId, cItemQuantity);
end //

delimiter //
create procedure proc_update_cart_item(in cItemProId int, cItemCartId int, cItemQuantity int, cItemId int)
begin
    update cart_item set proId=cItemProId, cartId=cItemCartId, quantity=cItemQuantity where id = cItemId;
end //

delimiter //
create procedure proc_get_cart_item()
begin
    select * from cart_item;
end //

delimiter //
create procedure proc_find_cart_item_by_id(in cItemId int)
begin
    select * from cart_item where id = cItemId;
end //

delimiter //
create procedure proc_clear_cart_item(in cItemId int)
begin
    delete from cart_item where cartId = cItemId;
end //

delimiter //
create procedure proc_find_cart_item_by_cart_id(in cart_id int)
begin
    select * from cart_item where cartId = cart_id;
end //

delimiter //
create procedure proc_change_product_quantity(in qty int)
begin
    update cart_item set quantity = qty;
end //

create table orders
(
    id        int auto_increment primary key,
    user_id   int,
    foreign key (user_id) references user (id),
    status    int      default 1,
    address   varchar(255),
    create_at datetime default (current_timestamp)
);
drop table orders;
delimiter //
create procedure proc_show_list_order()
begin
    select * from orders;
end //

delimiter //
create procedure proc_add_new_order(in oUserId int, oStatus int, oAddress varchar(255), oCreateAt date)
begin
    insert into orders(user_id, status, address, create_at) values (oUserId, oStatus, oAddress, oCreateAt);
end //

delimiter //
create procedure proc_update_order(in oUserId int, oStatus int, oAddress varchar(255), oCreateAt date, oId int)
begin
    update orders set user_id=oUserId, status=oStatus, address=oAddress, create_at=oCreateAt where id = oId;
end //

delimiter //
create procedure proc_change_orders_status(in oStatus int, oId int)
begin
    update orders set status=oStatus where id = oId;
end //

delimiter //
create procedure proc_find_orders_by_orders_id(in oId int)
begin
    select * from orders where id = oId;
end //

delimiter //
create procedure proc_create_orders(in oUserId int, oStatus int, oAddress varchar(255), out oId int)
begin
    insert into orders(user_id, status, address) values (oUserId, oStatus, oAddress);
    set oId = LAST_INSERT_ID();
end //



DELIMITER //

CREATE PROCEDURE proc_accept_or_deny_order(
    IN oId INT,
    IN _status int
)
BEGIN

        UPDATE orders SET status = _status WHERE id = oId;

END //

DELIMITER ;

create table order_detail
(
    id         int auto_increment primary key,
    order_id   int,
    foreign key (order_id) references orders (id),
    product_id int,
    foreign key (product_id) references product (id),
    quantity int
);
drop table order_detail;
delimiter //
create procedure proc_add_new_order_detail(in odOrderId int, odProId int,qty int)
begin
    insert into order_detail(order_id, product_id,quantity) values (odOrderId, odProId,qty);
end //

delimiter //
create procedure proc_update_order_detail(in odOrderId int, odProId int,qty int, odId int)
begin
    update order_detail set order_id=odOrderId, product_id=odProId,quantity=qty where id = odId;
end //


delimiter //
create procedure proc_find_order_detail_by_order_detail_id(in odId int)
begin
    select * from orders where id = odId;
end //

delimiter //
create procedure proc_show_list_order_detail()
begin
    select * from order_detail ;
end //