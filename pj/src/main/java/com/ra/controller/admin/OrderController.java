package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class OrderController {
    @RequestMapping("/new-order")
    public String new_order(){
        return "admin/order/new-order";
    }
    @RequestMapping("/order-detail")
    public String order_detail(){
        return "admin/order/order-detail";
    }
    @RequestMapping("/order-history")
    public String order_history(){
        return "admin/order/order-history";
    }

}
