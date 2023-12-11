package com.ra.model.service.product;

import com.ra.model.entity.Product;
import com.ra.model.service.IGenericService;

public interface ProductService extends IGenericService<Product,Integer> {
    void delete (Integer id);
    void changeStatus(Integer id);
}
