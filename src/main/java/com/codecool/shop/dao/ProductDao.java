package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface ProductDao {

    void add(Product product) throws DataSourceException;
    Product find(int id) throws DataSourceException;
    void remove(int id) throws DataSourceException;

    List<Product> getAll() throws DataSourceException;
    List<Product> getBy(Supplier supplier) throws DataSourceException;
    List<Product> getBy(ProductCategory productCategory) throws DataSourceException;

}
