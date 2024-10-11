package com.github.austinfsse.sdev200.finalproject.Models;

import java.sql.*;

// Class to validate login credentials against a SQLite database
public class ValidatingLoginCredentials {
    public static void main(String[] args) {
        // SQL query to select username and password from the 'people' table
        String query = "SELECT username, password FROM people";  // Replace with your table and query
        String databaseUrl = "jdbc:sqlite:javafxbank.db";  // The URL for the SQLite database

        // Try-with-resources to ensure resources are closed automatically
        try (Connection conn = DriverManager.getConnection(databaseUrl); // Establish connection to the database
             PreparedStatement pstmt = conn.prepareStatement(query); // Prepare the SQL query
             ResultSet rs = pstmt.executeQuery()) { // Execute the query and get results

            // Get the number of columns in the result set
            int columnCount = rs.getMetaData().getColumnCount();
            // Iterate through each row of the result set
            while (rs.next()) {
                // Loop through each column in the current row
                for (int i = 1; i <= columnCount; i++) {
                    // Get the column name
                    String columnName = rs.getMetaData().getColumnName(i);
                    // Retrieve the value of the current column
                    String value = rs.getString(i); // Retrieve value by column index
                    // Print column name and value on the same line
                    System.out.print(columnName + ": " + value + "\t");  // Print without newline
                }
                // Print a newline after each row for readability
                System.out.println();
            }

        } catch (SQLException e) {
            // Handle SQL exceptions by throwing a runtime exception
            throw new RuntimeException(e);
        }
    }
}
