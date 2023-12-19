package com.ra.model.service.category;

import com.ra.model.entity.Category;
import com.ra.model.service.IGenericService;

import java.util.List;

public interface CategoryService extends IGenericService<Category,Integer> {
    void changeStatus(Integer id);
    List<Category> sortCategory();
    List<Category> paginator(Integer noPage);
    Integer getTotalPage();

}
