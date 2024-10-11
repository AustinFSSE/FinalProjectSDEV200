package com.github.austinfsse.sdev200.finalproject.Models;

import java.sql.*;
import java.util.Random;

@SuppressWarnings("ALL")
public class DatabaseDriver {

    // Static final variable holding the database URL.
    // This follows the Singleton pattern, as the same database connection URL is shared across the entire application.
    private static final String DB_URL = "jdbc:sqlite:javafxbank.db";

    // Getter method to retrieve the database URL.
    // It ensures that other classes can access the database URL without modifying it.
    public static String getDbUrl() {
        return DB_URL;
    }

    // Method to establish a connection to the SQLite database.
    // This is used throughout the class for database operations.
    public Connection connect() {
        Connection conn = null;
        try {
            // Use DriverManager to get a connection using the provided DB_URL.
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Log any SQL exceptions.
        }
        return conn;
    }

    // Method to verify if the database is created and accessible.
    // Returns true if the connection is successful, false otherwise.
    public boolean verifyDatabaseIsCreated() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            return conn != null;  // If connection is successful, return true (database exists).
        } catch (SQLException e) {
            System.out.println("Database not created");  // If any SQL exception occurs, log it and return false.
            return false;
        }
    }

    // Method to create a new table named 'people' in the database if it doesn't already exist.
    public void createTable() {
        // SQL query to create the 'people' table.
        String createTableSQL = "CREATE TABLE IF NOT EXISTS people ("
                + "id INTEGER AUTOINCREMENT, "  // ID column with auto-increment.
                + "firstname TEXT NOT NULL, "   // First name field (required).
                + "lastname TEXT NOT NULL, "    // Last name field (required).
                + "email TEXT NOT NULL, "       // Email field (required).
                + "username TEXT PRIMARY KEY NOT NULL, "  // Username is the primary key (required).
                + "password TEXT NOT NULL, "    // Password field (required).
                + "accountnumber TEXT NOT NULL, "  // Account number field (required).
                + "balance INTEGER NOT NULL"    // Balance field (required).
                + ");";

        try (Connection conn = connect();  // Use connect() to establish a connection.
             PreparedStatement preparedStatement = conn.prepareStatement(createTableSQL)) {
            // Execute the table creation query.
            preparedStatement.execute();
            System.out.println("Table created");  // Log success.
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Log SQL exceptions.
        }
    }

    // Method to insert a new record (user) into the 'people' table.
    public void insertRecord(String firstName, String lastName, String email, String pwd, StringBuilder usrName) {

        // Ensure all input fields are provided, else throw an exception.
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() ||
                email == null || email.isEmpty() || pwd == null || pwd.isEmpty()) {
            throw new IllegalArgumentException("Input fields cannot be null or empty");
        }

        // Generate a random account number for the user.
        String accountNumber = generateAccNumber();
        // Default initial balance set to 0.
        int balance = 0;

        // SQL query to insert a new record into the 'people' table.
        String insertRecordSQL = "INSERT INTO people (firstname, lastname, email, username, password, accountnumber, balance) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = connect();  // Use connect() to establish a connection.
             PreparedStatement pstmt = conn.prepareStatement(insertRecordSQL)) {
            // Set parameters for the insert query.
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, usrName.toString());  // Convert StringBuilder to String for username.
            pstmt.setString(5, pwd);
            pstmt.setString(6, accountNumber);
            pstmt.setInt(7, balance);

            // Execute the query to insert the record.
            pstmt.executeUpdate();
            System.out.println("Inserted record");  // Log success.
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Log SQL exceptions.
        }
    }

    // Method to retrieve a user record based on the provided username.
    public String[] retrieveRecord(String username) {
        // SQL query to select a record from 'people' where the username matches.
        String viewRecordSQL = "SELECT * FROM people WHERE username = ?";
        String[] record = new String[7];  // Array to store retrieved record data.

        try (Connection conn = DriverManager.getConnection(getDbUrl());  // Establish connection.
             PreparedStatement pstmt = conn.prepareStatement(viewRecordSQL)) {
            // Set the username parameter in the query.
            pstmt.setString(1, username);

            // Execute the query and store the result in a ResultSet.
            ResultSet rs = pstmt.executeQuery();
            // Iterate through the result and populate the record array.
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
            System.out.println(e.getMessage());  // Log SQL exceptions.
        }
        return record;  // Return the retrieved record.
    }

    // Method to update a user's balance in the database based on their username.
    public void updateBalance(String username, int newBalance) {
        // SQL query to update the balance field where the username matches.
        String updateBalanceSQL = "UPDATE people SET balance = ? WHERE username = ?;";
        try (Connection conn = connect();  // Establish connection.
             PreparedStatement pstmt = conn.prepareStatement(updateBalanceSQL)) {
            // Set the new balance and username parameters in the query.
            pstmt.setInt(1, newBalance);
            pstmt.setString(2, username);
            // Execute the update query.
            pstmt.executeUpdate();
            System.out.println("Balance updated for user: " + username);  // Log success.
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Log SQL exceptions.
        }
    }

    // Helper method to generate a random 9-digit account number.
    private String generateAccNumber() {
        StringBuilder accountNumber = new StringBuilder();
        Random rand = new Random();
        // Generate a 9-digit random account number.
        for (int i = 0; i < 9; i++) {
            accountNumber.append(rand.nextInt(10));
        }
        return accountNumber.toString();
    }
}
