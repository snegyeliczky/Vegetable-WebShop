package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;
import java.util.Map;

public interface CartDao {

    void add(String user,Product product);
    void remove(int id, String userName);

    List<Product>getUserProductList(String user);
}
