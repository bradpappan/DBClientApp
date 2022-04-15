package controller;


import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// To test the french language in the login page use, Locale.setDefault(new Locale)("fr"));
public class Main extends Application {


    public static void main(String[] args) {
        JDBC.openConnection();
        //test.zoneIdHandler();

        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
        primaryStage.setTitle("DBClientApp");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


}
