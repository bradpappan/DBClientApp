package controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.addCustomerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class addCustomerController implements Initializable {

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            divisionOb.addAll(customerQuery.getDivision());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (addCustomerModel item : divisionOb) {
            if(!countries.contains(item.getCountry())){
                countries.add(item.getCountry());
            }
        }
            countryCombo.setItems(countries);
    }

    @FXML
    public void onSelected(ActionEvent event) {
        String countrySelected = countryCombo.getValue();
        divisionCombo.getItems().removeAll(divisions);

        for (addCustomerModel item : divisionOb) {
            if(item.getCountry().equals(countrySelected)){
                divisions.add(item.getDivision());
            }
        }
        divisionCombo.setItems(divisions);
    }

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
            customerQuery.insertCustomer(name, address, postalCode, phone, divisionId);
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
