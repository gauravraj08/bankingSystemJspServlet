package com.first.bms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MiniBean {
    private String pin;
    private String transactions;
    private String balance;

    public MiniBean(String pin) {
        this.pin = pin;
        fetchTransactionsAndBalance(pin);
    }

    private void fetchTransactionsAndBalance(String pin) {
        PreparedStatement statement = null;
        try {
            StringBuilder transactionsBuilder = new StringBuilder();
            int balance = 0;
            int serialNumber = 1;
            Connection c = DatabaseConnection.getConnection();
            String query = "select * from bank where pin = ?";
            statement = c.prepareStatement(query);
            statement.setString(1, pin);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String transaction = String.format("%d. %s %s %s\n", 
                                                    serialNumber++, 
                                                    resultSet.getString("date"), 
                                                    resultSet.getString("type"), 
                                                    resultSet.getString("amount"));
                transactionsBuilder.append(transaction);

                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }

            this.transactions = transactionsBuilder.toString();
            this.balance = String.format("Your Total Balance is Rs %d", balance);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close statement and database connection
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getTransactions() {
        return transactions;
    }

    public String getBalance() {
        return balance;
    }
}
