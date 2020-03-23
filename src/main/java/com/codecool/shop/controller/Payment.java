package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/paymentChose"})
public class Payment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String eMail = req.getParameter("e-mail");
        String country = req.getParameter("country");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        System.out.println(name+" | "+eMail+" | "+country);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        engine.process("product/payMent.html", context, resp.getWriter());




    }
}
