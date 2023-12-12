package com.ra.controller.user;

import com.ra.model.entity.User;
import com.ra.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyProfileController {
    @Autowired
    private UserService userService;


    @GetMapping("/user/my-profile/{id}")
    public String edit_my_profile(@PathVariable("id")Integer id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "user/my-profile";
    }

    @PostMapping("user/update-profile")
    public String update_profile(@ModelAttribute("user") User user){
        userService.saveOrUpdate(user);
        return "redirect:user/my-profile";
    }
}
