package controller;

import helper.appointmentsQuery;
import helper.customerRecordsQuery;
import helper.loginPageQuery;
import javafx.scene.control.*;
import model.customerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the main controller for the customer records table view
 */
public class customerRecordsController implements Initializable {
    
    public static customerModel selectedCustomer;

    public Button appointmentBtn;

    @FXML public TableView<customerModel> customerRecordsTable;
    @FXML private TableColumn<customerModel, Integer> customerIdColumn;
    @FXML private TableColumn<customerModel, String> customerNameColumn;
    @FXML private TableColumn<customerModel, String> customerAddressColumn;
    @FXML private TableColumn<customerModel, String> customerPostalCodeColumn;
    @FXML private TableColumn<customerModel, String> customerPhoneColumn;
    @FXML private TableColumn<customerModel, Integer> customerDivisionIdColumn;

    ObservableList<customerModel> customerRecords;

    /**
     * Initializes the customer records screen and populates the table view
     * Lambda expression that sets up a table event to change name of appointments button
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       initTable();
    }

    private void initTable() {
        customerRecords = FXCollections.observableArrayList();

        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        customerDivisionIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerDivisionId"));

        try {
          customerRecords.addAll(customerRecordsQuery.getAllCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerRecordsTable.setItems(customerRecords);

        //Lambda expression that sets up a table event to change name of appointments button
        customerRecordsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println(newSelection.getCustomerName());
                appointmentBtn.setText(newSelection.getCustomerName() + " Appointments");
            }
        });
    }

    /**
     * Deletes the selected customers appointments and then the selected customer
     * @throws SQLException
     */
    public void deleteCustomer() throws SQLException {
        selectedCustomer = customerRecordsTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            displayCustomAlert("Customer not selected", "Please select a customer");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Attention");
            alert.setContentText("Are you sure you want to delete this customer?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                appointmentsQuery.deleteAllAppointments(selectedCustomer.getCustomerId());
                customerRecordsQuery.delete(selectedCustomer.getCustomerId());
                initTable();
            }
        }





    }

    /**
     *
     * @return the selected customer to query
     */
    public static customerModel getCustomerToQuery() {
        return selectedCustomer;
    }

    /**
     *
     * @param event opens the selected customers appointments
     */
    @FXML
    public void openAppointmentsOnAction (ActionEvent event) {
        selectedCustomer = customerRecordsTable.getSelectionModel().getSelectedItem();
        Parent parent;

        if (selectedCustomer == null) {
            displayCustomAlert("No user selected.", "Please select a user.");
            return;
        }
            try {
                parent = FXMLLoader.load(Objects.requireNonNull(loginPageQuery.class.getResource("/view/customerAppointments.fxml")));
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
     * @param event changes scene to the add customer view
     */
    @FXML
    public void addCustomer(ActionEvent event) {
        Parent parent;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(loginPageQuery.class.getResource("/view/addCustomer.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * @param event changes scene to the edit customer view
     */
    public void editCustomer(ActionEvent event) {
        selectedCustomer = customerRecordsTable.getSelectionModel().getSelectedItem();
        Parent parent;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(loginPageQuery.class.getResource("/view/updateCustomer.fxml")));
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
     * @param event changes scene to the reports view
     */
    public void openReports(ActionEvent event) {
        Parent parent;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(loginPageQuery.class.getResource("/view/reports.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
