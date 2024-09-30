package com.github.austinfsse.sdev200.finalproject.Models;

import java.sql.*;
import java.util.Objects;

public class ValidatingLoginCredentials {
    public static void main(String[] args) {
        String query = "SELECT username, password FROM people";  // Replace with your table and query
        String databaseUrl = "jdbc:sqlite:javafxbank.db";  // Replace with your database URL

        try (Connection conn = DriverManager.getConnection(databaseUrl);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Get the number of columns
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                // Loop through each column in the current row
                for (int i = 1; i <= columnCount; i++) {
                    // Print column name and value
                    String columnName = rs.getMetaData().getColumnName(i);
                    String value = rs.getString(i); // Retrieve value by column index
                    System.out.print(columnName + ": " + value + "\t");  // Print without newline
                }
                System.out.println();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

