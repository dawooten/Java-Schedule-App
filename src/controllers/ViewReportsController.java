package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This is the ViewReports controller class that will show you all the reports that are available to select.
 * */
public class ViewReportsController {

    Stage stage;
    Parent scene;

    @FXML
    private Button typeMonthButton;
    @FXML
    private Button orgContactScheduleButton;
    @FXML
    private Button countryButton;
    @FXML
    private Button mainMenuButton;

    /**
     * onActionTypeMonth method. This method switches to the ReportAppointmentTypeMonth.fxml screen.
     * @param event onActionTypeMonth method is executed when button is pressed.
     * */
    @FXML
    void onActionTypeMonth(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/ReportAppointmentTypeMonth.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionOrgContactSchedule method. This method switches to the ReportOrganizationalContactSchedule.fxml screen.
     * @param event onActionOrgContactSchedule method is executed when button is pressed.
     * */
    @FXML
    void onActionOrgContactSchedule(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/ReportOrganizationalContactSchedule.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionCountryButton method. This method switches to the ReportAppointmentCountry.fxml screen.
     * @param event onActionCountryButton method is executed when button is pressed.
     * */
    @FXML
    void onActionCountryButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/ReportAppointmentCountry.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionMainMenu method. This method switches to the AppointmentInformation.fxml screen.
     * @param event onActionMainMenu method is executed when button is pressed.
     * */
    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/AppointmentInformation.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}