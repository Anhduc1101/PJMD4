package com.ra.model.dao.order_detail;

import com.ra.model.entity.OrderDetail;
import com.ra.model.service.order.OrderService;
import com.ra.model.service.product.ProductService;
import com.ra.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Override
    public List<OrderDetail> findAll() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_show_list_order_detail()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrder(orderService.findById(rs.getInt("order_id")));
                orderDetail.setProduct(productService.findById(rs.getInt("product_id")));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
        return orderDetails;
    }

    @Override
    public boolean saveOrUpdate(OrderDetail orderDetail) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            if (orderDetail.getId()==0){
            cs= con.prepareCall("call proc_add_new_order_detail(?,?,?)");
            cs.setInt(1,orderDetail.getOrder().getId());
            cs.setInt(2,orderDetail.getProduct().getProductId());
            cs.setInt(3,orderDetail.getQuantity());
            }else {
                cs=con.prepareCall("call proc_update_order_detail(?,?,?,?)");
                cs.setInt(1,orderDetail.getOrder().getId());
                cs.setInt(2,orderDetail.getProduct().getProductId());
                cs.setInt(3,orderDetail.getQuantity());
                cs.setInt(4,orderDetail.getId());
            }
            check =cs.executeUpdate();
            if (check>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
        return false;
    }

    @Override
    public OrderDetail findById(Integer integer) {
        Connection con = ConnectionDB.openCon();
        OrderDetail orderDetail = new OrderDetail();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_order_detail_by_order_detail_id(?)");
            cs.setInt(1, integer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrder(orderService.findById(rs.getInt("order_id")));
                orderDetail.setProduct(productService.findById(rs.getInt("product_id")));
                orderDetail.setQuantity(rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return orderDetail;
    }

    @Override
    public List<OrderDetail> findByName(String name) {
        return null;
    }

    @Override
    public List<OrderDetail> findListOrderDetailByOrderId(Integer id) {
        Connection con = ConnectionDB.openCon();
        List<OrderDetail> orderDetails=new ArrayList<>();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_list_order_detail_by_order_id(?)");
            cs.setInt(1,id);
            ResultSet rs=cs.executeQuery();
            while (rs.next()){
                OrderDetail orderDetail=new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrder(orderService.findById(rs.getInt("order_id")));
                orderDetail.setProduct(productService.findById(rs.getInt("product_id")));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
        return orderDetails;
    }
}
