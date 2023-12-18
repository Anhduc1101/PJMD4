package com.ra.model.dao.order;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Order;

import java.util.List;

public interface OrderDAO extends IGenericDAO<Order,Integer> {
    Order changeOrderStatus(Integer id);
    boolean createOrder(Order order, List<CartItem> cartItemList);
    void acceptOrDeny(Integer id,Integer status);

}
