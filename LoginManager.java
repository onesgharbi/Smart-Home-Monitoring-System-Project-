// File: LoginManager.java
// Package: com.smarthome.security

package com.smarthome.security;

import com.smarthome.users.User;
import com.smarthome.users.UserRole;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Manages user authentication for the Smart Home Monitoring System.
 * Verifies username and password and returns a User object if authentication is successful.
 */
public class LoginManager {
    // Predefined users for the system
    private static final Map<String, User> users = new HashMap<>();

    // Static block to initialize sample users
    static {
        users.put("homeowner", new User("homeowner", "password123", UserRole.HOMEOWNER));
        users.put("guard", new User("guard", "password123", UserRole.SECURITY_GUARD));
        users.put("tech", new User("tech", "password123", UserRole.TECHNICIAN));
    }

    /**
     * Authenticates the user by verifying username and password.
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return User object if authentication is successful; otherwise, null.
     */
    public static User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;  // Authentication successful
        }
        return null;  // Authentication failed
    }

    /**
     * Main method to run the authentication process from the console.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Attempt to authenticate the user
        User user = authenticate(username, password);

        // Output result of the authentication
        if (user != null) {
            System.out.println("Login successful for role: " + user.getRole());
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }

        scanner.close();
    }
}

