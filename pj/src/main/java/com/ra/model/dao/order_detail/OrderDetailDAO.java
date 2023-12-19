package com.ra.model.dao.order_detail;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO extends IGenericDAO<OrderDetail,Integer> {
    List<OrderDetail> findListOrderDetailByOrderId(Integer id);
}
