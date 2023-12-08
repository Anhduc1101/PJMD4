package com.ra.model.dao.user;

import com.ra.model.dao.IGenericDAO;
import com.ra.model.entity.User;

public interface UserDAO extends IGenericDAO<User,Integer> {
    void changeUserStatus(Integer id);
    void changeUserRole(Integer id);
    User checkEmail(String email);
}
