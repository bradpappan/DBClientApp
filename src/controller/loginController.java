package controller;

import helper.loginPageQuery;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.time.ZoneId;

public class loginController implements Initializable {

    @FXML private TextField usernameTf;
    @FXML private TextField passwordTf;
    @FXML private Label zoneIdLbl;
    @FXML private Button loginBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initializeZoneId();
        // Changed to lambda expression
        loginBtn.setOnAction(event -> {
            try {
                loginPageQuery.selectUser(event, usernameTf.getText(), passwordTf.getText());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void initializeZoneId() {
        zoneIdLbl.setText(ZoneId.systemDefault().getId());
    }
}
