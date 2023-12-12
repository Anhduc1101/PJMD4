package com.ra.controller.user;

import com.ra.model.entity.User;
import com.ra.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user/login";
    }

    @PostMapping("/login")
    public String handleLogin(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/login";
        } else {
            User userLogin = userService.login(user.getEmail(), user.getPassword());
            if (userLogin != null && userLogin.isRole() == true) {
                // luu session vao nhe
                session.setAttribute("userLogin", userLogin);
                return "redirect:/";
            } else {
                return "redirect:/admin";
            }
        }
    }

    @GetMapping("/log-out")
    public String log_out() {
        session.removeAttribute("userLogin");
        return "redirect:/";
    }
}
