package com.ra.model.service.cartItem;

import com.ra.model.dao.CartItem.CartItemDAO;
import com.ra.model.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemDAO cartItemDAO;

    @Override
    public List<CartItem> findAll() {
        return cartItemDAO.getCartItem();
    }

    @Override
    public boolean saveOrUpdate(CartItem cartItem) {
        return cartItemDAO.addOrUpdateCartItem(cartItem);
    }

    @Override
    public CartItem findById(Integer integer) {
        return cartItemDAO.findById(integer);
    }

    @Override
    public List<CartItem> findByName(String name) {
        return null;
    }

    @Override
    public void deleteCartItem(Integer id) {
        cartItemDAO.delete(id);
    }
}
