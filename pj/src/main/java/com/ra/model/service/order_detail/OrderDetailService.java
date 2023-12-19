package com.ra.model.service.order_detail;

import com.ra.model.entity.CartItem;
import com.ra.model.entity.Order;
import com.ra.model.entity.OrderDetail;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface OrderDetailService extends IGenericService<OrderDetail,Integer> {

    List<OrderDetail> findListOrderDetailByOrderId(Integer id);

}
