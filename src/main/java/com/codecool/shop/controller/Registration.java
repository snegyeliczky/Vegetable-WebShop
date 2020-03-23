package com.codecool.shop.controller;

import com.codecool.shop.dao.DataSourceException;
import com.codecool.shop.dao.JDBCImplementation.UserJDBC;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BufferedReader reader = req.getReader();
            String line = reader.readLine();
            UserJDBC userJDBC = new UserJDBC();

            JSONObject jsonObject = new JSONObject(line);

            String name = jsonObject.getString("name");
            String email = jsonObject.getString("email");
            String password = jsonObject.getString("password");
            System.out.println(name);
            System.out.println(email);
            System.out.println(password);
            userJDBC.addUser(name, email, password);


        } catch (DataSourceException e) {
            e.printStackTrace();
        }
    }
}

