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

    @GetMapping("/sub-category")
    public String subCategory(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categoryList", categories);
        Category cat = new Category();
        model.addAttribute("category", cat);
        return "admin/category/sub-category";
    }
//PHÂN TRANG

//    @GetMapping("/sub-category/{id}")
//    public String subCategory(@PathVariable("id") Integer id, Model model) {
//        List<Category> categoryList = categoryService.paginator(id);
//// Lấy danh sách các đối tượng Category theo trang từ categoryService và gán cho categoryList
//        model.addAttribute("totalPage", categoryService.getTotalPage());
//// Lấy tổng số trang từ categoryService và thêm vào model với thuộc tính "totalPage"
//        model.addAttribute("categoryList", categoryList);
//// Thêm danh sách categoryList vào model với thuộc tính "categoryList"
//        Category cat = new Category();
//// Tạo một đối tượng Category mới
//        model.addAttribute("category", cat);
//// Thêm đối tượng cat vào model với thuộc tính "category"
//        return "admin/category/sub-category";
//// Trả về tên của template "admin/category/sub-category"
//    }


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
        List<Category> categories = categoryService.findAll();
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
    @GetMapping("/search")
    public String search(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Category> searchResult = categoryService.findByName(search);
        model.addAttribute("categoryList", searchResult);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("totalPage", (int) Math.ceil(categories.size() / 4.0));
        return "admin/category/sub-category";
    }

    @GetMapping("/sort")
    public String sortCategory(Model model) {
        List<Category> sortResult =categoryService.sortCategory();
        model.addAttribute("categoryList",sortResult);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("totalPage", (int) Math.ceil(categories.size() / 4.0));
        return "admin/category/sub-category";
    }

}
