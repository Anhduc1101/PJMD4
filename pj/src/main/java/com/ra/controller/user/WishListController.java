package com.ra.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WishListController {
@RequestMapping("/wish-list")
public String wish_list(){
    return "user/wish-list";
} }
