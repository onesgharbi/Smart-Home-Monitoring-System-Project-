// File: Main.java
// Package: com.smarthome

package com.smarthome;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Main {

    // Enum representing user roles in the system
    public enum UserRole {
        HOMEOWNER, SECURITY_GUARD, TECHNICIAN
    }

    // Class representing a user
    public static class User {
        private String username;
        private String password;
        private UserRole role;

        public User(String username, String password, UserRole role) {
            this.username = username;
            this.password = password;
            this.role = role;
        }

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public UserRole getRole() { return role; }
    }

    // LoginManager for handling user authentication
    public static class LoginManager {
        // Predefined users
        private static final Map<String, User> users = new HashMap<>();

        static {
            users.put("homeowner", new User("homeowner", "password123", UserRole.HOMEOWNER));
            users.put("guard", new User("guard", "password123", UserRole.SECURITY_GUARD));
            users.put("tech", new User("tech", "password123", UserRole.TECHNICIAN));
        }

        public static User authenticate(String username, String password) {
            User user = users.get(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            return null;
        }
    }
 // SensorDataSimulator for simulating real-time sensor data
    public static class SensorDataSimulator {
        private final DoubleProperty temperature = new SimpleDoubleProperty();
        private final DoubleProperty humidity = new SimpleDoubleProperty();
        private final DoubleProperty motionDetected = new SimpleDoubleProperty();
        private final DoubleProperty lighting = new SimpleDoubleProperty();

        public SensorDataSimulator() {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        temperature.set(Math.random() * 40); // Temperature: 0-40°C
                        humidity.set(Math.random() * 100);   // Humidity: 0-100%
                        motionDetected.set(Math.random() < 0.1 ? 1 : 0); // 10% chance of motion
                        lighting.set(Math.random() * 100);   // Lighting: 0-100%
                    });
                }
            }, 0, 1000); // Update every second
        }

        public DoubleProperty temperatureProperty() { return temperature; }
        public DoubleProperty humidityProperty() { return humidity; }
        public DoubleProperty motionDetectedProperty() { return motionDetected; }
        public DoubleProperty lightingProperty() { return lighting; }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: User Login with console input
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = LoginManager.authenticate(username, password);
        if (user != null) {
            System.out.println("Login successful for: " + user.getRole());
        } else {
            System.out.println("Login failed.");
            return;
        }

        // Step 2: Initialize Sensor Data Simulation
        System.out.println("Starting sensor data simulation...");
        SensorDataSimulator sensorSimulator = new SensorDataSimulator();

        // Display sensor data updates
        sensorSimulator.temperatureProperty().addListener((obs, oldTemp, newTemp) -> {
            System.out.println("Temperature updated: " + newTemp);
        });
        sensorSimulator.humidityProperty().addListener((obs, oldHumidity, newHumidity) -> {
            System.out.println("Humidity updated: " + newHumidity);
        });
        sensorSimulator.motionDetectedProperty().addListener((obs, oldMotion, newMotion) -> {
            System.out.println("Motion detected: " + (newMotion.intValue() == 1 ? "Yes" : "No"));
        });
        sensorSimulator.lightingProperty().addListener((obs, oldLighting, newLighting) -> {
            System.out.println("Lighting updated: " + newLighting);
        });

        // Keep the program running until user presses Enter
        System.out.println("Press Enter to stop the simulation...");
        scanner.nextLine();
        System.out.println("Sensor simulation stopped.");
    }
}

   
