package com.ra.controller.user;

import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.model.service.cart.CartService;
import com.ra.model.service.cartItem.CartItemService;
import com.ra.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/cart")
    public String cart(Model model) {
        User user = (User) httpSession.getAttribute("userLogin");
        if (user != null) {
            Cart cart = cartService.findCartByUserId(user.getUserId());
            List<CartItem> cartItems = cartItemService.findCartItemByCart(cart);
            model.addAttribute("cartItems", cartItems);
            double sub = 0;
            for (CartItem cartItem : cartItems) {
                sub = sub + (cartItem.getQuantity() * cartItem.getProduct().getUnitPrice());
            }
            model.addAttribute("subTotal",sub);
            return "user/cart";
        }
        return "redirect:/login";
    }

    @GetMapping("/minus-quantity/{id}")
    public String minus_quantity(@PathVariable("id") Integer id, @ModelAttribute("product") Product product) {
        CartItem cartItem = cartItemService.findById(id);
        cartItem.setQuantity(cartItem.getQuantity() - 1);
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

//    @GetMapping("/cart/sub-total")
//    public String sub_total(Model model) {
//
//    }
}
