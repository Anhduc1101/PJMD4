package com.ra.model.dao.Cart;

import com.ra.model.entity.Cart;
import com.ra.model.entity.User;

import java.util.List;

public interface CartDAO {
    List<Cart> findAll();
    boolean saveOrUpdate(Cart cart);
    void delete(Integer id);
    Cart findById(Integer id);
    Cart findCartByUserId(Integer id);
Cart createCart(User user);
}
