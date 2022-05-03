package controller;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * @author Brad Pappan
 * Main class
 */
public class Main extends Application {


    /**
     * Sets the system default language in the program
     * @param args launches the program
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.getDefault());
        JDBC.openConnection();
        launch(args);



    }

    /**
     * Loads the login page
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
        primaryStage.setTitle("DBClientApp");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


}
