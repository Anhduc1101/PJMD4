package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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
    public String add(@Valid @ModelAttribute("add-category") Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/category/sub-category";
        } else {
            categoryService.saveOrUpdate(category);
            redirectAttributes.addFlashAttribute("mess", "Added Category Successfully!!!");
            return "redirect:/admin/sub-category";
        }
    }

    @GetMapping("/category/changeStatus/{id}")
    public String changeStatus(@PathVariable("id") Integer id) {
        categoryService.changeStatus(id);
        return "redirect:/admin/sub-category";
    }

    @GetMapping("/category/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        List<Category> categories=categoryService.findAll();
        model.addAttribute("categoryList", categories);
        Category cat = categoryService.findById(id);
        model.addAttribute("category", cat);
        return "admin/category/sub-category";
    }

    @PostMapping("edit-category")
    public String update(@ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/category/sub-category";
        } else {
            categoryService.saveOrUpdate(category);
            redirectAttributes.addFlashAttribute("mess", "Added Category Successfully!!!");
            return "redirect:/admin/sub-category";
        }
    }
}
