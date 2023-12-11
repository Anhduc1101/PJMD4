package com.ra.controller.admin;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.category.CategoryService;
import com.ra.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/product-list")
    public String product_add(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("productList", products);
        return "admin/product/product-list";
    }

    @RequestMapping("/product-detail")
    public String product_detail() {
        return "admin/product/product-detail";
    }

    @RequestMapping("/product-grid")
    public String product_grid() {
        return "admin/product/product-grid";
    }

    @GetMapping("/add-product")
    public String product_list(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("category", categoryList);
        Product pro = new Product();
        model.addAttribute("product", pro);
        return "admin/product/add-product";
    }


    @PostMapping("/add-product")
    public String handleAdd(@ModelAttribute ("product")  Product product) {
        productService.saveOrUpdate(product);
        System.out.println(product);
        return "redirect:/admin/product-list";
    }

    @GetMapping("/product/edit-product/{id}")
    public String edit_product(@PathVariable("id") Integer id, Model model){
        Product pro = productService.findById(id);
        model.addAttribute("pro",pro);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("category",categories);
        return "admin/product/edit-product";
    }

    @PostMapping("/product/edit-product")
    public String update_product(@ModelAttribute("product") Product product){
        productService.saveOrUpdate(product);
        return "redirect:/admin/product-list";
    }

    @GetMapping("/product/delete-product/{id}")
    public String delete(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/admin/product-list";
    }

    @GetMapping("/product/changeStatus/{id}")
    public String changeStatus(@PathVariable("id") Integer id){
        productService.changeStatus(id);
        return "redirect:/admin/product-list";
    }
}
