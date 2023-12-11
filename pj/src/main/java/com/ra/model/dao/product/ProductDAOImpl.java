package com.ra.model.dao.product;

import com.ra.model.entity.Product;
import com.ra.model.service.category.CategoryService;
import com.ra.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_show_list_product()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setProductId(rs.getInt("id"));
                pro.setProductName(rs.getString("name"));
                pro.setCategory(categoryService.findById(rs.getInt("category_id")));
                pro.setDescription(rs.getString("description"));
                pro.setUnitPrice(rs.getDouble("price"));
                pro.setStock(rs.getInt("stock"));
                pro.setStatus(rs.getBoolean("status"));
                products.add(pro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return products;
    }

    @Override
    public boolean saveOrUpdate(Product product) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            if (product.getProductId() == 0) {
                cs = con.prepareCall("call proc_add_new_product(?,?,?,?,?,?,?)");
                cs.setString(1,product.getImg());
                cs.setString(2, product.getProductName());
                cs.setInt(3, product.getCategory().getCategoryId());
                cs.setString(4, product.getDescription());
                cs.setDouble(5, product.getUnitPrice());
                cs.setInt(6, product.getStock());
                cs.setBoolean(7,product.isStatus());
            } else {
                cs = con.prepareCall("call proc_update_product(?,?,?,?,?,?,?,?)");
                cs.setString(1,product.getImg());
                cs.setString(2, product.getProductName());
                cs.setInt(3, product.getCategory().getCategoryId());
                cs.setString(4, product.getDescription());
                cs.setDouble(5, product.getUnitPrice());
                cs.setInt(6, product.getStock());
                cs.setBoolean(7, product.isStatus());
                cs.setInt(8, product.getProductId());
            }
            check = cs.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
        return false;
    }

    @Override
    public Product findById(Integer integer) {
        Connection con = ConnectionDB.openCon();
        Product pro = new Product();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_product_by_id(?)");
            cs.setInt(1, integer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                pro.setProductId(rs.getInt("id"));
                pro.setProductName(rs.getString("name"));
                pro.setCategory(categoryService.findById(rs.getInt("category_id")));
                pro.setDescription(rs.getString("description"));
                pro.setUnitPrice(rs.getDouble("price"));
                pro.setStock(rs.getInt("stock"));
                pro.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return pro;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_product_by_name(?)");
            cs.setString(1, name);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setProductId(rs.getInt("id"));
                pro.setProductName(rs.getString("name"));
                pro.setCategory(categoryService.findById(rs.getInt("category_id")));
                pro.setDescription(rs.getString("description"));
                pro.setUnitPrice(rs.getDouble("price"));
                pro.setStock(rs.getInt("stock"));
                pro.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return products;
    }

    @Override
    public void delete(Integer id) {
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_delete_product(?)");
            cs.setInt(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
