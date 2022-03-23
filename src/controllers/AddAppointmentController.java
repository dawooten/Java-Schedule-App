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
import utilities.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the AddAppointment controller class that will add an appointment to the appointmentsList.
 * */
public class AddAppointmentController implements Initializable {

    Stage stage;
    Parent scene;

    String query = null;
    Connection connection = JDBC.openConnection();

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

    private ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

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
    private Button addButton;
    @FXML
    private Button cancelButton;

    /**
     * onActionAdd method. This method is executed when the Add button is pressed. It will scrape all the info from the
     * fields and verify if the information is good. If it is good, it adds the information to the database and switches
     * to the AppointmentInformation view.
     * @param event onActionAdd method is executed when button is pressed.
     * */
    @FXML
    void onActionAdd(ActionEvent event) throws IOException {

        scrapeInfo();
        if(appointmentVerificationCheck()){
            addAppointment(title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
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
     * initialize method. This method is executed when the AddAppointment.fxml scene is called and sets up the view.
     * The contacts, customers, and users are all loaded into their respective ComboBoxes.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactComboBox.setItems(ContactsQuery.populateAllContactsNames());
        customerComboBox.setItems(CustomerQuery.populateAllCustomerNames());
        userComboBox.setItems(AppointmentsQuery.populateAllUserIDNames());
    }

    /**
     * scrapeInfo method. This method scrapes all the information from the fields and saves them to the associated
     * variable for use throughout the controller. CustomerID, UserID, and ContactID are converted from Strings to
     * Integers to be able to save to the database.
     * */
    @FXML
    public void scrapeInfo () {
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
        appointmentsList = ListManager.getAllAppointments();
        startDateTime = LocalDateTime.of(date, startTime2);
        endDateTime = LocalDateTime.of(date, endTime2);
}

    /**
     * addAppointment method. This method takes all the information from the variables and inserts them via SQL query
     * into the database.
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
    public void addAppointment(String title, String description, String location, String type, Timestamp start,
                               Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdate,
                               String lastUpdatedBy, int customerID, int userID, int contactID) {

        LocalDateTime tempStartTime1 = TimeManager.LocalTimeToEastCoastTimeZone(startDateTime);
        LocalDateTime tempStartTime2 = TimeManager.EastCoastTimeToUTCTimeZone(tempStartTime1);
        start = Timestamp.valueOf(tempStartTime2);

        LocalDateTime tempEndTime1 = TimeManager.LocalTimeToEastCoastTimeZone(endDateTime);
        LocalDateTime tempEndTime2 = TimeManager.EastCoastTimeToUTCTimeZone(tempEndTime1);
        end = Timestamp.valueOf(tempEndTime2);

        try {
            query = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, " +
                    "Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (" +
                    "'" + title + "'," +
                    "'" + description + "'," +
                    "'" + location + "'," +
                    "'" + type + "'," +
                    "'" + start + "'," +
                    "'" + end + "'," +
                    "'" + createDate + "'," +
                    "'" + createdBy + "'," +
                    "'" + lastUpdate + "'," +
                    "'" + lastUpdatedBy + "'," +
                    "" + customerID + "," +
                    "" + userID + "," +
                    "" + contactID + "" +
                    ")";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * appointmentVerificationCheck method. This boolean method verifies if the appointment to be scheduled is able to be
     * scheduled. It first checks to see if the proposed appointment is within EST business hours by calling the
     * businessHoursCheck method, passing through the date, start time, and end time. If the appointment falls outside
     * of the business hours, an alert is thrown to change the times to valid timeframe and returns false. If the hours
     * are valid, the appointment is then checked against existing appointments using a for loop. If there is an existing
     * appointment, it will throw an alert and return false. If there is no conflicts, method returns true.
     * @return true returns true if appointment passes verification check.
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
                for (Appointments appointments : appointmentsList) {
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
                        }else if (appointments.getStart().equals(startDateTime) || appointments.getEnd().equals(endDateTime)) {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setContentText("Your date and time matches " + startDateTime + " or " + endDateTime);
                            alert1.showAndWait();
                            return false;
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
     * @return true returns true if business is closed, returns false if business is open.
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
