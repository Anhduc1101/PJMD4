package com.ra.model.dao.Cart;

import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.User;
import com.ra.model.service.user.UserService;
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
public class CartDAOImpl implements CartDAO {
    @Autowired
    private UserService userService;

    @Override
    public List<Cart> findAll() {
        Connection connection = ConnectionDB.openCon();
        List<Cart> carts = new ArrayList<>();
        try {
            CallableStatement cs = connection.prepareCall("call proc_show_list_cart()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUser(userService.findById(rs.getInt("userId")));
                carts.add(cart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(connection);
        }
        return carts;
    }

    @Override
    public boolean saveOrUpdate(Cart cart) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            if (cart.getId() == 0) {
                cs = con.prepareCall("call proc_add_to_cart(?)");
                cs.setInt(1,cart.getUser().getUserId());
            }else {
                cs= con.prepareCall("call proc_update_cart(?,?)");
                cs.setInt(1,cart.getUser().getUserId());
                cs.setInt(2,cart.getId());
            }
            check=cs.executeUpdate();
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
    public void delete(Integer id) {

    }

    @Override
    public Cart findById(Integer id) {
        Connection con = ConnectionDB.openCon();
        Cart cart = new Cart();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_cart_by_id(?)");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                cart.setId(rs.getInt("id"));
                cart.setUser(userService.findById(rs.getInt("userId")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return cart;
    }

    @Override
    public Cart findCartByUserId(Integer id) {
        Connection con = ConnectionDB.openCon();
        Cart cart = new Cart();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_cart_by_user_id(?)");
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                cart.setId(rs.getInt("id"));
                cart.setUser(userService.findById(rs.getInt("userId")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return cart;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        saveOrUpdate(cart);
        return cart;
    }
}
