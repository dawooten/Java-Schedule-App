package utilities;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the CustomerQuery utility class that holds queries and various methods for the Customers controllers.
 * */
public class CustomerQuery {

    /**
     * populateAllCustomerNames method. This method runs a query that gets Customer_Names from customers and adds it all
     * to the AllCustomerNames list.
     * @return AllCustomerNames ObservableList that holds Customer_Names from customers.
     * */
    @FXML
    public static ObservableList populateAllCustomerNames() {

        ObservableList<String> AllCustomerNames = FXCollections.observableArrayList();

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

            try {
            query = "SELECT Customer_Name FROM customers;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                AllCustomerNames.add(resultSet.getString("Customer_Name"));
            }
        } catch (
        SQLException throwables) {
            throwables.printStackTrace();
        }
        return AllCustomerNames;
    }

    /**
     * convertCustomerIDToCustomerName method. This method takes a customerID and converts it to a customerName.
     * @param customerIDToCustomerName customerID that needs to be converted to an customerName.
     * @return customerName customerName of the customerID.
     * */
    @FXML
    public static String convertCustomerIDToCustomerName(int customerIDToCustomerName) {

        String customerName = null;
        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Customer_Name FROM customers WHERE Customer_ID = " + "'" + customerIDToCustomerName + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                customerName = resultSet.getString("Customer_Name");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerName;
    }
}



