import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.time.LocalDateTime;

public class DashboardController {
    private final ObservableList<Motion> motionData = FXCollections.observableArrayList();
    private final FilteredList<Motion> filteredData = new FilteredList<>(motionData, p -> true);
    private final VBox alertPanel = new VBox(10);

    public HBox createFilterBar() {
        HBox filterBar = new HBox(10);
        filterBar.setPadding(new Insets(10));

        ComboBox<String> severityFilter = new ComboBox<>(FXCollections.observableArrayList("All", "High", "Medium", "Low"));
        severityFilter.setValue("All");

        severityFilter.setOnAction(e -> {
            String selected = severityFilter.getValue();
            filteredData.setPredicate(motion -> "All".equals(selected) || motion.getSeverity().equals(selected));
        });

        filterBar.getChildren().addAll(new Label("Filter by Severity:"), severityFilter);
        return filterBar;
    }

    public VBox createAlertPanel() {
        alertPanel.setPadding(new Insets(10));
        alertPanel.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f8f8f8;");
        alertPanel.getChildren().add(new Label("Real-Time Alerts"));
        return alertPanel;
    }

    public StackPane createMotionMonitoringPane() {
        ImageView mapView = new ImageView(new Image("file:resources/map.png"));
        Group motionOverlay = new Group();

        motionData.addListener((observable, oldValue, newValue) -> {
            motionOverlay.getChildren().clear();
            for (Motion motion : motionData) {
                Circle marker = new Circle(motion.getX(), motion.getY(), 5);
                switch (motion.getSeverity()) {
                    case "High" -> marker.setFill(Color.RED);
                    case "Medium" -> marker.setFill(Color.YELLOW);
                    default -> marker.setFill(Color.GREEN);
                }
                motionOverlay.getChildren().add(marker);
            }
        });

        return new StackPane(mapView, motionOverlay);
    }

    public void startRealTimeAlerts() {
        Timeline alertTimeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> {
                    Motion motion = new Motion("Zone A", LocalDateTime.now(), "High", 100, 150);
                    motionData.add(motion);

                    HBox alertBox = new HBox(10);
                    Label alertLabel = new Label(motion.getTimestamp() + " - High Alert: Motion in " + motion.getLocation());
                    Button ackButton = new Button("Acknowledge");
                    ackButton.setOnAction(ev -> alertPanel.getChildren().remove(alertBox));

                    alertBox.getChildren().addAll(alertLabel, ackButton);
                    alertPanel.getChildren().add(alertBox);
                })
        );
        alertTimeline.setCycleCount(Timeline.INDEFINITE);
        alertTimeline.play();
    }
}
