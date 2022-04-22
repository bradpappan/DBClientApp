package helper;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class loginPageQuery implements Initializable {

    public static void selectUser(ActionEvent event, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT Password FROM users WHERE User_Name = ?";
            preparedStatement = JDBC.connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                displayLoginAlert();
            } else {
                while (resultSet.next()) {
                    String enteredPassword = resultSet.getString("password");
                    if (enteredPassword.equals(password)) {
                        loginSuccessful(event);
                    } else {
                        displayLoginAlert();
                    }
                }
            }
        } catch(Exception e){
                e.printStackTrace();
            }
    }

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


    private static void displayLoginAlert() {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle("Error");
        alertError.setHeaderText("Wrong username or password.");
        alertError.setContentText("Try logging again.");
        alertError.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

