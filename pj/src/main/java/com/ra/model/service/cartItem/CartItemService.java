package com.ra.model.service.cartItem;

import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface CartItemService extends IGenericService<CartItem,Integer> {
    void deleteCartItem(Integer id);
    List<CartItem> findCartItemByCart(Cart cart);
    void changeQuantity(Integer id);
    void clearCartItem(Integer id);
}
