package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.JDBCImplementation.ProductCategoryJDBC;
import com.codecool.shop.dao.JDBCImplementation.ProductJDBC;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = new ProductJDBC();
        ProductCategoryDao productCategoryDataStore = new ProductCategoryJDBC();
        WebContext context = new WebContext(req, resp, req.getServletContext());
        CartDao cart = CartDaoMem.getInstance();

        /*
        String productIdToAdd = req.getParameter("productId");
        if (productIdToAdd!=null) {
            int productId=Integer.parseInt(productIdToAdd);
            Product productToAdd = productDataStore.find(productId);
            String userId = req.getSession().getId();
            cart.add(userId,productToAdd);
            System.out.println(req.getSession().getId());
            System.out.println(productDataStore.find(productId).getName());
            System.out.println(productIdToAdd);
        }

         */



        try {

        String productCategory = req.getParameter("categoryId");
        if (productCategory==null || productCategory.equals("null")){
            context.setVariable("products", productDataStore.getAll());
            context.setVariable("category",null);
        }else {
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(productCategory))));
            context.setVariable("category",productCategoryDataStore.find(Integer.parseInt(productCategory)).getName());
            context.setVariable("categoryID",productCategory);
        }


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        context.setVariable("categoryTypes",productCategoryDataStore.getAll());

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }

}
