package controller;

import helper.JDBC;
import helper.loginPageQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.loginPageModel;

import java.net.URL;
import java.util.ResourceBundle;
import java.time.ZoneId;

public class loginPageController implements Initializable {

    @FXML private TextField usernameHandler;
    @FXML private TextField passwordHandler;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void loginHandler() {
        TextField username;
        TextField password;
        Button loginHandler;

    }

    public void zoneIdHandler() {

        System.out.print (ZoneId.systemDefault().getId());
    }

    public void attemptLogin(ActionEvent event) {
        String username = usernameHandler;
        String password = passwordHandler;
    }

    private void displayLoginAlert() {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText("Wrong username or password.");
        alertError.setContentText("Try logging again.");
        alertError.showAndWait();
    }

}
