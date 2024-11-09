// File: User.java
// Package: com.smarthome.users

package com.smarthome.users;

public class User {
    private final String username;
    private final String password;
    private final UserRole role;

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}

