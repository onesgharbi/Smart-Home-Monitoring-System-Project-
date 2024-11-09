// File: Dashboard.java
// Package: com.smarthome.ui

package com.smarthome.ui;

import com.smarthome.sensors.Sensor;
import com.smarthome.users.User;
import com.smarthome.security.Permission;
import com.smarthome.security.LoginManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Dashboard extends Application {
    private User user;

    @Override
    public void start(Stage primaryStage) {
        user = LoginManager.authenticate("homeowner", "password123");  // Example user for testing

        VBox root = new VBox();
        root.getChildren().add(new Label("Welcome, " + user.getUsername()));

        // Display sensor data based on user permissions
        if (LoginManager.hasPermission(user, Permission.ACCESS_HOMEOWNER_AREA)) {
            // Temperature and Humidity for the homeowner
            root.getChildren().add(new Label("Temperature: " + Sensor.getTemperature()));
            root.getChildren().add(new Label("Humidity: " + Sensor.getHumidity()));
        }

        // Motion Detection for the Security Guard
        if (LoginManager.hasPermission(user, Permission.ACCESS_SECURITY_AREA)) {
            root.getChildren().add(new Label("Motion Detected: " + Sensor.detectMotion()));
        }

        // Display lighting status
        root.getChildren().add(new Label("Lighting: " + Sensor.getLightStatus()));

        // Add button to toggle light manually
        Button toggleLightButton = new Button("Toggle Light");
        toggleLightButton.setOnAction(e -> {
            Sensor.toggleLight();  // Toggle light on button click
            updateLightingStatus(root);  // Update lighting status label
        });

        // Simulate light control based on motion detection
        Sensor.controlLightBasedOnMotion();
        root.getChildren().add(new Label("Lighting (auto): " + Sensor.getLightStatus()));

        // Add button to refresh the data or simulate motion-based light control
        Button refreshButton = new Button("Refresh Data");
        refreshButton.setOnAction(e -> {
            Sensor.controlLightBasedOnMotion();  // Update light based on motion detection
            updateLightingStatus(root);  // Update lighting status
        });

        root.getChildren().add(toggleLightButton);
        root.getChildren().add(refreshButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard");
        primaryStage.show();
    }

    private void updateLightingStatus(VBox root) {
        // Update the lighting status label
        root.getChildren().remove(3);  // Remove previous label for lighting status
        root.getChildren().add(new Label("Lighting: " + Sensor.getLightStatus()));  // Add updated label
    }

    public static void main(String[] args) {
        launch(args);
    }
}
