package controller;

import helper.appointmentsQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.addAppointmentModel;
import model.appointmentModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This class is the controller for the update appointment view
 */
public class updateAppointmentController implements Initializable {

    private ObservableList<addAppointmentModel> contactsOb = FXCollections.observableArrayList();
    private ObservableList<String> contacts = FXCollections.observableArrayList();
    private ObservableList<appointmentModel> overlapOb =  FXCollections.observableArrayList();

    appointmentModel selectedAppointment;

    private String appointmentId;
    private String selectedName;

    @FXML private TextField updateAppointmentId;
    @FXML private TextField titleTf;
    @FXML private TextField descriptionTf;
    @FXML private TextField locationTf;
    @FXML private ComboBox<String> contactCombo;
    @FXML private TextField typeTf;
    @FXML private TextField startTf;
    @FXML private TextField endTf;
    @FXML private TextField customerIdTf;
    @FXML private TextField userTf;
    @FXML private DatePicker startDp;
    @FXML private DatePicker endDp;


    /**
     *
     * @param customerId passes in the customerId
     * @param startLdt passes in the start time
     * @param endLdt passes in the end time
     * @return True if not overlapping another appointment, false if is
     * @throws SQLException
     */
    private Boolean addAppointmentValidation (String customerId, LocalDateTime startLdt, LocalDateTime endLdt, String testAppointmentId) throws SQLException {

        testAppointmentId = appointmentId;

        if (startLdt.isAfter(endLdt)) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp start = Timestamp.valueOf(formatter.format(startLdt));
        Timestamp end = Timestamp.valueOf(formatter.format(endLdt));

        overlapOb = appointmentsQuery.checkForOverlapOnUpdate(start, end, customerId, testAppointmentId);

        if (overlapOb.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param startTime passes in the start time for the appointment
     * @param endTime passes in the end time for the appointment
     * @param startDate passes in the start date
     * @return True if appointment is within hours, false if not
     */
    private boolean businessHours (String startTime, String endTime, LocalDate startDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime convertStart = LocalTime.parse(startTime, formatter);
        LocalTime convertEnd = LocalTime.parse(endTime, formatter);

        LocalDateTime startDt = LocalDateTime.of(startDate, convertStart);
        LocalDateTime endDt = LocalDateTime.of(startDate, convertEnd);

        ZonedDateTime zonedStartTime = ZonedDateTime.of(LocalDateTime.from(startDt), ZoneId.systemDefault());
        ZonedDateTime zonedEndTime = ZonedDateTime.of(LocalDateTime.from(endDt), ZoneId.systemDefault());

        ZonedDateTime start = ZonedDateTime.of(startDate, LocalTime.of(8,0),
                ZoneId.of("America/New_York"));
        ZonedDateTime end = ZonedDateTime.of(startDate, LocalTime.of(22, 0),
                ZoneId.of("America/New_York"));

        return !(zonedStartTime.isBefore(start) | zonedStartTime.isAfter(end) | zonedEndTime.isBefore(start) | zonedEndTime.isAfter(end) | start.isAfter(end));
    }

    /**
     * Checks for overlapped appointments and that the appointment is within business hours
     * Formats the time and changes it to a timestamp
     * @param event saves inputted appointment information
     * @throws SQLException
     */
    public void saveAppointment(ActionEvent event) throws SQLException {

        boolean appointmentError = true;
        boolean closedHours = true;

        LocalDate startDate = startDp.getValue();
        LocalDate endDate = endDp.getValue();
        String startTime = startTf.getText();
        String endTime = endTf.getText();
        String[] arr = startTf.getText().split(":");
        String[] arr2 = endTf.getText().split(":");

        LocalDateTime startLdt = LocalDateTime.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        LocalDateTime endLdt = LocalDateTime.of(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth(), Integer.parseInt(arr2[0]), Integer.parseInt(arr2[1]));

        String title = titleTf.getText();
        String description = descriptionTf.getText();
        String location = locationTf.getText();
        String type = typeTf.getText();
        int userId = Integer.parseInt(userTf.getText());
        int customerId = Integer.parseInt(customerIdTf.getText());
        int contactId = 0;

        appointmentError = addAppointmentValidation(String.valueOf(customerId), startLdt, endLdt, appointmentId);
        closedHours = businessHours(startTime, endTime, startDate);

        for (addAppointmentModel item : contactsOb) {
            if (contactCombo.getValue().equals(item.getContactName())) {
                contactId = item.getContactId();
            }
        }

        if (!appointmentError) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Overlapped appointment");
            alertError.setContentText("Cannot schedule overlapped appointment, please choose another date or time.");
            alertError.showAndWait();
            return;
        } else if (!closedHours) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText("Outside business hours.");
            alertError.setContentText("The appointment is outside business hours, please choose a different time.");
            alertError.showAndWait();
            return;
        }

        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp start = Timestamp.valueOf((customFormatter.format(startLdt)));
        Timestamp end = Timestamp.valueOf((customFormatter.format(endLdt)));

        try {
            //change order of parameters, should be the same as method call
            appointmentsQuery.updateAppointment(appointmentId, title, description, location, type, start, end, contactId, customerId, userId);
            returnToAppointments(event);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the update appointment view and populates the view with all selected appointment information
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            selectedAppointment = appointmentsController.getAppointmentToQuery();
            appointmentId = selectedAppointment.getAppointmentId();
            appointmentsQuery.editAppointment((selectedAppointment.getAppointmentId()));
            contactsOb.addAll(appointmentsQuery.getContacts());

            for (addAppointmentModel item : contactsOb) {
                if (!contacts.contains(item.getContactName())) {
                    contacts.add(item.getContactName());
                }
            }

            selectedName = (appointmentsQuery.getContactName(selectedAppointment.getAppointmentContactId()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ZonedDateTime startUtc = selectedAppointment.getAppointmentStart().toInstant().atZone(ZoneOffset.UTC);
        ZonedDateTime endUtc = selectedAppointment.getAppointmentEnd().toInstant().atZone(ZoneOffset.UTC);

        ZonedDateTime localStart = startUtc.withZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime localEnd = endUtc.withZoneSameInstant(ZoneId.systemDefault());

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String localStartTime = localStart.format(timeFormatter);
        String localEndTime = localEnd.format(timeFormatter);


        titleTf.setText(String.valueOf(selectedAppointment.getAppointmentTitle()));
        descriptionTf.setText(String.valueOf(selectedAppointment.getAppointmentDescription()));
        locationTf.setText(String.valueOf(selectedAppointment.getAppointmentLocation()));
        typeTf.setText(String.valueOf(selectedAppointment.getAppointmentType()));
        userTf.setText(String.valueOf(selectedAppointment.getAppointmentUserId()));
        customerIdTf.setText(String.valueOf(selectedAppointment.getAppointmentCustomerId()));
        contactCombo.setItems(contacts);
        contactCombo.getSelectionModel().select(contacts.indexOf(selectedName));

        startTf.setText(localStartTime);
        endTf.setText(localEndTime);
        startDp.setValue(selectedAppointment.getAppointmentStart().toLocalDateTime().toLocalDate());
        endDp.setValue(selectedAppointment.getAppointmentEnd().toLocalDateTime().toLocalDate());

        startDp.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate startDp, boolean empty) {
                super.updateItem(startDp, empty);
                setDisable(empty || startDp.getDayOfWeek() == DayOfWeek.SATURDAY || startDp.getDayOfWeek() == DayOfWeek.SUNDAY || startDp.isBefore(LocalDate.now()));
            }
        });

        endDp.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate endDp, boolean empty) {
                super.updateItem(endDp, empty);
                setDisable(empty || endDp.getDayOfWeek() == DayOfWeek.SATURDAY || endDp.getDayOfWeek() == DayOfWeek.SUNDAY || endDp.isBefore(LocalDate.now()));
            }
        });
    }

    /**
     *
     * @param event returns the view back to the customer appointments view
     * @throws IOException
     */
    private void returnToAppointments (ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerAppointments.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}