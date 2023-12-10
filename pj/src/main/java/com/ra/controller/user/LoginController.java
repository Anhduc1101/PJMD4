package com.ra.controller.user;

import com.ra.model.entity.User;
import com.ra.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;
    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "user/login";
    }
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute("user") User user){
        User userLogin = userService.login(user.getEmail(),user.getPassword());
        System.out.println(userLogin);
        if (userLogin!=null){
            // luu session vao nhe
            session.setAttribute("userLogin",userLogin);
            return "redirect:/";
        }
        return "redirect:/login";
    }
}
