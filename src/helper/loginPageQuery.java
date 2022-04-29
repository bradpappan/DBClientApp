package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.appointmentModel;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * This class holds the queries for the login page
 */
public class loginPageQuery {

    /**
     *
     * @param event passes in the event
     * @param username passes in the username
     * @param password passes in the password
     * @return returns the selected user and verifies the information matches
     * @throws SQLException
     */
    public static boolean selectUser(ActionEvent event, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT Password FROM users WHERE User_Name = ?";
            preparedStatement = JDBC.connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                displayLoginAlert();
                return false;
            } else {
                while (resultSet.next()) {
                    String enteredPassword = resultSet.getString("password");
                    if (enteredPassword.equals(password)) {
                        loginSuccessful(event);
                        return true;
                    } else {
                        displayLoginAlert();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Changes scene if the login was successful
     * @param event changes scene to customer records
     * @throws Exception
     */
    public static void loginSuccessful(ActionEvent event) throws Exception {
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
     * Displays error if username or password doesnt match
     */
    private static void displayLoginAlert() {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText("Wrong username or password.");
        alertError.setContentText("Try logging again.");
        alertError.showAndWait();
    }

    /**
     *
     * @return appointments within 15 minutes of login
     * @throws SQLException
     */
    public static ObservableList<appointmentModel> appointmentWarning() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ObservableList<appointmentModel> appointmentWarningOb = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointments WHERE Start BETWEEN now() AND date_add(now(), INTERVAL 15 MINUTE);";
        preparedStatement = JDBC.connection.prepareStatement(sql);
        //preparedStatement.setTimestamp(1, startTime);
        //preparedStatement.setTimestamp(2, endTime);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String appointmentId = resultSet.getString("Appointment_ID");
            String appointmentTitle = resultSet.getString("Title");
            String appointmentDescription = resultSet.getString("Description");
            String appointmentLocation = resultSet.getString("Location");
            String appointmentType = resultSet.getString("Type");
            Timestamp appointmentStart = resultSet.getTimestamp("Start");
            Timestamp appointmentEnd = resultSet.getTimestamp("End");
            int appointmentCustomerId = resultSet.getInt("Customer_ID");
            int appointmentUserId = resultSet.getInt("User_ID");
            String appointmentContactId = resultSet.getString("Contact_ID");

            appointmentModel appointmentResult = new appointmentModel(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart,
                    appointmentEnd, appointmentCustomerId, appointmentUserId, appointmentContactId);
            appointmentWarningOb.add(appointmentResult);
        }
        return appointmentWarningOb;
    }
}

