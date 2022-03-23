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
import utilities.ReportsTypeMonth;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * This is the ReportAppointmentTypeMonth controller class that will generate a report that gathers all appointments
 * and groups them by type of appointment and month.
 * */
public class ReportAppointmentTypeMonthController implements Initializable {

    Stage stage;
    Parent scene;

    String query = null;
    Connection connection = JDBC.openConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<ReportsTypeMonth> appointments = FXCollections.observableArrayList();

    @FXML
    private TableView<ReportsTypeMonth> reportTableView;
    @FXML
    private TableColumn<ReportsTypeMonth, Integer> numberAppointmentsTableColumn;
    @FXML
    private TableColumn<ReportsTypeMonth, String> monthTableColumn;
    @FXML
    private TableColumn<ReportsTypeMonth, String> appointmentTypeTableColumn;
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
     * initialize method. This method is executed when the ReportAppointmentTypeMonth.fxml scene is called. The database
     * connection is opened and the refreshCustomerAppointments method is called to populate the TableView.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connection = JDBC.openConnection();
        refreshCustomerAppointments();
        numberAppointmentsTableColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfAppointments"));
        monthTableColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        appointmentTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
    }

    /**
     * refreshCustomerAppointments method. This method is executed when the scene is initialized. It will run a query to
     * generate a report that gathers all appointments. The information can be grouped by a lambda that will stream the
     * appointments from the appointments list, sort them by comparison, and added to TableView.
     * */
    @FXML
    public void refreshCustomerAppointments() {

        try {
            appointments.clear();

            query = "SELECT monthname(Start) as Month, Type, COUNT(*) FROM appointments GROUP BY monthname(Start);";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointments.add(new ReportsTypeMonth(
                        resultSet.getInt("COUNT(*)"),
                        resultSet.getString("Month"),
                        resultSet.getString("Type")));
                reportTableView.setItems(appointments);
            }
            //Lambda for sorting appointments;
            appointments.stream().sorted(Comparator.comparing(i -> i.getNumberOfAppointments())).collect(Collectors.toList());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
