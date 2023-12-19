package com.ra.model.service.category;

import com.ra.model.dao.category.CategoryDAO;
import com.ra.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public boolean saveOrUpdate(Category category) {
        return categoryDAO.saveOrUpdate(category);
    }

    @Override
    public Category findById(Integer integer) {
        return categoryDAO.findById(integer);
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryDAO.findByName(name);
    }

    @Override
    public void changeStatus(Integer id) {
        categoryDAO.changeStatus(id);
    }

    @Override
    public List<Category> sortCategory() {
        return categoryDAO.sortCategory();
    }

    @Override
    public List<Category> paginator(Integer noPage) {
        return categoryDAO.paginator(noPage);
    }

    @Override
    public Integer getTotalPage() {
        return categoryDAO.getTotalPage();
    }
}
