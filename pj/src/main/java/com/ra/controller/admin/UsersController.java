package com.ra.controller.admin;

import com.ra.model.entity.User;
import com.ra.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class UsersController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user-list")
        public String user_list(Model model){
        List<User> userList=userService.findAll();
        model.addAttribute("userList",userList);
        return "admin/users/user-list";
        }

    @RequestMapping("/user-profile")
    public String user_profile(){
        return "admin/users/user-profile";
    }

    @GetMapping("/users/changeStatus/{id}")
    public String changeStatus(@PathVariable("id") Integer id){
        userService.changeUserStatus(id);
        return "redirect:/admin/user-list";
    }

    @GetMapping("/users/changeRole/{id}")
    public String changeRole(@PathVariable("id") Integer id){
        userService.changeUserRole(id);
        return "redirect:/admin/user-list";
    }
}
