package com.ra.model.dao.order;

import com.ra.model.entity.CartItem;
import com.ra.model.entity.Order;
import com.ra.model.entity.User;
import com.ra.model.service.user.UserService;
import com.ra.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    private UserService userService;

    @Override
    public List<Order> findAll() {
        Connection con = ConnectionDB.openCon();
        List<Order> orders = new ArrayList<>();
        try {
            CallableStatement cs = con.prepareCall("call proc_show_list_order()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUser(userService.findById(rs.getInt("user_id")));
                order.setStatus(rs.getInt("status"));
                order.setAddress(rs.getString("address"));
                order.setCreate_at(rs.getDate("create_at"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return orders;
    }

    @Override
    public boolean saveOrUpdate(Order order) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            if (order.getId() == 0) {
                cs = con.prepareCall("call proc_add_new_order(?,?,?,?)");
                cs.setInt(1, order.getUser().getUserId());
                cs.setInt(2, order.getStatus());
                cs.setString(3, order.getAddress());
                cs.setDate(4, order.getCreate_at());
            } else {
                cs = con.prepareCall("call proc_update_order(?,?,?,?,?)");
                cs.setInt(1, order.getUser().getUserId());
                cs.setInt(2, order.getStatus());
                cs.setString(3, order.getAddress());
                cs.setDate(4, order.getCreate_at());
                cs.setInt(5, order.getId());
            }
            check = cs.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return false;
    }

    @Override
    public Order findById(Integer integer) {
        Connection con = ConnectionDB.openCon();
        Order order = new Order();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_orders_by_orders_id(?)");
            cs.setInt(1, integer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                order.setId(rs.getInt("id"));
                order.setUser(userService.findById(rs.getInt("id")));
                order.setStatus(rs.getInt("status"));
                order.setAddress(rs.getString("address"));
                order.setCreate_at(rs.getDate("create_at"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return order;
    }

    @Override
    public List<Order> findByName(String name) {
        return null;
    }

    @Override
    public Order changeOrderStatus(Integer id) {
        Connection con = ConnectionDB.openCon();
        Order order = new Order();
        try {
            CallableStatement cs = con.prepareCall("call proc_change_orders_status(?,?)");
            cs.setInt(1, order.getStatus());
            cs.setInt(2, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return order;
    }

    @Override
    public boolean createOrder(Order order, List<CartItem> cartItemList) {
        Connection con = ConnectionDB.openCon();
        int orderId;
        try {
            con.setAutoCommit(false);
            CallableStatement cs = con.prepareCall("call proc_create_orders(?,?,?,?)");
            cs.setInt(1, order.getUser().getUserId());
            cs.setInt(2, order.getStatus());
            cs.setString(3, order.getAddress());
//            cs.setDate(4, order.getCreate_at());
            cs.registerOutParameter(4, Types.INTEGER);
            int checkStatement = cs.executeUpdate();
            orderId = cs.getInt(4);
            CallableStatement callableStatement = con.prepareCall("call proc_add_new_order_detail(?,?,?)");
            for (CartItem cartItem : cartItemList) {
                callableStatement.setInt(1, orderId);
                callableStatement.setInt(2, cartItem.getProduct().getProductId());
                callableStatement.setInt(3,cartItem.getQuantity());
                callableStatement.addBatch();
            }
            int[] batchResult = callableStatement.executeBatch();
            if (checkStatement > 0 && batchResult.length > 0) {
                con.commit();
                return true;
            } else {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return true;
    }

    @Override
    public void acceptOrDeny(Integer id,Integer status) {
        Connection connection = ConnectionDB.openCon();
        try {
            CallableStatement cs = connection.prepareCall("call proc_accept_or_deny_order(?,?)");
            cs.setInt(1,id);
            cs.setInt(2,status);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(connection);
        }
    }
}
