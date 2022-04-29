package controller;

import helper.customerQuery;
import helper.customerRecordsQuery;
import helper.regionQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.addCustomerModel;
import model.customerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This class is the controller for the update customer view
 */
public class updateCustomerController implements Initializable {

    @FXML
    private ComboBox<String> countryCombo;
    @FXML
    private ComboBox<String> divisionCombo;
    @FXML
    private TextField customerNameTf;
    @FXML
    private TextField addressTf;
    @FXML
    private TextField postalCodeTf;
    @FXML
    private TextField phoneTf;

    private ObservableList<addCustomerModel> divisionOb = FXCollections.observableArrayList();
    private ObservableList<String> countries = FXCollections.observableArrayList();
    private ObservableList<String> divisions = FXCollections.observableArrayList();

    private int customerId;
    private String selectedCountry;
    private String selectedDivision;

    customerModel selectedCustomerToOpen;

    /**
     * Initializes the update customer view, populates the view with selected customer information
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        try {
            selectedCustomerToOpen = customerRecordsController.getCustomerToQuery();
            customerId = selectedCustomerToOpen.getCustomerId();
            customerRecordsQuery.editCustomer((selectedCustomerToOpen.getCustomerId()));
            divisionOb.addAll(customerQuery.getDivision());

            for (addCustomerModel item : divisionOb) {
                if(!countries.contains(item.getCountry())) {
                    countries.add(item.getCountry());
                }
            }

            selectedCountry = (regionQuery.getCountry(selectedCustomerToOpen.getCustomerDivisionId()));
            selectedDivision = (regionQuery.getDivision(selectedCustomerToOpen.getCustomerDivisionId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }



        customerNameTf.setText(String.valueOf(selectedCustomerToOpen.getCustomerName()));
        addressTf.setText(String.valueOf(selectedCustomerToOpen.getCustomerAddress()));
        postalCodeTf.setText(String.valueOf(selectedCustomerToOpen.getCustomerPostalCode()));
        phoneTf.setText(String.valueOf(selectedCustomerToOpen.getCustomerPhone()));


        countryCombo.setItems(countries);
        countryCombo.getSelectionModel().select(countries.indexOf(selectedCountry));


        initDivisions();
        divisionCombo.getSelectionModel().select(divisions.indexOf(selectedDivision));
    }

    /**
     * Gets the selected country and populates the division combo box
     */
    @FXML
    public void initDivisions() {
        String countrySelected = countryCombo.getValue();
        divisionCombo.getItems().removeAll(divisions);

        for (addCustomerModel item : divisionOb) {
            if(item.getCountry().equals(countrySelected)){
                divisions.add(item.getDivision());
            }
        }
        divisionCombo.setItems(divisions);
    }

    /**
     *
     * @param event saves the customer
     * @throws SQLException
     */
    public void saveCustomer(ActionEvent event) throws SQLException {
        String name = customerNameTf.getText();
        String address = addressTf.getText();
        int postalCode = Integer.parseInt(postalCodeTf.getText());
        String phone = phoneTf.getText();
        int divisionId = 0;

        for (addCustomerModel item : divisionOb) {
            if(divisionCombo.getValue().equals(item.getDivision())) {
                divisionId = item.getDivisionId();
            }
        }

        try {
            customerQuery.updateCustomer(customerId, name, address, postalCode, phone, divisionId);
            returnToRecords(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void returnToRecords (ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/customerRecords.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
