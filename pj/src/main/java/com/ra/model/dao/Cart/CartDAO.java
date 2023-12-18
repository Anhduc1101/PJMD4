package com.ra.model.dao.cart;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Cart;
import com.ra.model.entity.User;

import java.util.List;

public interface CartDAO extends IGenericDAO<Cart,Integer> {


    Cart findCartByUserId(Integer id);

    Cart createCart(User user);

    void changeQuantity(Integer id);
//    void clearCart(Cart cart);
}
