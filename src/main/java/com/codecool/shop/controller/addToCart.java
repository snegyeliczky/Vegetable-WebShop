package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.JDBCImplementation.ProductJDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Product;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addToCart"})
public class addToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CartDao cart = CartDaoMem.getInstance();
            ProductDao productDataStore = new ProductJDBC();

            BufferedReader reader = req.getReader();
            String line = reader.readLine();

            JSONObject jsonObject = new JSONObject(line);
            int prodId = jsonObject.getInt("prodId");

            Product productToAdd = productDataStore.find(prodId);

            String userId = req.getSession().getId();
            cart.add(userId, productToAdd);
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }
}

