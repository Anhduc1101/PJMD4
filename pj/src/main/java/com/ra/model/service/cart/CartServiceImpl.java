package com.ra.model.service.cart;

import com.ra.model.dao.Cart.CartDAO;
import com.ra.model.entity.Cart;
import com.ra.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDAO cartDAO;
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
}
