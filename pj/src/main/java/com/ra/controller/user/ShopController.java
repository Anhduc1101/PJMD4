package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {
    @RequestMapping("/shop")
    public String shop(){
        return "user/shop";
    }
}
