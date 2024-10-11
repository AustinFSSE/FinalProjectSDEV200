package com.github.austinfsse.sdev200.finalproject.Models;

public class User {

    // Static variable to hold the single instance of User
    private static User instance;

    // Fields to store user information
    private String username;      // Stores the username of the user
    private String password;      // Stores the password of the user
    private String email;         // Stores the email of the user
    private String accountNumber; // Stores the account number of the user
    private String balance;       // Stores the balance of the user's account
    private String firstName;     // Stores the user's first name
    private String lastName;      // Stores the user's last name

    // Private constructor to prevent instantiation
    // This ensures that the User class can only be accessed through the getInstance() method.
    private User() {
    }

    // Public method to provide access to the single instance
    // This method implements lazy initialization, creating the instance when it's first requested.
    public static User getInstance() {
        if (instance == null) {
            instance = new User(); // Create a new instance if it doesn't already exist.
        }
        return instance; // Return the existing instance.
    }

    // Getter and Setter methods for user information
    public String getUsername() {
        return username; // Returns the user's username.
    }
    public void setUsername(String username) {
        this.username = username; // Sets the user's username.
    }

    public String getPassword() {
        return password; // Returns the user's password.
    }
    public void setPassword(String password) {
        this.password = password; // Sets the user's password.
    }

    public String getEmail() {
        return email; // Returns the user's email.
    }
    public void setEmail(String email) {
        this.email = email; // Sets the user's email.
    }

    public String getAccountNumber() {
        return accountNumber; // Returns the user's account number.
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber; // Sets the user's account number.
    }

    public String getBalance() {
        return balance; // Returns the user's account balance.
    }

    public void setBalance(String balance) {
        this.balance = balance; // Sets the user's account balance.
    }

    public String getFirstName() {
        return firstName; // Returns the user's first name.
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName; // Sets the user's first name.
    }

    public String getLastName() {
        return lastName; // Returns the user's last name.
    }

    public void setLastName(String lastName) {
        this.lastName = lastName; // Sets the user's last name.
    }
}
