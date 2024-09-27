package com.github.austinfsse.sdev200.finalproject.Models;

import com.github.austinfsse.sdev200.finalproject.Controllers.Clients.CreateAccountController;

import java.sql.*;
import java.util.Random;

public class DatabaseDriver {

    private static final String DB_URL = "jdbc:sqlite:javafxbank.db";
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS people ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "firstname TEXT NOT NULL, "
                + "lastname TEXT NOT NULL, "
                + "email TEXT NOT NULL, "
                + "username TEXT NOT NULL, "
                + "password TEXT NOT NULL, "
                + "accountnumber TEXT NOT NULL, "
                + "balance INTEGER NOT NULL"
                + ");";

        try (Connection conn = connect();
             Statement statement = conn.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertRecord(String firstName, String lastName, String email, String pwd, StringBuilder usrName) {

        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() ||
                email == null || email.isEmpty() || pwd == null || pwd.isEmpty()) {
            throw new IllegalArgumentException("Input fields cannot be null or empty");
        }

        String accountNumber = generateAccNumber();
        int balance = 0;

        String insertRecordSQL = "INSERT INTO people (firstname, lastname, email, username, password, accountnumber, balance) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertRecordSQL)) {
            // Set parameters
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, usrName.toString()); // Convert StringBuilder to String
            pstmt.setString(5, pwd);
            pstmt.setString(6, accountNumber);
            pstmt.setInt(7, balance);

            createTable(); // Call this once before any insertions
            pstmt.executeUpdate();
            System.out.println("Inserted record");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private String generateAccNumber() {
        StringBuilder accountNumber = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 9; i++) {
            accountNumber.append(rand.nextInt(10));
        }
        return accountNumber.toString();
    }

}
