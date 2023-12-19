package com.ra.model.dao.category;

import com.ra.model.entity.Category;
import com.ra.util.ConnectionDB;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryDAOImpl implements CategoryDAO {
    private int LIMIT = 5;
    private int totalPage = 0;

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_show_list_category()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Category cat = new Category();
                cat.setCategoryId(rs.getInt("id"));
                cat.setCategoryName(rs.getString("name"));
                cat.setCategoryStatus(rs.getBoolean("status"));
                categories.add(cat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return categories;
    }

    @Override
    public boolean saveOrUpdate(Category category) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            if (category.getCategoryId() == 0) {
                cs = con.prepareCall("call proc_add_new_category(?,?)");
                cs.setString(1, category.getCategoryName());
                cs.setBoolean(2, category.isCategoryStatus());
            } else {
                cs = con.prepareCall("call proc_update_category(?,?,?)");
                cs.setString(1, category.getCategoryName());
                cs.setBoolean(2, category.isCategoryStatus());
                cs.setInt(3, category.getCategoryId());
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
    public Category findById(Integer integer) {
        Category cat = new Category();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_category_by_id(?)");
            cs.setInt(1, integer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                cat.setCategoryId(rs.getInt("id"));
                cat.setCategoryName(rs.getString("name"));
                cat.setCategoryStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return cat;
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> categories = new ArrayList<>();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_category_by_name(?)");
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Category cat = new Category();
                cat.setCategoryId(rs.getInt("id"));
                cat.setCategoryName(rs.getString("name"));
                cat.setCategoryStatus(rs.getBoolean("status"));
                categories.add(cat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return categories;
    }

    @Override
    public void changeStatus(Integer id) {
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_change_status_category(?)");
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
    }

    @Override
    public List<Category> sortCategory() {
        Connection con = ConnectionDB.openCon();
        List<Category> categories = new ArrayList<>();
        try {
            CallableStatement cs = con.prepareCall("call proc_sort_category()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Category cat = new Category();
                cat.setCategoryId(rs.getInt("id"));
                cat.setCategoryName(rs.getString("name"));
                cat.setCategoryStatus(rs.getBoolean("status"));
                categories.add(cat);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return categories;
    }

    @Override
    public List<Category> paginator(Integer noPage) {
        Connection con = ConnectionDB.openCon();
        List<Category> categories = new ArrayList<>();
        try {
            CallableStatement cs = con.prepareCall("call proc_pagination_category(?,?,?)");
            cs.setInt(1, LIMIT);
            cs.setInt(2, noPage);
            cs.setInt(3, Types.INTEGER);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Category cat = new Category();
                cat.setCategoryId(rs.getInt("id"));
                cat.setCategoryName(rs.getString("name"));
                cat.setCategoryStatus(rs.getBoolean("status"));
                categories.add(cat);
            }
            this.totalPage = cs.getInt(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return categories;
    }

    @Override
    public Integer getTotalPage() {
        return totalPage;
    }
}
