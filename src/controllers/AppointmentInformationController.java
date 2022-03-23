package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Appointments;
import utilities.JDBC;
import utilities.ListManager;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the AppointmentInformation controller class that will display all appointments in TableView, give options to
 * filter appointments, add/update/delete appointments, and go to the Customer Information.
 * */
public class AppointmentInformationController implements Initializable {

    Stage stage;
    Parent scene;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Appointments appointments = null;
    Appointments apptToDelete = null;
    public static Appointments appointmentToUpdate;

    ObservableList<Appointments> AllAppointmentsList = FXCollections.observableArrayList();
    ObservableList<Appointments> AllMonthAppointmentsList = FXCollections.observableArrayList();
    ObservableList<Appointments> AllWeekAppointmentsList = FXCollections.observableArrayList();

    @FXML
    private Label welcomeLabel;
    @FXML
    private Label masterAppointmentLabel;
    @FXML
    private TableView<Appointments> appointmentsTableView;
    @FXML
    private TableColumn<Appointments, Integer> appointmentIDTableColumn;
    @FXML
    private TableColumn<Appointments, String> titleTableColumn;
    @FXML
    private TableColumn<Appointments, String> descriptionTableColumn;
    @FXML
    private TableColumn<Appointments, String> locationTableColumn;
    @FXML
    private TableColumn<Appointments, Integer> contactTableColumn;
    @FXML
    private TableColumn<Appointments, String> typeTableColumn;
    @FXML
    private TableColumn<Appointments, LocalDateTime> startDateTimeTableColumn;
    @FXML
    private TableColumn<Appointments, LocalDateTime> EndDateTimeTableColumn;
    @FXML
    private TableColumn<Appointments, Integer> customerIDTableColumn;
    @FXML
    private TableColumn<Appointments, Integer> userIDTableColumn;
    @FXML
    private Label appointmentsLabel;
    @FXML
    private RadioButton allRadioButton;
    @FXML
    private RadioButton currentMonthRadioButton;
    @FXML
    private RadioButton currentWeekRadioButton;
    @FXML
    private Button customerInformation;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Button updateAppointmentButton;
    @FXML
    private Button deleteAppointmentButton;
    @FXML
    private Button viewReportsButton;
    @FXML
    private Button exitButton;

    /**
     * onActionCustomerInformation method. This method switches to the CustomerInformation.fxml screen.
     * @param event onActionCustomerInformation method is executed when button is pressed.
     * */
    @FXML
    void onActionCustomerInformation(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustomerInformation.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionAddAppointment method. This method switches to the AddAppointment.fxml screen.
     * @param event onActionAddAppointment method is executed when button is pressed.
     * */
    @FXML
    void onActionAddAppointment(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionUpdateAppointment method. This method switches to the UpdateAppointment.fxml screen.
     * @param event onActionUpdateAppointment method is executed when button is pressed.
     * */
    @FXML
    void onActionUpdateAppointment(ActionEvent event) throws IOException {

        appointmentToUpdate = appointmentsTableView.getSelectionModel().getSelectedItem();

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/UpdateAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionDeleteAppointment method. This method deletes the selected appointment. It will alert the user if they
     * are sure they want to delete the appointment. If confirmed, the method will delete the appointment from the
     * database and refresh the TableView.
     * @param event onActionDeleteAppointment method is executed when button is pressed.
     * */
    @FXML
    void onActionDeleteAppointment(ActionEvent event) {

        apptToDelete = appointmentsTableView.getSelectionModel().getSelectedItem();
        int apptID = apptToDelete.getAppointmentID();
        String apptType = apptToDelete.getType();

        try {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete Appointment ID " + apptID + ": " + apptType + "?");
            alert1.setTitle("Delete Appointment");
            Optional<ButtonType> result = alert1.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {
                appointments = appointmentsTableView.getSelectionModel().getSelectedItem();
                query = "DELETE FROM appointments WHERE Appointment_ID = " + appointments.getAppointmentID();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                refreshAppointments();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * onActionViewReports method. This method switches to the ViewReports.fxml screen.
     * @param event onActionViewReports method is executed when button is pressed.
     * */
    @FXML
    void onActionViewReports(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/ViewReports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionExit method. This method closes the application.
     * @param event onActionExit method is executed when button is pressed.
     * */
    @FXML
    void onActionExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * onActionAll method. This method will show all the appointments from the database in TableView.
     * @param event onActionAll method is executed when button is pressed.
     * */
    @FXML
    void onActionAll(ActionEvent event) {
        refreshAppointments();
    }

    /**
     * onActionCurrentMonth method. This method will show all the appointments for the current month from the database
     * in TableView.
     * @param event onActionCurrentMonth method is executed when button is pressed.
     * */
    @FXML
    void onActionCurrentMonth(ActionEvent event) {

        Month currentMonth = LocalDate.now().getMonth();
        int currentYear = LocalDate.now().getYear();
        AllMonthAppointmentsList.clear();

        for(Appointments appointments : AllAppointmentsList) {
            if(appointments.getStart().getMonth().equals(currentMonth) && appointments.getStart().getYear() == (currentYear)) {
                AllMonthAppointmentsList.add(appointments);
            }
        }
        appointmentsTableView.setItems(AllMonthAppointmentsList);
    }

    /**
     * onActionCurrentWeek method. This method will show all the appointments for the current week from the database
     * in TableView.
     * @param event onActionCurrentWeek method is executed when button is pressed.
     * */
    @FXML
    void onActionCurrentWeek(ActionEvent event) {
        TemporalField currentWeek = WeekFields.ISO.weekOfWeekBasedYear();
        LocalDate todaysDate = LocalDate.now();
        int currentYear = LocalDate.now().getYear();
        int thisWeekNumber = todaysDate.get(currentWeek);
        AllWeekAppointmentsList.clear();

        for(Appointments appointments : AllAppointmentsList) {
            TemporalField weekOfAppointment = WeekFields.ISO.weekOfWeekBasedYear();
            int weekNumberOfAppointment = appointments.getStart().toLocalDate().get(weekOfAppointment);
            if(weekNumberOfAppointment == thisWeekNumber && appointments.getStart().getYear() == currentYear) {
                AllWeekAppointmentsList.add(appointments);
            }
        }
        appointmentsTableView.setItems(AllWeekAppointmentsList);
    }

    /**
     * initialize method. This method is executed when the AppointmentInformation.fxml scene is called and sets up the
     * TableView via the allAppointments method and shows all the appointments via the refreshAppointments method.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        allRadioButton.setSelected(true);
        connection = JDBC.openConnection();
        refreshAppointments();
        allAppointments();
    }

    /**
     * refreshAppointments method. This method is refreshes all appointments in the database. The appointmentList is
     * cleared out and refreshed with all appointments from the database. The appointments are then displayed via TableView.
     * */
    @FXML
    public void refreshAppointments() {

        try {
            AllAppointmentsList.clear();

            query = "SELECT * FROM appointments";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AllAppointmentsList.add(new Appointments(
                        resultSet.getInt("Appointment_ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Description"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type"),
                        resultSet.getTimestamp("Start").toLocalDateTime(),
                        resultSet.getTimestamp("End").toLocalDateTime(),
                        resultSet.getTimestamp("Create_Date").toLocalDateTime(),
                        resultSet.getString("Created_By"),
                        resultSet.getTimestamp("Last_Update"),
                        resultSet.getString("Last_Updated_By"),
                        resultSet.getInt("Customer_ID"),
                        resultSet.getInt("User_ID"),
                        resultSet.getInt("Contact_ID")));
                appointmentsTableView.setItems(AllAppointmentsList);
            }
            ListManager.setAllAppointments(AllAppointmentsList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * allAppointments method. This method is executed when the AppointmentInformation.fxml scene is initialized and
     * sets up the TableView.
     * */
    @FXML
    public void allAppointments() {
        appointmentIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactTableColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<Appointments, LocalDateTime>("start"));
        EndDateTimeTableColumn.setCellValueFactory(new PropertyValueFactory<Appointments, LocalDateTime>("end"));
        customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));
        appointmentsTableView.setItems(AllAppointmentsList);
    }
}
