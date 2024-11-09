package InitialSensorsIntegration;


import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

public class InitialSensorsIntegration {

	

	    private static final Random random = new Random();
	    private static final Timer timer = new Timer();

	    private static double getMockTemperature() {
	        return 20.0 + (10.0 * random.nextDouble());
	    }

	    private static double getMockHumidity() {
	        return 40.0 + (20.0 * random.nextDouble());
	    }

	    private static boolean detectMockMotion() {
	        return random.nextBoolean();
	    }

	    private static <T> void scheduleSensorUpdate(Supplier<T> sensorFunction, String label, long intervalMs) {
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                T sensorData = sensorFunction.get();
	                System.out.println(label + ": " + sensorData + (label.equals("Motion Detected") ? " at " + new Date() : ""));
	            }
	        }, 0, intervalMs);
	    }

	    public static void main(String[] args) {
	        scheduleSensorUpdate(SensorIntegration::getMockTemperature, "Temperature", 2000);
	        scheduleSensorUpdate(SensorIntegration::getMockHumidity, "Humidity", 2000);
	        scheduleSensorUpdate(() -> detectMockMotion() ? "Motion Detected" : "", "Motion Detected", 1000);
	    }
	}
