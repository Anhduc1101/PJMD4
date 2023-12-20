package com.ra.controller;

import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private HttpSession httpSession;
@Autowired
private ProductService productService;
    @RequestMapping("/")
    public String index(Model model) {
        List<Product> products=productService.findAll();
        model.addAttribute("products",products);
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "user/about";
    }

    @RequestMapping("/blog")
    public String blog() {
        return "user/blog";
    }

    @RequestMapping("/checkout")
    public String checkout() {
        return "user/checkout";
    }

    @RequestMapping("/wish-list")
    public String wish_list() {
        return "user/wish-list";
    }


}
