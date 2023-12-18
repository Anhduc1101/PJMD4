package com.ra.controller.user;

import com.ra.model.entity.*;
import com.ra.model.service.cart.CartService;
import com.ra.model.service.cartItem.CartItemService;
import com.ra.model.service.order.OrderService;
import com.ra.model.service.order_detail.OrderDetailService;
import com.ra.model.service.product.ProductService;
import com.ra.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping("/cart")
    public String cart(Model model) {
        User user = (User) httpSession.getAttribute("userLogin");
        if (user != null) {
            Cart cart = cartService.findCartByUserId(user.getUserId());
            List<CartItem> cartItems = cartItemService.findCartItemByCart(cart);
            model.addAttribute("cartItems", cartItems);
            httpSession.setAttribute("cartItems",cartItems);
            double totalAmount = 0;
            for (CartItem cartItem : cartItems) {
                totalAmount = totalAmount + (cartItem.getQuantity() * cartItem.getProduct().getUnitPrice());
            }
            model.addAttribute("subTotal", totalAmount);
            httpSession.setAttribute("total", totalAmount);
            model.addAttribute("userLogin", user);
            return "user/cart";
        }
        return "redirect:/login";
    }

    @GetMapping("/minus-quantity/{id}")
    public String minus_quantity(@PathVariable("id") Integer id, @ModelAttribute("product") Product product) {
        CartItem cartItem = cartItemService.findById(id);
        cartItem.setQuantity(cartItem.getQuantity() - 1);
        if (cartItem.getQuantity()==0){
            cartItemService.deleteCartItem(id);
        }
        cartItemService.saveOrUpdate(cartItem);
        return "redirect:/cart";
    }

    @GetMapping("/plus-quantity/{id}")
    public String plus_quantity(@PathVariable("id") Integer id, @ModelAttribute("product") Product product) {
        CartItem cartItem = cartItemService.findById(id);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemService.saveOrUpdate(cartItem);
        return "redirect:/cart";
    }

    @GetMapping("/cart/{id}")
    public String delete_item(@PathVariable("id") Integer id) {
        cartItemService.deleteCartItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/placeOrder")
    public String place_order(@RequestParam("address") String address) {
//        lấy dc thằng user đang đăng nhập
        User user = (User) httpSession.getAttribute("userLogin");
//        lay dc cai cart của úser dang dang nhap,
        Cart cart = cartService.findCartByUserId(user.getUserId());
        List<CartItem> cartItemList = cartItemService.findCartItemByCart(cart);
//        day sang order
        Order order = new Order();
        order.setUser(user);
        order.setStatus(1);
        order.setAddress(address);
//        day sang orderdetail
        orderService.createOrder(order, cartItemList);
        cartItemService.clearCartItem(cart.getId());
        return "user/thanks";
    }
}
