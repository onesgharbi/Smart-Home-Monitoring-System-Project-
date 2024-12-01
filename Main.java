import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SecurityDashboardApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Create Dashboard UI
        DashboardController controller = new DashboardController();
        root.setTop(controller.createFilterBar());
        root.setCenter(controller.createMotionMonitoringPane());
        root.setRight(controller.createAlertPanel());

        // Set the scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Security Guard Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Start real-time alerts
        controller.startRealTimeAlerts();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

