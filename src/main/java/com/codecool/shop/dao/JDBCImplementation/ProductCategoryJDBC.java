package com.codecool.shop.dao.JDBCImplementation;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductCategoryJDBC extends JDBCBaseConnection implements ProductCategoryDao {
    @Override
    public void add(ProductCategory category) throws DataSourceException {
        try(Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO product_category(name, description, department) VALUES (?,?,?)");
            stmt.setString(1,category.getName());
            stmt.setString(2,category.getDescription());
            stmt.setString(3,category.getDepartment());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new DataSourceException("Product Category add failed", e);
        }
    }

    @Override
    public ProductCategory find(int id) throws DataSourceException {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product_category WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            ProductCategory productCategory = createListFromSQLRs(resultSet).get(0);

            resultSet.close();
            statement.close();
            return productCategory;
        } catch (SQLException e) {
        throw new DataSourceException("Product Category find failed", e);
        }
    }

    @Override
    public void remove(int id) throws DataSourceException {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE product_category WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DataSourceException("Product Category remove failed", e);
        }
    }

    @Override
    public List<ProductCategory> getAll() throws DataSourceException {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product_category");
            ResultSet resultSet = statement.executeQuery();

            List<ProductCategory> productCategoryList = createListFromSQLRs(resultSet);

            resultSet.close();
            statement.close();
            return productCategoryList;
        } catch (SQLException | DataSourceException e) {
            throw new DataSourceException("Product Category list failed", e);
        }
    }

    private List<ProductCategory> createListFromSQLRs(ResultSet resultSet) throws DataSourceException {
        try {
            List<ProductCategory> productCategoryList = new LinkedList<>();
            while (resultSet.next()){
                String resultName = resultSet.getString("name");
                String resultDescription = resultSet.getString("description");
                String resultDepartment = resultSet.getString("department");

                ProductCategory productCategory = new ProductCategory(resultName, resultDescription, resultDepartment);
                productCategory.setId(resultSet.getInt("id"));

                productCategoryList.add(productCategory);
            }
            return productCategoryList;
        } catch (SQLException e) {
            throw new DataSourceException("Product Category createListFromSQLRs failed", e);
        }
    }
}
