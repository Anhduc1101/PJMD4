package com.ra.model.service;

import com.ra.model.entity.Cart;

import java.util.List;

public interface IGenericService<T,ID> {
    List<T> findAll();
    boolean saveOrUpdate(T t);
    T findById(ID id);
    List<T> findByName(String name);

//    void clearCart(Cart cart);
}
