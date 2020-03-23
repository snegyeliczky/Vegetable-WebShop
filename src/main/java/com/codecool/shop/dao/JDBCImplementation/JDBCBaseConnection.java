package com.codecool.shop.dao.JDBCImplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JDBCBaseConnection {

    private static final String DATABASE = System.getenv("DATABASE");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
    }


}
