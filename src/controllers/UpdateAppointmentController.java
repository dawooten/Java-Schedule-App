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
import javafx.stage.Stage;
import models.Appointments;
import models.Customers;
import utilities.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the UpdateAppointment controller class that will update an appointment in the appointmentToUpdate list.
 * */
public class UpdateAppointmentController implements Initializable {

    String query = null;
    Connection connection = JDBC.openConnection();

    int appointmentID;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private LocalDate date;
    private String startTime;
    private String endTime;
    private int customerID;
    private int userID;
    LocalTime startTime2;
    LocalTime endTime2;
    Timestamp start;
    Timestamp end;
    Timestamp createDate;
    String createdBy;
    Timestamp lastUpdate;
    String lastUpdatedBy;
    String customerNameToConvert;
    String userNameToConvert;
    String contactNameToConvert;
    int contactID;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;

    private Appointments appointmentToUpdate = AppointmentInformationController.appointmentToUpdate;
    private static ObservableList<Appointments> checkAppts = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;

    @FXML
    private TextField AppointmentIDTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private ComboBox<String> contactComboBox;
    @FXML
    private TextField typeTextField;
    @FXML
    private DatePicker datePickerBox;
    @FXML
    private TextField startTimeTextField;
    @FXML
    private TextField endTimeTextField;
    @FXML
    private ComboBox<String> customerComboBox;
    @FXML
    private ComboBox<String> userComboBox;
    @FXML
    private Button updateButton;
    @FXML
    private Button cancelButton;

    /**
     * onActionUpdate method. This method is executed when the Update button is pressed. It will scrape all the info
     * from the fields and verify if the information is good. If it is good, it updates the information to the database
     * and switches to the AppointmentInformation view.
     * @param event onActionUpdate method is executed when button is pressed.
     * */
    @FXML
    void onActionUpdate(ActionEvent event) throws IOException {

        scrapeInfo();
        if(appointmentVerificationCheck()){
            updateAppointment(appointmentID, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/views/AppointmentInformation.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * onActionCancel method. This method is executed when the Cancel button is pressed. It will switch to the
     * AppointmentInformation view without saving.
     * @param event onActionCancel method is executed when button is pressed.
     * */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        alert1.setTitle("Cancel");
        Optional<ButtonType> result = alert1.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/views/AppointmentInformation.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * initialize method. This method is executed when the UpdateAppointment.fxml scene is called and sets up the view.
     * All the fields are populated from appointmentToUpdate item that was selected on the AppointmentInformation.fxml
     * screen.  The contacts, customers, and users are all loaded into their respective ComboBoxes, converting their names
     * back to IDs.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        AppointmentIDTextField.setText(String.valueOf(appointmentToUpdate.getAppointmentID()));
        titleTextField.setText(appointmentToUpdate.getTitle());
        descriptionTextField.setText(appointmentToUpdate.getDescription());
        locationTextField.setText(appointmentToUpdate.getLocation());
        typeTextField.setText(appointmentToUpdate.getType());
        datePickerBox.setValue(appointmentToUpdate.getStart().toLocalDate());
        startTimeTextField.setText(String.valueOf(appointmentToUpdate.getStart().toLocalTime()));
        endTimeTextField.setText(String.valueOf(appointmentToUpdate.getEnd().toLocalTime()));

        int contactIDToContactName = appointmentToUpdate.getContactID();
        String contactNames = ContactsQuery.convertContactIDToContactName(contactIDToContactName);
        contactComboBox.getSelectionModel().select(contactNames);
        contactComboBox.setItems(ContactsQuery.populateAllContactsNames());

        int customerIDToCustomerName = appointmentToUpdate.getCustomerID();
        String customerNames = CustomerQuery.convertCustomerIDToCustomerName(customerIDToCustomerName);
        customerComboBox.getSelectionModel().select(customerNames);
        customerComboBox.setItems(CustomerQuery.populateAllCustomerNames());

        int userIDToUserName = appointmentToUpdate.getUserID();
        String userNames = UsersQuery.convertUserIDToUserName(userIDToUserName);
        userComboBox.getSelectionModel().select(userNames);
        userComboBox.setItems(AppointmentsQuery.populateAllUserIDNames());
    }

    /**
     * updateAppointment method. This method takes all the information from the variables and updates them via SQL query
     * into the database.
     * @param appointmentID Integer of the appointmentID. Auto-generated.
     * @param contactID Integer of the converted Contact's name.
     * @param createDate Timestamp of when appointment was created.
     * @param createdBy String of who created the appointment.
     * @param customerID Integer of the converted Customer's name.
     * @param description String of the appointment's description.
     * @param end Timestamp of the appointment's end time.
     * @param lastUpdate Timestamp of when appointment was last updated.
     * @param lastUpdatedBy String of who last updated appointment.
     * @param location String of where the appointment is.
     * @param start Timestamp of the appointment's start time.
     * @param title String of the appointment's name.
     * @param type String of what kind of appointment it is.
     * @param userID Integer of the converted User's name.
     * */
    @FXML
    public void updateAppointment(int appointmentID, String title, String description, String location, String type, Timestamp start,
                                  Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdate,
                                  String lastUpdatedBy, int customerID, int userID, int contactID) {

        LocalDateTime tempStartTime1 = TimeManager.LocalTimeToEastCoastTimeZone(startDateTime);
        LocalDateTime tempStartTime2 = TimeManager.EastCoastTimeToUTCTimeZone(tempStartTime1);
        start = Timestamp.valueOf(tempStartTime2);

        LocalDateTime tempEndTime1 = TimeManager.LocalTimeToEastCoastTimeZone(endDateTime);
        LocalDateTime tempEndTime2 = TimeManager.EastCoastTimeToUTCTimeZone(tempEndTime1);
        end = Timestamp.valueOf(tempEndTime2);

        try {
            query = "UPDATE appointments SET Title = " + "'" + title + "'," +
                    "Description = " + "'" + description + "'," +
                    "Location = " + "'" + location + "'," +
                    "Type = " + "'" + type + "'," +
                    "Start = " + "'" + start + "'," +
                    "End = " + "'" + end + "'," +
                    "Create_Date = " + "'" + createDate + "'," +
                    "Created_By = " + "'" + createdBy + "'," +
                    "Last_Update = " + "'" + lastUpdate + "'," +
                    "Last_Updated_By = " + "'" + lastUpdatedBy + "'," +
                    "Customer_ID = " + "'" + customerID +  "', " +
                    "User_ID = " + "'" + userID +  "', " +
                    "Contact_ID = " + "'" + contactID +  "' " +
                    "WHERE Appointment_ID = " + appointmentID + "";

            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * scrapeInfo method. This method scrapes all the information from the fields and saves them to the associated
     * variable for use throughout the controller. CustomerID, UserID, and ContactID are converted from Integers to
     * Strings to allow the user to see a name instead of number for those ComboBoxes.
     * */
    @FXML
    public void scrapeInfo() {
        appointmentID = Integer.parseInt(AppointmentIDTextField.getText());
        title = titleTextField.getText();
        description = descriptionTextField.getText();
        location = locationTextField.getText();
        type = typeTextField.getText();
        date = datePickerBox.getValue();
        startTime = startTimeTextField.getText();
        startTime2 = LocalTime.parse(startTime);
        endTime = endTimeTextField.getText();
        endTime2 = LocalTime.parse(endTime);
        start = Timestamp.valueOf(LocalDateTime.of(date, startTime2));
        end = Timestamp.valueOf(LocalDateTime.of(date, endTime2));
        createDate = Timestamp.valueOf(LocalDateTime.now());
        createdBy = "test";
        lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        lastUpdatedBy = "test";
        customerNameToConvert = customerComboBox.getSelectionModel().getSelectedItem();
        customerID = AppointmentsQuery.convertCustomerNameToCustomerID(customerNameToConvert);
        userNameToConvert = userComboBox.getSelectionModel().getSelectedItem();
        userID = AppointmentsQuery.convertUserNameToUserID(userNameToConvert);
        contactNameToConvert = contactComboBox.getSelectionModel().getSelectedItem();
        contactID = AppointmentsQuery.convertContactNameToContactID(contactNameToConvert);
        checkAppts = ListManager.getAllAppointments();
        startDateTime = LocalDateTime.of(date, startTime2);
        endDateTime = LocalDateTime.of(date, endTime2);
    }

    /**
     * appointmentVerificationCheck method. This boolean method verifies if the appointment to be scheduled is able to be
     * scheduled. It first checks to see if the proposed appointment is within EST business hours by calling the
     * businessHoursCheck method, passing through the date, start time, and end time. If the appointment falls outside
     * of the business hours, an alert is thrown to change the times to valid timeframe and returns false. If the hours
     * are valid, the appointment is then checked against existing appointments using a for loop. If there is an existing
     * appointment, it will throw an alert and return false. If there is no conflicts, method returns true.
     * @return true
     * */
    @FXML
    public boolean appointmentVerificationCheck() {

        if (businessHoursCheck(date, startDateTime, endDateTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Business hours are 0800 to 2200 EST. Please enter a valid time.");
            alert.showAndWait();
            return false;
        } else if (startDateTime.isAfter(endDateTime) || startDateTime.equals(endDateTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Start Time can't be after or the same as End Time.");
            alert.showAndWait();
            return false;
        } else {
            try {
                for (Appointments appointments : checkAppts) {
                    if (appointments.getAppointmentID() != appointmentToUpdate.getAppointmentID()) {
                        if (appointments.getCustomerID() == customerID) {
                            if (startDateTime.isAfter(appointments.getStart().minusMinutes(1)) && startDateTime.isBefore(appointments.getEnd())) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Customer " + appointments.getCustomerID() + " has an appointment: " + appointments.getTitle() + " between " + appointments.getStart() + " and " + appointments.getEnd());
                                alert.showAndWait();
                                return false;
                            } else if (endDateTime.isAfter(appointments.getStart()) && endDateTime.isBefore(appointments.getEnd())) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Customer " + appointments.getCustomerID() + " has an appointment: " + appointments.getTitle() + " between " + appointments.getStart() + " and " + appointments.getEnd());
                                alert.showAndWait();
                                return false;
                            } else if (appointments.getStart().equals(startDateTime) || appointments.getEnd().equals(endDateTime)) {
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setContentText("Your date and time matches " + startDateTime + " or " + endDateTime);
                                alert1.showAndWait();
                                return false;
                            }
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return true;
    }

    /**
     * businessHoursCheck method. This boolean method checks the date, startDateTime, and endDateTime against the
     * business' open hours. If the hours are valid, the console prints out "Open" and returns false. If the business is
     * closed, it will return true and print to the console "Closed".
     * @param date LocalDate of the datepicker box.
     * @param startDateTime LocalDateTime of the starting time entered.
     * @param endDateTime LocalDateTime of the ending time entered.
     * @return true
     * */
    @FXML
    public boolean businessHoursCheck(LocalDate date, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        if(!TimeManager.businessHours(startDateTime) || !TimeManager.businessHours(endDateTime)) { //false || false
            System.out.println("Closed");
            return true;//true
        } else {
            System.out.println("Open");
            return false;//false
        }
    }
}
