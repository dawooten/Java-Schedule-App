package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the AppointmentsQuery utility class that holds queries and various methods for the Appointments controllers.
 * */
public class AppointmentsQuery {

    /**
     * populateAllUserIDNames method. This method creates a new ObservableList and runs a query that gets all the
     * usernames from users.
     * @return AllUserIDNames ObservableList that holds all usernames from users.
     * */
    @FXML
    public static ObservableList populateAllUserIDNames() {

        ObservableList<String> AllUserIDNames = FXCollections.observableArrayList();

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT User_Name FROM users;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                AllUserIDNames.add(resultSet.getString("User_Name"));
            }
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        return AllUserIDNames;
    }

    /**
     * convertUserNameToUserID method. This method takes a userName and converts it to a userID.
     * @param userNameToConvert userName that needs to be converted to an userID.
     * @return userID userID of the userName.
     * */
    @FXML
    public static int convertUserNameToUserID(String userNameToConvert) {

        int userID = 0;
        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT User_ID FROM users WHERE User_Name = " + "'" + userNameToConvert + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                userID = resultSet.getInt("User_ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userID;
    }

    /**
     * convertCustomerNameToCustomerID method. This method takes a customerName and converts it to a customerID.
     * @param customerNameToConvert customerName that needs to be converted to an customerID.
     * @return customerID customerID of the customerName.
     * */
    @FXML
    public static int convertCustomerNameToCustomerID(String customerNameToConvert) {

        int customerID = 0;
        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Customer_ID FROM customers WHERE Customer_Name = " + "'" + customerNameToConvert + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                customerID = resultSet.getInt("Customer_ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerID;
    }

    /**
     * convertContactNameToContactID method. This method takes a contactName and converts it to a contactID.
     * @param contactNameToConvert contactName that needs to be converted to an contactID.
     * @return contactID contactID of the contactName.
     * */
    @FXML
    public static int convertContactNameToContactID(String contactNameToConvert) {

        int contactID = 0;
        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Contact_ID FROM contacts WHERE Contact_Name = " + "'" + contactNameToConvert + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                contactID = resultSet.getInt("Contact_ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactID;
    }

    /**
     * typeAppt method. This method creates an ObservableList and gets all the types from the appointments table.
     * @return TypeAppt ObservableList of all the types of appointments.
     * */
    @FXML
    public static ObservableList typeAppt() {

        ObservableList<String> TypeAppt = FXCollections.observableArrayList();

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Type FROM appointments;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TypeAppt.add(resultSet.getString("Type"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return TypeAppt;
    }

}
