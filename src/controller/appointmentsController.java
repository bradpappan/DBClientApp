package controller;

import helper.appointmentsQuery;
import helper.loginPageQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.appointmentModel;
import helper.modifyAppointmentsQuery;
import model.customerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the main controller for the appointment table view
 */
public class appointmentsController implements Initializable {

    public static customerModel selectedCustomerToOpen;

    @FXML
    public TableView<appointmentModel> appointmentSchedulesTable;
    @FXML
    private TableColumn<appointmentModel, Integer> appointmentIdColumn;
    @FXML
    private TableColumn<appointmentModel, String> appointmentTitleColumn;
    @FXML
    private TableColumn<appointmentModel, String> appointmentDescriptionColumn;
    @FXML
    private TableColumn<appointmentModel, String> appointmentLocationColumn;
    @FXML
    private TableColumn<appointmentModel, String> appointmentTypeColumn;
    @FXML
    private TableColumn<appointmentModel, Date> appointmentStartColumn;
    @FXML
    private TableColumn<appointmentModel, Date> appointmentEndColumn;
    @FXML
    private TableColumn<appointmentModel, Integer> appointmentCustomerIdColumn;
    @FXML
    private TableColumn<appointmentModel, Integer> appointmentUserIdColumn;
    @FXML
    private TableColumn<appointmentModel, Integer> appointmentContactIdColumn;

    ObservableList<appointmentModel> appointmentSchedule = FXCollections.observableArrayList();


    public static appointmentModel selectedAppointment;

    /**
     * Initializes the appointments table view and populates the tables with selected customer appointments
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
    }

    private void initTable() {
        selectedCustomerToOpen = customerRecordsController.getCustomerToQuery();
        appointmentSchedule = FXCollections.observableArrayList();
                
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        appointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentStart"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentEnd"));
        appointmentCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentCustomerId"));
        appointmentUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentUserId"));
        appointmentContactIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentContactId"));

        try {
            appointmentSchedule.addAll(modifyAppointmentsQuery.getAllAppointments(String.valueOf(selectedCustomerToOpen.getCustomerId())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        appointmentSchedulesTable.setItems(appointmentSchedule);
    }

    /**
     * Deletes the selected appointment
     * @throws SQLException
     */
    public void deleteAppointment() throws SQLException {
        selectedAppointment = appointmentSchedulesTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment == null) {
            displayCustomAlert("Part not selected", "Please select a part");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Attention");
            alert.setContentText("Are you sure you want to delete Appointment ID: " + selectedAppointment.getAppointmentId() + " and Type: " + selectedAppointment.getAppointmentType());
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                modifyAppointmentsQuery.deleteAppointment(selectedAppointment.getAppointmentId());
                initTable();
            }
        }
    }

    /**
     *
     * @param event changes to the add appointment screen
     */
    public void addAppointment(ActionEvent event) {
        Parent parent;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(loginPageQuery.class.getResource("/view/addAppointment.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event changes to the edit appointment screen
     */
    public void editAppointment(ActionEvent event) {
        selectedAppointment = appointmentSchedulesTable.getSelectionModel().getSelectedItem();
        Parent parent;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(loginPageQuery.class.getResource("/view/updateAppointment.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return the selected appointment in the table view
     */
    public static appointmentModel getAppointmentToQuery() {
        return selectedAppointment;
    }

    /**
     *
     * @param event exits back to the customer records screen
     */
    public void exit(ActionEvent event) {

        Parent parent;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(loginPageQuery.class.getResource("/view/customerRecords.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param event filters the appointments by showing the next 7 days of appointments
     * @throws SQLException
     */
    public void weeksBtn(ActionEvent event) throws SQLException {

        ObservableList<appointmentModel> appointmentFilter = FXCollections.observableArrayList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ZonedDateTime startWeekDate = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime endWeekDate = startWeekDate.plusWeeks(1);

        Timestamp start = Timestamp.valueOf(formatter.format(startWeekDate));
        Timestamp end = Timestamp.valueOf(formatter.format(endWeekDate));

        appointmentFilter = appointmentsQuery.thisWeeksAppointments(start, end);

        showAppointments(appointmentFilter);

    }

    /**
     *
     * @param event filters the appointments by showing the next month of appointments
     * @throws SQLException
     */
    public void monthsBtn(ActionEvent event) throws SQLException {

        ObservableList<appointmentModel> appointmentFilter = FXCollections.observableArrayList();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ZonedDateTime startWeekDate = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime endWeekDate = startWeekDate.plusMonths(1);

        Timestamp start = Timestamp.valueOf(formatter.format(startWeekDate));
        Timestamp end = Timestamp.valueOf(formatter.format(endWeekDate));

        appointmentFilter = appointmentsQuery.thisWeeksAppointments(start, end);

        showAppointments(appointmentFilter);

    }

        /**
         * Custom alert that can be used to display an error
         * @param header can input any message
         * @param content can input any message
         */
        private static void displayCustomAlert(String header, String content) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Error");
            alertError.setHeaderText(header);
            alertError.setContentText(content);
            alertError.showAndWait();
        }

    /**
     *
     * @param appointmentList sets the table view to the filtered appointments, either by month or week
     */
    public void showAppointments(ObservableList<appointmentModel> appointmentList) {
        appointmentSchedulesTable.setItems(appointmentList);
    }
}