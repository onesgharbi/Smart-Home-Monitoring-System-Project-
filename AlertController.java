public class AlertController {
    private TemperatureSensor tempSensor;
    private HumiditySensor humiditySensor;

    public AlertController(TemperatureSensor tempSensor, HumiditySensor humiditySensor) {
        this.tempSensor = tempSensor;
        this.humiditySensor = humiditySensor;
    }

    public void checkEnvironmentalNotifications() {
        if (tempSensor.getTemperature() > 30) {
            showNotification("Warning: High Temperature!");
        }
        if (humiditySensor.getHumidity() < 30) {
            showNotification("Warning: Low Humidity!");
        }
    }

    private void showNotification(String message) {
        System.out.println(message); // Replace with UI notification
    }
}
