package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartItemController {
    @GetMapping("/cart-item")
    public String getCartItem(){
        return "user/cart-item";
    }
}
