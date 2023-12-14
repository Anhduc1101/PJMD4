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
import org.springframework.web.bind.annotation.RequestMapping;

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
        if (user!=null){
        Cart cart = cartService.findCartByUserId(user.getUserId());
        List<CartItem> cartItems =  cartItemService.findCartItemByCart(cart);
        model.addAttribute("cartItems",cartItems);
        return "user/cart";
        }
        return "redirect:/login";
    }


}
