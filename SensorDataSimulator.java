// File: Main.java
// Package: com.smarthome

package com.smarthome;

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

   
