package com.ra.model.service.cartItem;

import com.ra.model.entity.CartItem;
import com.ra.model.service.IGenericService;

public interface CartItemService extends IGenericService<CartItem,Integer> {
    void deleteCartItem(Integer id);
}
