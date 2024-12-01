package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import models.Role;

import java.util.ArrayList;
import java.util.List;

public class SignUpController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField roleField;

    private static List<User> userDatabase = new ArrayList<>();

    public void handleSignUp() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String roleInput = roleField.getText().toUpperCase();

        try {
            Role role = Role.valueOf(roleInput);
            if (isUsernameAvailable(username)) {
                User newUser = new User(username, password, role);
                userDatabase.add(newUser);
                System.out.println("Signup successful! User added.");
            } else {
                System.out.println("Username is already taken.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role. Please enter HOMEOWNER, SECURITY_GUARD, or TECHNICIAN.");
        }
    }

    private boolean isUsernameAvailable(String username) {
        return userDatabase.stream().noneMatch(user -> user.getUsername().equals(username));
    }
}
