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
import java.time.zone.ZoneRulesProvider;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

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

    public void deleteAppointment() throws SQLException {
        selectedAppointment = appointmentSchedulesTable.getSelectionModel().getSelectedItem();
        modifyAppointmentsQuery.deleteAppointment(selectedAppointment.getAppointmentId());
        initTable();
    }

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

    public static appointmentModel getAppointmentToQuery() {
        return selectedAppointment;
    }

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

    public void showAppointments(ObservableList<appointmentModel> appointmentList) {
        appointmentSchedulesTable.setItems(appointmentList);
    }
}