package com.derekwooten;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utilities.JDBC;

import java.io.IOException;

/**
 * This is the main class that starts the Master Appointment Scheduler application.
 * @author Derek Wooten
 * @version 1.0
 * @since 5 January 2022
 * */
public class Main extends Application {

    /**
     * start method. This method will start the application's start screen. A lambda is used here to show how a lambda
     * can be used on a button via a setOnAction(event). Lambdas are commonly used on button action events.
     * * @param stage stage sets and shows the first scene for the application.
     * */
    @Override
    public void start(Stage stage) throws Exception{

        //Lambda launcher
        BorderPane pane = new BorderPane();
        ToggleButton button = new ToggleButton("Launch Master Appointment Scheduler");
        button.setOnAction((event) -> {
            ToggleButton source = (ToggleButton) event.getSource();
            if(source.isSelected()) {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../../views/LoginScreen.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.setScene(new Scene(root, 500, 356));
                stage.show();
            }
        });
        pane.setCenter(button);
        stage.setScene(new Scene(pane, 500, 356));
        stage.show();
    }
    /**
     * main method. This method opens the Java Database Connection (JDBC) to the MySQL database and launches the above start method. Once the
     * application is closed, Java Database Connection is closed.
     * @param args starts the application.
     * */
    public static void main(String[] args) {

        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
