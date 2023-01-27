package com.example.BudgetDucklings.configuration;

import com.example.BudgetDucklings.database.MySqlDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

@Configuration
public class Config {
    @Bean
    public MySqlDB mySqlDB (){
        return MySqlDB.getInstance();
    }
    @Bean
    public Connection connection(MySqlDB mySqlDB){
        return mySqlDB.getConnection();
    }
}
