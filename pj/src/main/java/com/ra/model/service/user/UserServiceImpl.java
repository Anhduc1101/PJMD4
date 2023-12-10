package com.ra.model.service.user;

import com.ra.model.dao.user.UserDAO;
import com.ra.model.entity.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public boolean saveOrUpdate(User user) {
        return userDAO.saveOrUpdate(user);
    }

    @Override
    public User findById(Integer integer) {
        return userDAO.findById(integer);
    }

    @Override
    public List<User> findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public void changeUserStatus(Integer id) {
        userDAO.changeUserStatus(id);
    }

    @Override
    public void changeUserRole(Integer id) {
        userDAO.changeUserRole(id);
    }

    @Override
    public User login(String email, String password) {
        User user = userDAO.checkEmail(email);

        if (user != null) {
            System.out.println(BCrypt.checkpw(password,user.getPassword()));
            if (BCrypt.checkpw(password,user.getPassword())) {
                // tu pass nhâp vao với pass trong database so sanh ra false kìa
                return user;
            }
        }
        return null;
    }

//    @Override
//    public User checkEmail(String email) {
//        return userDAO.checkEmail(email);
//    }
}
