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
import utilities.ReportsCustomers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * This is the ReportCustomerAppointments controller class that will generate a report that gathers all appointments
 * and groups them by the number of appointments per customer to determine who are the most frequent.
 * */
public class ReportCustomerAppointmentsController implements Initializable {

    Stage stage;
    Parent scene;

    String query = null;
    Connection connection = JDBC.openConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<ReportsCustomers> appointments = FXCollections.observableArrayList();

    @FXML
    private TableView<ReportsCustomers> reportTableView;
    @FXML
    private TableColumn<ReportsCustomers, String> customerName;
    @FXML
    private TableColumn<ReportsCustomers, String> totalAppointments;
    @FXML
    private Button reportMenuButton;

    /**
     * onActionReportMenuButton method. This method returns the application to the ViewReports.fxml scene.
     * @param event onActionReportMenuButton method is executed when button is pressed.
     * */
    @FXML
    void onActionReportMenuButton(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/ViewReports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * initialize method. This method is executed when the ReportCustomerAppointments.fxml scene is called. The database
     * connection is opened and the refreshCustomerAppointments method is called to populate the TableView.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = JDBC.openConnection();
        refreshAppointments();
        customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        totalAppointments.setCellValueFactory(new PropertyValueFactory<>("totalAppts"));
    }

    /**
     * refreshAppointments method. This method is executed when the scene is initialized. It will run a query to
     * generate a report that gathers all customer Names and count the number of appointments. These will then be inner
     * joined on the Customer_ID. The information can be grouped by a lambda that will stream the customer names,
     * sort them by comparison, and added to TableView.
     * */
    @FXML
    public void refreshAppointments() {

        try {
            appointments.clear();

            query = "SELECT customers.Customer_Name, count(appointments.Appointment_ID) FROM customers " +
                    "INNER JOIN appointments ON customers.Customer_ID = appointments.Customer_ID GROUP BY Customer_Name";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new ReportsCustomers(
                        resultSet.getString("Customer_Name"),
                        resultSet.getString("count(appointments.Appointment_ID)")));
                reportTableView.setItems(appointments);
            }
            appointments.stream().sorted(Comparator.comparing(i -> i.getCustomerName())).collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
