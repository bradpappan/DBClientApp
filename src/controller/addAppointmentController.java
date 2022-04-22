package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {

    @FXML private TextField addAppointmentId;
    @FXML private TextField addAppointmentTitle;
    @FXML private TextField addAppointmentDescription;
    @FXML private TextField addAppointmentLocation;
    @FXML private TextField addAppointmentType;
    @FXML private TextField addAppointmentStartDate;
    @FXML private TextField addAppointmentEndDate;
    @FXML private TextField addAppointmentCustomerId;
    @FXML private TextField addAppointmentUserId;
    @FXML private Button addAppointmentSaveHandler;
    @FXML private ComboBox addAppointmentContact;

    public void saveAppointment(ActionEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void returnToAppointments (ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/addAppointment.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
