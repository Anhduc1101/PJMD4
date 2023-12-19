package com.ra.controller.admin;

import com.ra.controller.UploadController;
import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.category.CategoryService;
import com.ra.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Controller
@RequestMapping("admin")
public class ProductController {
    @Value("D:\\duc\\mySQL\\projectMD4\\pj\\src\\main\\webapp\\uploads\\")
    private String path;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/product-list")
    public String product_list(Model model) {
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
    public String product_add(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("category", categoryList);
        Product pro = new Product();
        model.addAttribute("product", pro);
        return "admin/product/add-product";
    }


    @PostMapping("/add-product")
    public String handleAdd(@Valid @ModelAttribute("product") Product product, BindingResult result,RedirectAttributes attributes, Model model, @RequestParam("images") MultipartFile file ) {
        if (result.hasErrors()) {
            List<Category> categoryList = categoryService.findAll();
            model.addAttribute("category", categoryList);
            return "admin/product/add-product";
        } else {
            String fileName = file.getOriginalFilename();
            File destination = new File(path + fileName);
            try {
                Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setImg(fileName);
            productService.saveOrUpdate(product);
//            System.out.println(product);
//            attributes.addFlashAttribute("mess", "Added New Product Successfully!!!");
            return "redirect:/admin/product-list";
        }
    }

    @GetMapping("/product/edit-product/{id}")
    public String edit_product(@PathVariable("id") Integer id, Model model) {
        Product pro = productService.findById(id);
        model.addAttribute("pro", pro);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("category", categories);
        return "admin/product/edit-product";
    }

    @PostMapping("/product/edit-product")
    public String update_product(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, @RequestParam("images") MultipartFile file) {
        try {
        if (result.hasErrors()) {
            List<Category> categoryList = categoryService.findAll();
            model.addAttribute("category", categoryList);
            return "admin/product/edit-product";
        }
                if (!file.isEmpty()){
                    String fileName = file.getOriginalFilename();
                    File destination = new File(path + fileName);
                    Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
                    product.setImg(fileName);
                }
                productService.saveOrUpdate(product);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/product-list";
    }

    @GetMapping("/product/delete-product/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return "redirect:/admin/product-list";
    }

    @GetMapping("/product/changeStatus/{id}")
    public String changeStatus(@PathVariable("id") Integer id) {
        productService.changeStatus(id);
        return "redirect:/admin/product-list";
    }


}
