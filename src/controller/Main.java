package controller;


import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

// To test the french language in the login page use, Locale.setDefault(new Locale)("fr"));
public class Main extends Application {


    public static void main(String[] args) {
        JDBC.openConnection();
        launch(args);

        Locale france = new Locale("fr", "FR");
        ResourceBundle rb = ResourceBundle.getBundle("language/fr", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("fr"));


    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginPage.fxml"));
        primaryStage.setTitle("DBClientApp");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


}
