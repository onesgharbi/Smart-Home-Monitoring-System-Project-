import java.time.LocalDateTime;

public class Motion {
    private final String location;
    private final LocalDateTime timestamp;
    private final String severity;
    private final double x;
    private final double y;

    public Motion(String location, LocalDateTime timestamp, String severity, double x, double y) {
        this.location = location;
        this.timestamp = timestamp;
        this.severity = severity;
        this.x = x;
        this.y = y;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSeverity() {
        return severity;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
