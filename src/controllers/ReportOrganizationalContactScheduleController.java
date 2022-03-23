package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utilities.JDBC;
import utilities.ReportsContactSchedule;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * This is the ReportOrganizationalContactSchedule controller class that will generate a report that gathers all
 * appointments and orders them by Contact_ID.
 * */
public class ReportOrganizationalContactScheduleController implements Initializable {

    Stage stage;
    Parent scene;

    String query = null;
    Connection connection = JDBC.openConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<ReportsContactSchedule> appointments = FXCollections.observableArrayList();

    @FXML
    private TableView<ReportsContactSchedule> contactTableView;
    @FXML
    private TableColumn<ReportsContactSchedule, Integer> contactTableColumn;
    @FXML
    private TableColumn<ReportsContactSchedule, Integer> appointmentIDTableColumn;
    @FXML
    private TableColumn<ReportsContactSchedule, String> titleTableColumn;
    @FXML
    private TableColumn<ReportsContactSchedule, String> typeTableColumn;
    @FXML
    private TableColumn<ReportsContactSchedule, String> descriptionTableColumn;
    @FXML
    private TableColumn<ReportsContactSchedule, Timestamp> startDateTimeTableColumn;
    @FXML
    private TableColumn<ReportsContactSchedule, Timestamp> endDateTimeTableColumn;
    @FXML
    private TableColumn<ReportsContactSchedule, Integer> customerIDTableColumn;
    @FXML
    private Button reportMenuButton;

    /**
     * onActionReportMenuButton method. This method returns the application to the ViewReports.fxml scene.
     * @param event onActionReportMenuButton method is executed when button is pressed.
     * */
    @FXML
    void onActionReportMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/ViewReports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * initialize method. This method is executed when the ReportOrganizationalContactSchedule.fxml scene is called.
     * The database connection is opened and the refreshContactAppointments method is called to populate the TableView.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = JDBC.openConnection();
        refreshContactAppointments();
        contactTableColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startDateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endDateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }

    /**
     * refreshContactAppointments method. This method is executed when the scene is initialized. It will run a query to
     * generate a report that gathers all appointments, orders them by Contact_ID, and adds to TableView.
     * */
    @FXML
    public void refreshContactAppointments() {

        try {
            appointments.clear();

            query = "SELECT Contact_ID, Appointment_ID, Title, Type, Description, Start, End, Customer_ID FROM appointments ORDER BY Contact_ID";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new ReportsContactSchedule(
                        resultSet.getInt("Contact_ID"),
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Type"),
                        resultSet.getString("Description"),
                        resultSet.getTimestamp("Start"),
                        resultSet.getTimestamp("End"),
                        resultSet.getInt("Customer_ID")));
                contactTableView.setItems(appointments);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}