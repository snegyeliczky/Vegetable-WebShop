package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier) throws DataSourceException;
    Supplier find(int id) throws DataSourceException;
    void remove(int id) throws DataSourceException;

    List<Supplier> getAll() throws DataSourceException;
}
