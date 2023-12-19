package com.ra.model.dao.category;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.Category;

import java.util.List;

public interface CategoryDAO extends IGenericDAO<Category,Integer> {
    void changeStatus(Integer id);
    List<Category> sortCategory();
    List<Category> paginator(Integer noPage);
    Integer getTotalPage();

}
