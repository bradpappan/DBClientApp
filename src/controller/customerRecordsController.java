package controller;

import helper.customerRecordsQuery;
import helper.loginPageQuery;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class customerRecordsController implements Initializable {
    
    public static customerModel selectedCustomer;

    @FXML public TableView<customerModel> customerRecordsTable;
    @FXML private TableColumn<customerModel, Integer> customerIdColumn;
    @FXML private TableColumn<customerModel, String> customerNameColumn;
    @FXML private TableColumn<customerModel, String> customerAddressColumn;
    @FXML private TableColumn<customerModel, String> customerPostalCodeColumn;
    @FXML private TableColumn<customerModel, String> customerPhoneColumn;
    @FXML private TableColumn<customerModel, Integer> customerDivisionIdColumn;

    ObservableList<customerModel> customerRecords;
    
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
    }

    public void deleteCustomer() throws SQLException {
        selectedCustomer = customerRecordsTable.getSelectionModel().getSelectedItem();
        customerRecordsQuery.delete(selectedCustomer.getCustomerId());
        initTable();
    }

    public static customerModel getCustomerToQuery() {
        return selectedCustomer;
    }

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

    private static void displayCustomAlert(String header, String content) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText(header);
        alertError.setContentText(content);
        alertError.showAndWait();
    }

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
}
