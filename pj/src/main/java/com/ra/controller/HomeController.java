package com.ra.controller;

import com.ra.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/")
    public String index() {
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
