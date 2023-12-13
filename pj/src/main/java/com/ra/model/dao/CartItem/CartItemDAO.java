package com.ra.model.dao.CartItem;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Product;

import java.util.List;

public interface CartItemDAO {
void delete(Integer id);
boolean addOrUpdateCartItem(CartItem cartItem);
List<CartItem> getCartItem();
CartItem findById(Integer id);
}
