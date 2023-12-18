package com.ra.model.service.order;

import com.ra.model.entity.CartItem;
import com.ra.model.entity.Order;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface OrderService extends IGenericService<Order,Integer> {
    Order changeStatusOrder(Integer id);
    boolean createOrder(Order order, List<CartItem> cartItemList);
    void acceptOrDeny(Integer id, Integer status);
}
