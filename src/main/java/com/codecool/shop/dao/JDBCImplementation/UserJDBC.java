package com.codecool.shop.dao.JDBCImplementation;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBC extends JDBCBaseConnection {

    public void addUser(String name, String email, String password) throws DataSourceException {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (name, email, password) VALUES (?,?,?)")) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataSourceException("User failed to add new User to the database", e);
        }
    }

}