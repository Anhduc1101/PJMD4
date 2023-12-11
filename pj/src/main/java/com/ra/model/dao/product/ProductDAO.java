package com.ra.model.dao.product;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Product;

public interface ProductDAO extends IGenericDAO<Product,Integer> {
    void delete(Integer id);
    void changeStatus(Integer id);
}
