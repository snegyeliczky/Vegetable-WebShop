package com.codecool.shop.dao.JDBCImplementation;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductJDBC extends JDBCBaseConnection implements ProductDao
{


    @Override
    public void add(Product product) throws DataSourceException {
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO product(name, description, default_price, supplier_id, product_category_id) VALUES (?,?,?,?,?)");
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setFloat(3, product.getDefaultPrice());
            stmt.setInt(4, product.getSupplier().getId());
            stmt.setInt(5, product.getProductCategory().getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new DataSourceException("Product failed to add to the database", e);
        }

    }

    @Override
    public Product find(int id) throws DataSourceException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT p.*, s.name as s_name, s.description as s_description," +
                    " pc.name as pc_name, pc.description as pc_description, pc.department as pc_department " +
                    "FROM product p " +
                    "JOIN supplier s ON p.supplier_id = s.id " +
                    "JOIN product_category pc ON p.product_category_id = pc.id " +
                    "WHERE p.id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Product result = createProducts(resultSet).get(0);

            resultSet.close();
            statement.close();
            return result;
        } catch (SQLException e) {
            throw new DataSourceException("Product not found", e);
        }
    }

    @Override
    public void remove(int id) throws DataSourceException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM prodcut WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new DataSourceException("Removal of product failed", e);
        }

    }

    @Override
    public List<Product> getAll() throws DataSourceException {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT p.*, s.name as s_name, s.description as s_description," +
                    " pc.name as pc_name, pc.description as pc_description, pc.department as pc_department " +
                    "FROM product p " +
                    "JOIN supplier s ON p.supplier_id = s.id " +
                    "JOIN product_category pc ON p.product_category_id = pc.id ");

            List<Product> productList = createProducts(resultSet);

            resultSet.close();
            statement.close();
            return productList;
        } catch (SQLException e) {
            throw new DataSourceException("Failed to create all product list", e);
        }
    }

    private List<Product> createProducts(ResultSet resultSet) throws DataSourceException {
        try {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product newProduct = createProduct(resultSet);
                products.add(newProduct);
            }
            return products;
        } catch (SQLException e) {
            throw new DataSourceException("Couldn't create list of Products", e);
        }

    }

    @Override
    public List<Product> getBy(Supplier supplier) throws DataSourceException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT p.*, s.name as s_name, s.description as s_description," +
                    " pc.name as pc_name, pc.description as pc_description, pc.department as pc_department " +
                    "FROM product p " +
                    "JOIN supplier s ON p.supplier_id = s.id " +
                    "JOIN product_category pc ON p.product_category_id = pc.id " +
                    "WHERE s.id = ?");
            statement.setInt(1, supplier.getId());
            ResultSet resultSet = statement.executeQuery();

            List<Product> productList = createProducts(resultSet);

            resultSet.close();
            statement.close();
            return productList;
        } catch (SQLException e) {
            throw new DataSourceException("Failed to get Product by Supplier", e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) throws DataSourceException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT p.*, s.name as s_name, s.description as s_description," +
                    " pc.name as pc_name, pc.description as pc_description, pc.department as pc_department " +
                    "FROM product p " +
                    "JOIN supplier s ON p.supplier_id = s.id " +
                    "JOIN product_category pc ON p.product_category_id = pc.id " +
                    "WHERE pc.id = ?");
            statement.setInt(1, productCategory.getId());
            ResultSet resultSet = statement.executeQuery();

            List<Product> productList = createProducts(resultSet);

            resultSet.close();
            statement.close();
            return productList;
        } catch (SQLException e) {
            throw new DataSourceException("Failed to get Product by Category", e);
        }
    }

    private Supplier createSupplier(ResultSet resultSet) throws SQLException {
        int supplierId = resultSet.getInt("supplier_id");
        String supplierName = resultSet.getString("s_name");
        String supplierDescription = resultSet.getString("s_description");

        Supplier supplier = new Supplier(supplierName, supplierDescription);
        supplier.setId(supplierId);

        return supplier;
    }

    private ProductCategory createProductCategory(ResultSet resultSet) throws SQLException {
        int productCategoryId = resultSet.getInt("product_category_id");
        String categoryName = resultSet.getString("pc_name");
        String categoryDepartment = resultSet.getString("pc_department");
        String categoryDescription = resultSet.getString("pc_description");

        ProductCategory productCategory = new ProductCategory(categoryName, categoryDepartment, categoryDescription);
        productCategory.setId(productCategoryId);

        return productCategory;
    }

    private Product createProduct(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        float price = resultSet.getFloat("default_price");

        Product product = new Product(name, price, "USD", description, createProductCategory(resultSet), createSupplier(resultSet));
        product.setId(id);

        return product;
    }

}
