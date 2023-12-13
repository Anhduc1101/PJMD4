package com.ra.controller.user;

import com.ra.model.entity.Product;
import com.ra.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/shop")
    public String shop(Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("productList",productList);
        return "user/shop";
    }
}
