package com.example.practice.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Hibernate<T> {
    private Connection connection;

    public Hibernate() throws SQLException {
        connection = DriverManager.getConnection("");
    }

    public void write(T t) {

    }
}
