package com.ra.model.service.cart;

import com.ra.model.entity.Cart;
import com.ra.model.entity.User;
import com.ra.model.service.IGenericService;

public interface CartService extends IGenericService<Cart,Integer> {
    Cart findCartByUserId(Integer id);
    Cart createCart(User user);

}
