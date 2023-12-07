package com.ra.model.dao.category;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Category;

public interface CategoryDAO extends IGenericDAO<Category,Integer> {
    void changeStatus(Integer id);
    void sortCategory();

}
