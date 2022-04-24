package controller;

import helper.appointmentsQuery;
import helper.customerQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.addAppointmentModel;
import model.addCustomerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {

    @FXML private TextField appointmentIdTf;
    @FXML private TextField titleTf;
    @FXML private TextField descriptionTf;
    @FXML private TextField locationTf;
    @FXML private TextField typeTf;
    @FXML private DatePicker startDp;
    @FXML private DatePicker endDp;
    @FXML private TextField startTf;
    @FXML private TextField endTf;
    @FXML private TextField customerIdTf;
    @FXML private TextField useridTf;
    @FXML private Button saveHandler;
    @FXML private ComboBox<String> contactCombo;

    private ObservableList<addAppointmentModel> contactsOb = FXCollections.observableArrayList();

    private ObservableList<String> contacts = FXCollections.observableArrayList();

    private Object selectedCustomerToOpen;


    public void saveAppointment(ActionEvent event) {

        Timestamp start = formatTime(startDp, startTf.getText());
        Timestamp end = formatTime(endDp, endTf.getText());
        int customerId = customerRecordsController.getCustomerToQuery().getCustomerId();

        String title = titleTf.getText();
        String description = descriptionTf.getText();
        String location = locationTf.getText();
        String type = typeTf.getText();
        int contactId = 0;

        int userId = Integer.parseInt(useridTf.getText());

        for (addAppointmentModel item : contactsOb) {
            if (contactCombo.getValue().equals(item.getContactName())) {
                contactId = item.getContactId();
            }
        }
        try {
            appointmentsQuery.insertAppointment(title, description, location, type, start, end, customerId, userId, contactId);
            returnToAppointments(event);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            contactsOb.addAll(appointmentsQuery.getContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (addAppointmentModel item : contactsOb) {
            if (!contacts.contains(item.getContactName())) {
                contacts.add(item.getContactName());
            }
        }
            contactCombo.setItems(contacts);
    }

    private void returnToAppointments (ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerAppointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public Timestamp formatTime(DatePicker date, String time) {
        Timestamp ts = Timestamp.valueOf(date.getValue() + " " + time + ":00.000");
        return ts;
    }
}
