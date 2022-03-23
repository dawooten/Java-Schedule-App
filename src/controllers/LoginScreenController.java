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
import utilities.JDBC;
import utilities.ListManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * This is the LoginScreen controller class that will display the Login Screen. The Login Screen is displayed in English
 * or French, depending on the user's selected computer language. As the user is logged in, the username and password is
 * authenticated via the database. Once authenticated, the system checks for appointments within 15 minutes. In addition,
 * the username, date/time and a message whether the user successfully or fails to log in is recorded to the
 * login_activity.text file.
 * */
public class LoginScreenController implements Initializable {

    Stage stage;
    Parent scene;

    boolean appt = false;
    int apptID = 0;
    String apptDate = "";
    String apptTime = "";
    String query = null;
    Connection connection = JDBC.openConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String fileName = "login_activity.txt";
    Timestamp dateTime = Timestamp.valueOf(LocalDateTime.now());

    private ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();

    @FXML
    private Label appointmentHeaderLabel;
    @FXML
    private Label userIDLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label userLocationLabel;
    @FXML
    private Label zoneIDLabel;
    @FXML
    private Label loginMessageLabel;

    /**
     * onActionCancel method. This method closes the application.
     * @param event onActionCancel method is executed when button is pressed.
     * */
    @FXML
    void onActionCancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * onActionReset method. This method clears the fields and sets the loginMessage Label to blank.
     * @param event onActionReset method is executed when button is pressed.
     * */
    @FXML
    void onActionReset(ActionEvent event) {
        usernameTextField.clear();
        passwordField.clear();
        loginMessageLabel.setText("");
    }

    /**
     * onActionLogin method. This method logs the user into the application. When the method is called, it checks to see
     * if the username and password are blank. If blank, it displays a message to enter a username and password in English
     * or French. Next, the loginAuthentication method is called and checks the username and password in the database.
     * If loginAuthentication is false, it displays a message that the username and password is wrong in English or French.
     * Next, the apptCheck method is called to check for appointments within 15 minutes of logging in and displays them
     * in English or French. If apptCheck is false, it displays there are no appointments within 15 minutes of logging in.
     * During each check, the logActivity method is called with either success or failure to log in and records it.
     * @param event onActionLogin method is executed when button is pressed.
     * */
    @FXML
    void onActionLogin(ActionEvent event) throws IOException, SQLException {

        if (usernameTextField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {
            if (loginAuthentication() == true) {
                System.out.println(loginAuthentication());
                if (apptCheck() == true) {
                    System.out.println(apptCheck());
                    System.out.println(appt);

                        try {
                            ResourceBundle resourceBundle = ResourceBundle.getBundle("utilities/french", Locale.getDefault());
                            if(Locale.getDefault().getLanguage().equals("fr")) {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, resourceBundle.getString("appt") + apptID + resourceBundle.getString("date") + apptDate + resourceBundle.getString("time") + apptTime);
                                Optional<ButtonType> result = alert.showAndWait();
                                if(result.isPresent() && result.get() == ButtonType.OK) {
                                    logActivity("Success");
                                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                                    scene = FXMLLoader.load(getClass().getResource("/views/AppointmentInformation.fxml"));
                                    stage.setScene(new Scene(scene));
                                    stage.show();
                                }
                            } else {
                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Upcoming Appointment Information: " + apptID + " Date: " + apptDate + " Time: " + apptTime);
                                Optional<ButtonType> result = alert1.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK) {
                                    logActivity("Success");
                                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                                    scene = FXMLLoader.load(getClass().getResource("/views/AppointmentInformation.fxml"));
                                    stage.setScene(new Scene(scene));
                                    stage.show();
                                }
                            }
                        } catch (Exception exception) {
                        }
                    } else {
                        try {
                            ResourceBundle resourceBundle = ResourceBundle.getBundle("utilities/french", Locale.getDefault());
                            if(Locale.getDefault().getLanguage().equals("fr")) {
                                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, resourceBundle.getString("noAppt"));
                                Optional<ButtonType> result = alert2.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK) {
                                    logActivity("Success");
                                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                                    scene = FXMLLoader.load(getClass().getResource("/views/AppointmentInformation.fxml"));
                                    stage.setScene(new Scene(scene));
                                    stage.show();
                                }
                            } else {
                                Alert alert3 = new Alert(Alert.AlertType.INFORMATION, "No Appointments within 15 minutes.");
                                Optional<ButtonType> result = alert3.showAndWait();
                                if(result.isPresent() && result.get() == ButtonType.OK) {
                                    logActivity("Success");
                                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                                    scene = FXMLLoader.load(getClass().getResource("/views/AppointmentInformation.fxml"));
                                    stage.setScene(new Scene(scene));
                                    stage.show();
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            } else {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("utilities/french", Locale.getDefault());
                if(Locale.getDefault().getLanguage().equals("fr")) {
                    logActivity("Fail");
                    loginMessageLabel.setText(resourceBundle.getString("Wrong_UN_PW"));
                }
                else {
                    logActivity("Fail");
                    loginMessageLabel.setText("Incorrect username or password.");
                }

            }
        } else {
            try {
                ResourceBundle resourceBundle = ResourceBundle.getBundle("utilities/french", Locale.getDefault());
                if(Locale.getDefault().getLanguage().equals("fr")) {
                    logActivity("Fail");
                    loginMessageLabel.setText(resourceBundle.getString("Insert_UN_PW"));
                }
                else {
                    logActivity("Fail");
                    loginMessageLabel.setText("Please insert your username and password.");
                }
            } catch (Exception exception) {
                logActivity("Fail");
                loginMessageLabel.setText("Please insert your username and password.");
            }
        }
    }

    /**
     * initialize method. This method is executed when the LoginScreen.fxml scene is called. The user's timezone is
     * displayed and language options are set based on the user's computer language setting.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ZoneId zoneId = ZoneId.of(TimeZone.getDefault().getID());
        zoneIDLabel.setText(zoneId.toString());
        try {
            resourceBundle = ResourceBundle.getBundle("utilities/french", Locale.getDefault());
            if (Locale.getDefault().getLanguage().equals("fr")) {
                appointmentHeaderLabel.setText(resourceBundle.getString("Master_Appointment_Schedule"));
                userIDLabel.setText(resourceBundle.getString("User_ID"));
                passwordLabel.setText(resourceBundle.getString("Password"));
                loginButton.setText(resourceBundle.getString("Login"));
                resetButton.setText(resourceBundle.getString("Reset"));
                cancelButton.setText(resourceBundle.getString("Cancel"));
                userLocationLabel.setText(resourceBundle.getString("User_Location"));
            }
        } catch (Exception exception) {

        }
    }

    /**
     * loginAuthentication method. This method connects to the database and queries the username and password. If the
     * username and password match a database user, the method returns true and allows the user to log in.
     * @return true returns true if loginAuthentication is valid
     * @throws SQLException SQL query used
     * */
    @FXML
    public boolean loginAuthentication() throws SQLException {

        Connection connDB = JDBC.openConnection();
        String checkLogin = "SELECT count(1) FROM users WHERE User_Name = '" + usernameTextField.getText() +
                "' AND Password = '" + passwordField.getText() + "' ";

        Statement statement = connDB.createStatement();
        ResultSet queryResult = statement.executeQuery(checkLogin);

        while (queryResult.next()) {
            if (queryResult.getInt(1) == 1) {
                loginMessageLabel.setText("Welcome!");
                return true;
            }
        } return false;
    }

    /**
     * apptCheck method. This method takes the current time and runs a for loop to check for any appointments in the
     * database that are within 15 minutes of the current time. If there is an appointment, the boolean method returns
     * true, otherwise, false.
     * */
    @FXML
    private boolean apptCheck(){

            LocalDateTime time = LocalDateTime.now();
            populateAppts();

            for(Appointments appointments : appointmentsList) {
                LocalDateTime apptStart = appointments.getStart();
                long dif = ChronoUnit.MINUTES.between(time, apptStart);
                System.out.println("Difference: " + dif);
                 if(dif >= 0 && dif <= 15) {
                        appt = true;
                        apptID = appointments.getAppointmentID();
                        apptDate = String.valueOf(appointments.getStart().toLocalDate());
                        apptTime = String.valueOf(appointments.getStart().toLocalTime());
                 }
            }
        if (appt == true) {
            return true;
        }
        return false;
    }

    /**
     * populateAppts method. This method clears the AllAppointments list and runs a query from the database that will
     * repopulate all the AllAppointments list.
     * */
    private void populateAppts() {
        try {
            ListManager.getAllAppointments().clear();

            query = "SELECT * FROM appointments";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                appointmentsList.add(new Appointments(
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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * logActivity method. This method creates a new FileWriter and PrintWriter and adds any successful or failed logins
     * to the login_activity.txt file. Once the activity is written, the file closes.
     * @param writeToLog takes the variable input and does a comparison to "Success" or "Fail"
     * */
    private void logActivity(String writeToLog) throws IOException {
        String userName = usernameTextField.getText();
        FileWriter activity = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(activity);
        if(writeToLog == "Success") {
            outputFile.println("Login Successful. UserID: " + userName + " Date: " + dateTime);
        } else {
            outputFile.println("Login Failed. UserID: " + userName + " Date: " + dateTime);
        }
        outputFile.close();
        System.out.println("Log_Activity Updated.");
    }
}
