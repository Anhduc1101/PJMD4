package com.ra.model.dao.user;

import com.ra.model.entity.Category;
import com.ra.model.entity.User;
import com.ra.util.ConnectionDB;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_show_list_user()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getBoolean("role"));
                user.setStatus(rs.getBoolean("status"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return userList;
    }

    @Override
    public boolean saveOrUpdate(User user) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            if (user.getUserId() == 0) {
                String hashPass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
                cs = con.prepareCall("call proc_add_new_user(?,?,?,?)");
                cs.setString(1, user.getUserName());
                cs.setString(2, user.getEmail());
                cs.setString(3, hashPass);
                cs.setString(4, user.getPhone());
            } else {
                cs = con.prepareCall("call proc_update_user(?,?,?,?,?)");
                cs.setString(1, user.getUserName());
                cs.setString(2, user.getEmail());
                cs.setString(3, user.getPassword());
                cs.setString(4, user.getPhone());
                cs.setInt(5, user.getUserId());
            }
            check = cs.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return false;
    }

    @Override
    public User findById(Integer integer) {

        Connection con = ConnectionDB.openCon();
        User user = new User();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_user_by_id(?)");
            cs.setInt(1, integer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getBoolean("role"));
                user.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return user;
    }

    @Override
    public List<User> findByName(String name) {
        List<User> userList = new ArrayList<>();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_user_by_name(?)");
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getBoolean("role"));
                user.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return userList;
    }

    @Override
    public void changeUserStatus(Integer id) {
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_change_status_user(?)");
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
    }

    @Override
    public void changeUserRole(Integer id) {
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_change_role_user(?)");
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
    }

    @Override
    public User checkEmail(String email) {
        Connection connection = ConnectionDB.openCon();
        User user = null;
        try {
            CallableStatement cs = connection.prepareCall("call proc_find_user_by_email(?)");
            cs.setString(1, email);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setStatus(rs.getBoolean("status"));
                user.setRole(rs.getBoolean("role"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(connection);
        }
        return user;
    }
}
