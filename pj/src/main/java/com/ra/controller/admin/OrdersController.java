package com.ra.controller.admin;

import com.ra.model.entity.*;
import com.ra.model.service.cart.CartService;
import com.ra.model.service.cartItem.CartItemService;
import com.ra.model.service.order.OrderService;
import com.ra.model.service.order_detail.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class OrdersController {
    @Value("D:\\duc\\mySQL\\projectMD4\\pj\\src\\main\\webapp\\uploads\\")
    private String path;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;
    @GetMapping("/list-order")
    public String list_order(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin/order/list-order";
    }

    @GetMapping("/order/accept/{id}")
    public String accept(@PathVariable("id") Integer id) {
        orderService.acceptOrDeny(id,2);
        return "redirect:/admin/list-order";
    }

    @GetMapping("/order/deny/{id}")
    public String deny(@PathVariable("id") Integer id) {
        orderService.acceptOrDeny(id,0);
        return "redirect:/admin/list-order";
    }

    @GetMapping("/order/info/{id}")
    public String info(@PathVariable("id") Integer id,Model model) {
        List<OrderDetail> orderDetailList=orderDetailService.findListOrderDetailByOrderId(id);
        model.addAttribute("orderDetailList",orderDetailList);
        double totalAmount = 0;
        for (OrderDetail orderDetail : orderDetailList) {
            totalAmount = totalAmount + (orderDetail.getQuantity() * orderDetail.getProduct().getUnitPrice());
        }
        httpSession.setAttribute("total", totalAmount);
        return "/admin/order/order-detail";
    }


//    @RequestMapping("/order-detail")
//    public String order_detail(){
//        return "admin/order/order-detail";
//    }
//    @RequestMapping("/order-history")
//    public String order_history(){
//        return "admin/order/order-history";
//    }

}
