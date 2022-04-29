package controller;

import helper.loginPageQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.appointmentModel;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.time.ZoneId;

/**
 * This class is the controller for the login page
 */
public class loginController implements Initializable {

    private ObservableList<appointmentModel> appointments = FXCollections.observableArrayList();

    @FXML private TextField usernameTf;
    @FXML private TextField passwordTf;
    @FXML private Label zoneIdLbl;
    @FXML private Button loginBtn;

    /**
     * Initializes the login page, runs the query for the login attempt, and shows an alert for appointments with 15 minutes
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            appointments = loginPageQuery.appointmentWarning();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        initializeZoneId();
        loginBtn.setOnAction(event -> {
            try {
                boolean login = loginPageQuery.selectUser(event, usernameTf.getText(), passwordTf.getText());
                loggerController.logger(usernameTf.getText(), login);

                if (appointments.isEmpty()) {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Attention");
                    alertError.setHeaderText("No appointments within 15 minutes");
                    alertError.setContentText("There are currently no appointments within 15 minutes of current login.");
                    alertError.showAndWait();
                } else {
                    for (appointmentModel appt : appointments) {
                        Alert alertError = new Alert(Alert.AlertType.ERROR);
                        alertError.setTitle("Attention");
                        alertError.setHeaderText("List of appointments");
                        alertError.setContentText("Appointments within 15 minutes are: Appointment ID = " + appt.getAppointmentId() + ", Starts at " + appt.getAppointmentStart().toString());
                        alertError.showAndWait();
                    }
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Sets the zone id label to system default
     */
    public void initializeZoneId() {
        zoneIdLbl.setText(ZoneId.systemDefault().getId());
    }
}
