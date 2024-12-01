import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class JSONLogger {
    public void logData(TemperatureSensor tempSensor, HumiditySensor humiditySensor) {
        JSONObject logData = new JSONObject();
        logData.put("temperature", tempSensor.getTemperature());
        logData.put("humidity", humiditySensor.getHumidity());
        
        try (FileWriter file = new FileWriter("sensor_data.json", true)) {
            file.write(logData.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
