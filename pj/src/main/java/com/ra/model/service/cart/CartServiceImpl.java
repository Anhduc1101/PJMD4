package com.ra.model.service.cart;

import com.ra.model.dao.cart.CartDAO;
import com.ra.model.entity.Cart;
import com.ra.model.entity.User;
import com.ra.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDAO cartDAO;
    @Autowired
    private HttpSession httpSession;

    @Override
    public List<Cart> findAll() {
        return cartDAO.findAll();
    }

    @Override
    public boolean saveOrUpdate(Cart cart) {
        return cartDAO.saveOrUpdate(cart);
    }

    @Override
    public Cart findById(Integer integer) {
        return cartDAO.findById(integer);
    }

    @Override
    public List<Cart> findByName(String name) {
        return null;
    }

    @Override
    public Cart findCartByUserId(Integer id) {
        return cartDAO.findCartByUserId(id);
    }

    @Override
    public Cart createCart(User user) {
        return null;
    }

    @Override
    public void clearCart(Integer id) {
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_clear_cart(?)");
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
    }

}
