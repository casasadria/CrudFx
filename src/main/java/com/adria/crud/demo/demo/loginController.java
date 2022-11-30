package com.adria.crud.demo.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class loginController {
    private Stage stage;
    private static DataBasePDAO bd = new DataBasePostGreSQLDAO();
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction(ActionEvent event) {
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            loginMessageLabel.setText("Try to search database");
            if(validateLogin()){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("principalMain.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }else{
            loginMessageLabel.setText("The username or password is \nincorrect, please try again");
        }
    }
    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public boolean validateLogin() {
            boolean login = false;
        try {
            login = bd.validateLogin(usernameTextField.getText(), passwordPasswordField.getText());
            if(login) {
                loginMessageLabel.setText("Login correct");

            }else{
                loginMessageLabel.setText("Login incorrect");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return login;
    }
}