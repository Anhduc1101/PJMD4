package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class ProductController {
    @RequestMapping("/product-add")
    public String product_add(){
        return "admin/product/product-add";
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
