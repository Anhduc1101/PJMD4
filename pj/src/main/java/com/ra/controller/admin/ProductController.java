package com.ra.controller.admin;

import com.ra.model.entity.Product;
import com.ra.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/product")
    public String product_add(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("productList",products);
        Product pro = new Product();
        model.addAttribute("product",pro);
        return "admin/product/product";
    }
    @RequestMapping("/product-detail")
    public String product_detail(){
        return "admin/product/product-detail";
    }
    @RequestMapping("/product-grid")
    public String product_grid(){
        return "admin/product/product-grid";
    }
    @RequestMapping("/product-list")
    public String product_list(){
        return "admin/product/product-list";
    }


}
