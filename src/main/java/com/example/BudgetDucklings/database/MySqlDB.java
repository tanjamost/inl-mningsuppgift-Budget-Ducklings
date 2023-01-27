package com.example.BudgetDucklings.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDB {
    private static MySqlDB instance;
    private Connection connection;
    private MySqlDB(){
        String link = "jdbc:mysql://localhost:3306/ducklings?createDatabaseIfNotExist=true";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(link, "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static MySqlDB  getInstance() {
        if(instance == null) {
            instance = new MySqlDB();
        }

        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
