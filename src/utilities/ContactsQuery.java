package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Contacts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the ContactsQuery utility class that holds queries and various methods for the Contacts controllers.
 * */
public class ContactsQuery {

    /**
     * populateAllContacts method. This method runs a query that gets all the information from contacts and adds it all
     * to the AllContacts list.
     * */
    @FXML
    public static void populateAllContacts() {

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT * FROM contacts;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ListManager.getAllContacts().add(new Contacts(
                        resultSet.getInt("Contact_ID"),
                        resultSet.getString("Contact_Name"),
                        resultSet.getString("Email")));
                ListManager.getAllContacts().addAll();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * populateAllContactsNames method. This method creates a new ObservableList and runs a query that gets all the
     * Contact_Names from contacts.
     * @return AllContactsNames ObservableList that holds all Contact_Names from contacts.
     * */
    @FXML
    public static ObservableList populateAllContactsNames() {

        ObservableList<String> AllContactsNames = FXCollections.observableArrayList();

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Contact_Name FROM contacts;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                AllContactsNames.add(resultSet.getString("Contact_Name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return AllContactsNames;
    }

    /**
     * convertContactIDToContactName method. This method takes a contactID and converts it to a contactName.
     * @param contactIDToContactName contactID that needs to be converted to an contactName.
     * @return contactName contactName of the contactID.
     * */
    @FXML
    public static String convertContactIDToContactName(int contactIDToContactName) {

        String contactName = null;
        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Contact_Name FROM contacts WHERE Contact_ID = " + "'" + contactIDToContactName + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                contactName = resultSet.getString("Contact_Name");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactName;
    }
}
