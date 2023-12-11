package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin")


public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/sub-category")
    public String subCategory(Model model) {
        List<Category> categories = categoryService.findAll();
        Category cat = new Category();
        model.addAttribute("category", cat);
        model.addAttribute("categoryList", categories);
        return "admin/category/sub-category";
    }


    @PostMapping("/add-category")
    public String add(@ModelAttribute("add-category") Category category, RedirectAttributes redirectAttributes) {
        categoryService.saveOrUpdate(category);
        System.out.println(category);
        redirectAttributes.addFlashAttribute("mess", "Add Successfully!");
        return "redirect:/admin/sub-category";
    }

    @GetMapping("/category/changeStatus/{categoryId}")
    public String changeStatus(@PathVariable("categoryId") Integer id) {
        categoryService.changeStatus(id);
        return "redirect:/admin/sub-category";
    }

    @GetMapping("/category/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
//        List<Category> categories=categoryService.findAll();
//        model.addAttribute("categoryList", categories);
        Category cat = categoryService.findById(id);
        model.addAttribute("category", cat);
        return "admin/category/sub-category";
    }

    @PostMapping("edit-category")
    public String update(@ModelAttribute("category") Category category){
        categoryService.saveOrUpdate(category);
        return "redirect:/admin/sub-category";
    }
}
