package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.JDBCImplementation.ProductJDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckOut extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {

            WebContext context = new WebContext(req, resp, req.getServletContext());

            CartDaoMem cart = CartDaoMem.getInstance();
            ProductDao productDataStore = new ProductJDBC();

            String userId = req.getSession().getId();
            String productIdToAdd = req.getParameter("productId");
            String productIdToRemove = req.getParameter("RemoveProductId");

            if (productIdToRemove != null) {
                cart.remove(Integer.parseInt(productIdToRemove), userId);
            }

            if (productIdToAdd != null) {
                cart.add(userId, productDataStore.find(Integer.parseInt(productIdToAdd)));
            }


            Map<Product, Integer> userProductListInMap = cart.getUserCartInMap(userId);
            System.out.println(userProductListInMap.toString());

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            context.setVariable("products", userProductListInMap);

            engine.process("product/checkout.html", context, resp.getWriter());

        } catch (IOException | DataSourceException e) {
            e.printStackTrace();

        }

    }
}
