package com.ra.model.dao.CartItem;

import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Product;
import com.ra.model.service.cart.CartService;
import com.ra.model.service.product.ProductService;
import com.ra.util.ConnectionDB;
import jdk.vm.ci.code.site.Call;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDAOImpl implements CartItemDAO {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @Override
    public void delete(Integer id) {
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_delete_cart_item(?)");
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }

    }

    @Override
    public boolean addOrUpdateCartItem(CartItem cartItem) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            if (cartItem.getId() == 0) {
                cs = con.prepareCall("call proc_add_to_cart_item(?,?,?)");
                cs.setInt(1, cartItem.getProduct().getProductId());
                cs.setInt(2, cartItem.getCart().getId());
                cs.setInt(3, cartItem.getQuantity());
            } else {
                cs = con.prepareCall("call proc_update_cart_item(?,?,?,?)");
                cs.setInt(1, cartItem.getProduct().getProductId());
                cs.setInt(2, cartItem.getCart().getId());
                cs.setInt(3, cartItem.getQuantity());
                cs.setInt(4, cartItem.getId());
            }
            check = cs.executeUpdate();
            if (check>0){
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
    public List<CartItem> getCartItem() {
        Connection con = ConnectionDB.openCon();
        List<CartItem> cartItems = new ArrayList<>();
        try {
            CallableStatement cs = con.prepareCall("call proc_get_cart_item()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                CartItem cartItem = new CartItem();
                cartItem.setId(rs.getInt("id"));
                cartItem.setProduct(productService.findById(rs.getInt("proId")));
                cartItem.setCart(cartService.findById(rs.getInt("cartId")));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItems.add(cartItem);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
        return cartItems;
    }

    @Override
    public CartItem findById(Integer id) {
        Connection con = ConnectionDB.openCon();
                CartItem cartItem = new CartItem();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_cart_item_by_id(?)");
            cs.setInt(1,id);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                cartItem.setId(rs.getInt("id"));
                cartItem.setProduct(productService.findById(rs.getInt("proId")));
                cartItem.setCart(cartService.findById(rs.getInt("cartId")));
                cartItem.setQuantity(rs.getInt("quantity"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
        return cartItem;
    }

    @Override
    public List<CartItem> findCartItemByCartId(Cart cart) {
        Connection con = ConnectionDB.openCon();
        List<CartItem> cartItemList = new ArrayList<>();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_cart_item_by_cart_id(?)");
            cs.setInt(1,cart.getId());
            ResultSet rs= cs.executeQuery();
            while (rs.next()){
                CartItem cart1 = new CartItem();
                cart1.setId(rs.getInt("id"));
                cart1.setCart(cartService.findById(rs.getInt("cartId")));
                cart1.setProduct(productService.findById(rs.getInt("proId")));
                cart1.setQuantity(rs.getInt("quantity"));
                cartItemList.add(cart1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
        return cartItemList;
    }
}
