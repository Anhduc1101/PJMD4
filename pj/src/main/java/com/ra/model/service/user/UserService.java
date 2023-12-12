package com.ra.model.service.user;

import com.ra.model.entity.User;
import com.ra.model.service.IGenericService;

public interface UserService extends IGenericService<User,Integer> {
    void changeUserStatus(Integer id);
    void changeUserRole(Integer id);
    User login(String email, String password);
    boolean register(User user);
}
