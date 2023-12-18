package com.ra.model.service.order;

import com.ra.model.dao.order.OrderDAO;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDAO orderDAO;
    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public boolean saveOrUpdate(Order order) {
        return orderDAO.saveOrUpdate(order);
    }

    @Override
    public Order findById(Integer integer) {
        return orderDAO.findById(integer);
    }

    @Override
    public List<Order> findByName(String name) {
        return null;
    }

    @Override
    public Order changeStatusOrder(Integer id) {
        return orderDAO.changeOrderStatus(id);
    }

    @Override
    public boolean createOrder(Order order, List<CartItem> cartItemList) {
        return orderDAO.createOrder(order,cartItemList);
    }

    @Override
    public void acceptOrDeny(Integer id, Integer status) {
        orderDAO.acceptOrDeny(id,status);
    }
}
