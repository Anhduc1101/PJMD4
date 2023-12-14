package com.ra.controller.user;

import com.ra.model.entity.Cart;
import com.ra.model.entity.CartItem;
import com.ra.model.entity.Product;
import com.ra.model.entity.User;
import com.ra.model.service.cart.CartService;
import com.ra.model.service.cartItem.CartItemService;
import com.ra.model.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartItemController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private HttpSession httpSession;
    @Value("D:\\duc\\mySQL\\projectMD4\\pj\\src\\main\\webapp\\uploads\\")
    private String path;

    @GetMapping("/cart-item/{id}")
    public String getCartItem(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "user/cart-item";
    }

    @PostMapping("/add-to-cart")
    public String add_to_cart(@ModelAttribute("product") Product product, @RequestParam("ec_qtybtn") Integer qty) {
//  lay user dang dang nhap ve
        User user = (User) httpSession.getAttribute("userLogin");
//        kiem tra xem user da dang nhap chua
        if (user == null) {
//            chua thi chuyen ve trang login
            return "redirect:/login";
        }
//        lay list cart(gio hang) ve va kiem tra
        List<Cart> cartList = cartService.findAll();
//        neu user da dang nhap roi, kiem tra user co ton tai gio hang hay khong?
        Cart cart = cartService.findCartByUserId(user.getUserId());
//        neu khong co thi tao gio hang moi
        if (cart == null) {
            Cart cart1 = new Cart();
            cart1.setUser(user);
            cartList.add(cart1);
        }
//        lay danh sach nhung san pham trong gio hang cua user dang dang nhap
        List<CartItem> cartItemList = cartItemService.findCartItemByCart(cart);
        boolean checkProduct = checkProductExist(product, cartItemList);
//        neu san pham da ton tai trong gio hang
        if (checkProduct) {
//            cong so luong san pham len
            CartItem cartItem = findCartItemByProductId(product,cartItemList);
            cartItem.setQuantity(cartItem.getQuantity()+qty);
            cartItemService.saveOrUpdate(cartItem);
        } else {
//        neu san pham chua ton tai thi tao cartItem moi
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setQuantity(qty);
            cartItem.setProduct(product);
            cartItemService.saveOrUpdate(cartItem);
        }
        return "redirect:/shop";
    }
    private CartItem findCartItemByProductId(Product product, List<CartItem> cartItemList){
        for (CartItem item:cartItemList) {
            if (item.getProduct().getProductId()==product.getProductId()){
                return item;
            }
        }
        return null;
    }

    private boolean checkProductExist(Product product, List<CartItem> cartItemList) {
        for (CartItem item : cartItemList) {
            if (item.getProduct().getProductId() == product.getProductId()) {
                return true;
            }
        }
        return false;
    }
}
