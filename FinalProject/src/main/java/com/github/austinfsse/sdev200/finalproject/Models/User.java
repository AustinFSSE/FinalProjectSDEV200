package com.github.austinfsse.sdev200.finalproject.Models;

public class User {

    // Static variable to hold the single instance of User
    private static User instance;

    // Fields to store user information
    private String username;
    private String password;
    private String email;
    private String accountNumber;
    private String balance;
    private String firstName;
    private String lastName;

    // Private constructor to prevent instantiation
    private User() {
    }

    // Public method to provide access to the single instance
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    // Getter and Setter methods for user information

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
