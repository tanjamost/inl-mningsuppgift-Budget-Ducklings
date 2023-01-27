package com.example.BudgetDucklings.repository;

import com.example.BudgetDucklings.model.UserDetails;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AuthRep {
    private Connection connection;
    public AuthRep(Connection connection) {
        this.connection = connection;
    }

    public UserDetails findAllByUsername(UserDetails user){
        UserDetails actualUser = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users WHERE username=?");
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                actualUser = new UserDetails();
                actualUser.setUsername(resultSet.getString("username"));
                actualUser.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return actualUser;

    }
}
