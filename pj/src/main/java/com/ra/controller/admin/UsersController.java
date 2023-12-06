package com.ra.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class UsersController {
    @RequestMapping("/user-list")
        public String user_list(){
        return "admin/users/user-list";
        }

    @RequestMapping("/user-profile")
    public String user_profile(){
        return "admin/users/user-profile";
    }
}
