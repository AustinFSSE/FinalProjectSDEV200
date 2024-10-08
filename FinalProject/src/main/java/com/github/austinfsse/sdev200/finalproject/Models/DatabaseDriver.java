package com.github.austinfsse.sdev200.finalproject.Models;

import java.sql.*;
import java.util.Random;

public class DatabaseDriver {

    private static  String DB_URL = "jdbc:sqlite:javafxbank.db";
    public static String getDbUrl() {
        return DB_URL;
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public boolean verifyDatabaseIsCreated() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            return conn != null; //Database does exist
        } catch (SQLException e) {
            System.out.println("Database not created");
            return false;
        }
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
             PreparedStatement preparedStatement = conn.prepareStatement(createTableSQL)) {
            preparedStatement.execute();
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


            pstmt.executeUpdate();
            System.out.println("Inserted record");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] retrieveRecord(String username) {
        String viewRecordSQL = "SELECT * FROM people WHERE username = ? and password = ?";
        String[] record = new String[7];

        try (Connection conn = DriverManager.getConnection(getDbUrl());
        PreparedStatement pstmt = conn.prepareStatement(viewRecordSQL)) {
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                record[0] = rs.getString("firstname");
                record[1] = rs.getString("lastname");
                record[2] = rs.getString("email");
                record[3] = rs.getString("username");
                record[4] = rs.getString("password");
                record[5] = rs.getString("accountnumber");
                record[6] = rs.getString("balance");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return record;
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
