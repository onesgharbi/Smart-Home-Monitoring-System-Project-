// File: LoginManager.java
// Package: com.smarthome.security

package com.smarthome.security;

import com.smarthome.users.User;
import com.smarthome.users.UserRole;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Manages user authentication and access control for the Smart Home Monitoring System.
 */
public class LoginManager {
    private static final Map<String, User> users = new HashMap<>();

    static {
        users.put("homeowner", new User("homeowner", "password123", UserRole.HOMEOWNER));
        users.put("guard", new User("guard", "password123", UserRole.SECURITY_GUARD));
        users.put("tech", new User("tech", "password123", UserRole.TECHNICIAN));
    }

    /**
     * Authenticates a user based on their username and password.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return A User object if authentication is successful; null otherwise.
     */
    public static User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;  // Authentication successful
        }
        return null;  // Authentication failed
    }

    /**
     * Checks if the user has permission to access homeowner functions.
     * @param user The authenticated user.
     * @return True if the user has access; otherwise, false.
     */
    public static boolean accessHomeownerArea(User user) {
        return user.getRole() == UserRole.HOMEOWNER;
    }

    /**
     * Checks if the user has permission to access security functions.
     * @param user The authenticated user.
     * @return True if the user has access; otherwise, false.
     */
    public static boolean accessSecurityArea(User user) {
        return user.getRole() == UserRole.SECURITY_GUARD || user.getRole() == UserRole.HOMEOWNER;
    }

    /**
     * Checks if the user has permission to access technician functions.
     * @param user The authenticated user.
     * @return True if the user has access; otherwise, false.
     */
    public static boolean accessTechnicianArea(User user) {
        return user.getRole() == UserRole.TECHNICIAN || user.getRole() == UserRole.HOMEOWNER;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = authenticate(username, password);

        if (user != null) {
            System.out.println("Login successful for role: " + user.getRole());

            // Check access levels based on user role
            if (accessHomeownerArea(user)) {
                System.out.println("Access granted to Homeowner area.");
            } else {
                System.out.println("Access denied to Homeowner area.");
            }

            if (accessSecurityArea(user)) {
                System.out.println("Access granted to Security area.");
            } else {
                System.out.println("Access denied to Security area.");
            }

            if (accessTechnicianArea(user)) {
                System.out.println("Access granted to Technician area.");
            } else {
                System.out.println("Access denied to Technician area.");
            }
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }

        scanner.close();
    }
}


