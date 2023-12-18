package com.ra.model.dao.cartItem;

import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;

import java.util.List;

public interface CartItemDAO {
void delete(Integer id);
boolean addOrUpdateCartItem(CartItem cartItem);
List<CartItem> getCartItem();
CartItem findById(Integer id);
List<CartItem> findCartItemByCartId(Cart cart);
void clearCartItem(Integer id);
}
