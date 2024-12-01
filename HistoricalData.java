public class HistoricalData {
    private double temperature;
    private double humidity;
    private long timestamp;

    public HistoricalData(double temperature, double humidity, long timestamp) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }
}
