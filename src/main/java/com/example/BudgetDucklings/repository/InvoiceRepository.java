package com.example.BudgetDucklings.repository;

import com.example.BudgetDucklings.model.PaymentDetails;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceRepository {
    private Connection connection;

    public InvoiceRepository(Connection connection) {
        this.connection = connection;
    }

    public void store(PaymentDetails paymentDetails, String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into payments values (default, ?,?,?,?,?,?)");
            preparedStatement.setString(1, paymentDetails.getTitle());
            preparedStatement.setDate(2, paymentDetails.getDate());
            preparedStatement.setString(3, paymentDetails.getDescription());
            preparedStatement.setString(4, paymentDetails.getCategory());
            preparedStatement.setDouble(5, paymentDetails.getSum());
            preparedStatement.setString(6, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PaymentDetails> findByUsers(String username) {
        List<PaymentDetails> paymentDetailsList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from payments WHERE author=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaymentDetails payment = new PaymentDetails();
                payment.setId(resultSet.getInt("id"));
                payment.setDate(resultSet.getDate("date"));
                payment.setCategory(resultSet.getString("category"));
                payment.setTitle(resultSet.getString("title"));
                payment.setDescription(resultSet.getString("description"));
                payment.setSum(resultSet.getDouble("sum"));
                paymentDetailsList.add(payment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentDetailsList;
    }
}
