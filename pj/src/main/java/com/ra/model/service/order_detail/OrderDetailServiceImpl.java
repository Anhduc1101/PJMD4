package com.ra.model.service.order_detail;

import com.ra.model.dao.order_detail.OrderDetailDAO;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Order;
import com.ra.model.entity.OrderDetail;
import com.ra.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    @Autowired
    private OrderDetailDAO orderDetailDAO;
    @Override
    public List<OrderDetail> findAll() {
        return orderDetailDAO.findAll();
    }

    @Override
    public boolean saveOrUpdate(OrderDetail orderDetail) {
        return orderDetailDAO.saveOrUpdate(orderDetail);
    }

    @Override
    public OrderDetail findById(Integer integer) {
        return orderDetailDAO.findById(integer);
    }
    @Override
    public List<OrderDetail> findByName(String name) {
        return null;
    }

    @Override
    public List<OrderDetail> findListOrderDetailByOrderId(Integer id) {
        return orderDetailDAO.findListOrderDetailByOrderId(id);
    }
}
