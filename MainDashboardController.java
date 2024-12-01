package controllers;

import javafx.scene.control.Label;
import models.*;

public class MainDashboardController {
    private TemperatureSensor tempSensor = new TemperatureSensor();
    private HumiditySensor humiditySensor = new HumiditySensor();
    private MotionSensor motionSensor = new MotionSensor();
    private LightSensor lightSensor = new LightSensor();

    private Label temperatureLabel;
    private Label humidityLabel;
    private Label motionStatusLabel;
    private Label lightLevelLabel;

    public void loadDashboard(Role role) {
        if (role == Role.HOMEOWNER) {
            loadHomeownerDashboard();
        } else if (role == Role.SECURITY_GUARD) {
            loadSecurityGuardDashboard();
        } else if (role == Role.TECHNICIAN) {
            loadTechnicianDashboard();
        }
    }

    private void loadHomeownerDashboard() {
        temperatureLabel.setText("Temperature: " + tempSensor.getTemperature() + "°C");
        humidityLabel.setText("Humidity: " + humiditySensor.getHumidity() + "%");
        lightLevelLabel.setText("Light Level: " + lightSensor.getLightLevel() + " lux");
    }

    private void loadSecurityGuardDashboard() {
        motionStatusLabel.setText("Motion Detected: " + motionSensor.isMotionDetected());
    }

    private void loadTechnicianDashboard() {
        temperatureLabel.setText("Temperature: " + tempSensor.getTemperature() + "°C");
        humidityLabel.setText("Humidity: " + humiditySensor.getHumidity() + "%");
        lightLevelLabel.setText("Light Level: " + lightSensor.getLightLevel() + " lux");
    }
}
