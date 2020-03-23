package com.codecool.shop.controller;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/paymentForm"})
public class PaymentFormApi extends HttpServlet {
    private Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String template = Resources.toString(Resources.getResource("addPaymentData.html"), Charsets.UTF_8);
        System.out.println(template);
        String itemJson = this.gson.toJson(template);

        System.out.println(itemJson);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(itemJson);
        out.flush();




    }
}
