package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category) throws DataSourceException;
    ProductCategory find(int id) throws DataSourceException;
    void remove(int id) throws DataSourceException;

    List<ProductCategory> getAll() throws DataSourceException;

}
