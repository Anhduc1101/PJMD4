package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")

public class CategoryController {
    @RequestMapping("/sub-category")
    public String subCategory(){
        return "admin/category/sub-category";
    }
}