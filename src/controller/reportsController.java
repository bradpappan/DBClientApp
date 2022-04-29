package controller;

import helper.loginPageQuery;
import helper.reportsQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * This class is the controller for the reports view
 */
public class reportsController {

    @FXML
    TextArea reportTa;

    /**
     * first report that shows total number of types, months and lists them in a text area
     * @param event launches the queries to populate the observable lists
     * @throws SQLException
     */
    public void showFirstReport(ActionEvent event) throws SQLException {

        ObservableList<String> typeList = reportsQuery.typeList();
        ObservableList<String> monthList = reportsQuery.monthList();

        for (String appt : typeList) {
            reportTa.appendText(appt);
        }

        for (String appt : monthList) {
            reportTa.appendText(appt);
        }
    }

    /**
     * second report that shows contact names and the appointments they have
     * @param event launches the queries to populate the observable list
     * @throws SQLException
     */
    public void showSecondReport(ActionEvent event) throws SQLException {

        ObservableList<String> contacts = reportsQuery.contactNames();

        for (String contactList : contacts) {
            String contactId = reportsQuery.contactIds(contactList);
            reportTa.appendText("Contact Name " + contactList + ": " + "\n");
            ObservableList<String> contactAppointmentsList = reportsQuery.contactAppointmentList(contactId);

            if (contactAppointmentsList.isEmpty()) {
                reportTa.appendText("No appointments for this contact. \n");
            }

            for (String apptList : contactAppointmentsList) {
                reportTa.appendText(apptList + "\n" + "\n");
            }
        }
    }

    /**
     * third report that shows the total number of active customers
     * @param event launches the queries to populate the string
     * @throws SQLException
     */
    public void showThirdReport(ActionEvent event) throws SQLException {

       String numberOfCustomers = reportsQuery.countCustomers();

       if (numberOfCustomers.isEmpty()) {
           reportTa.appendText("There are no current active customers");
       } else {
           reportTa.appendText("Total number of active Customers in the database are: " + numberOfCustomers);
       }
    }

    /**
     *
     * @param event exits to the customer records view
     */
    public void exitHandler(ActionEvent event) {
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
}
