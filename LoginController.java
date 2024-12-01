import models.User;
import models.Role;

public class LoginController {
    private MainDashboardController mainDashboardController;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    public LoginController(MainDashboardController mainDashboardController) {
        this.mainDashboardController = mainDashboardController;
    }

    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (isValidUser(username, password)) {
            User loggedInUser = getUser(username, password);
            mainDashboardController.loadDashboard(loggedInUser.getRole());
        } else {
            System.out.println("Invalid username or password");
        }
    }

    private boolean isValidUser(String username, String password) {
        // Simplified for example
        return "user".equals(username) && "pass".equals(password);
    }

    private User getUser(String username, String password) {
        return new User(username, password, Role.HOMEOWNER); // Example hardcoded role
    }
}
