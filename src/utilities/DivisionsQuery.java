package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.FirstLevelDivisions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is the DivisionsQuery utility class that holds queries and various methods for the Divisions controllers.
 * */
public class DivisionsQuery {

    /**
     * populateAllDivisions method. This method runs a query that gets all information from first_level_divisions and
     * adds it all to the AllDivisions list.
     * */
    @FXML
    public static void populateAllDivisions() {

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT * FROM first_level_divisions;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ListManager.getAllDivisions().add(String.valueOf(new FirstLevelDivisions(
                        resultSet.getInt("Division_ID"),
                        resultSet.getString("Division"),
                        resultSet.getTimestamp("Create_Date").toLocalDateTime(),
                        resultSet.getString("Created_By"),
                        resultSet.getTimestamp("Last_Update"),
                        resultSet.getString("Last_Updated_By"),
                        resultSet.getInt("Country_ID"))));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * populateAllDivisionsNamesUS method. This method runs a query that gets Division from first_level_divisions where
     * the Country_ID is 1 and adds it all to the getAllDivisionsNamesUSv2 list.
     * @return getAllDivisionsNamesUSv2 ObservableList that holds all divisions where the Country_ID is 1
     * */
    @FXML
    public static ObservableList populateAllDivisionsNamesUS() {

        ObservableList<String> getAllDivisionsNamesUSv2 = FXCollections.observableArrayList();

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Division FROM first_level_divisions WHERE Country_ID = 1;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                getAllDivisionsNamesUSv2.add(resultSet.getString("Division"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getAllDivisionsNamesUSv2;
    }

    /**
     * populateAllDivisionsNamesUK method. This method runs a query that gets Division from first_level_divisions where
     * the Country_ID is 2 and adds it all to the getAllDivisionsNamesUKv2 list.
     * @return getAllDivisionsNamesUKv2 ObservableList that holds all divisions where the Country_ID is 2
     * */
    @FXML
    public static ObservableList populateAllDivisionsNamesUK() {

        ObservableList<String> getAllDivisionsNamesUKv2 = FXCollections.observableArrayList();

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Division FROM first_level_divisions WHERE Country_ID = 2;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                getAllDivisionsNamesUKv2.add(resultSet.getString("Division"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getAllDivisionsNamesUKv2;
    }

    /**
     * populateAllDivisionsNamesCanada method. This method runs a query that gets Division from first_level_divisions where
     * the Country_ID is 3 and adds it all to the getAllDivisionsNamesCanadav2 list.
     * @return getAllDivisionsNamesCanadav2 ObservableList that holds all divisions where the Country_ID is 3
     * */
    @FXML
    public static ObservableList populateAllDivisionsNamesCanada() {

        ObservableList<String> getAllDivisionsNamesCanadav2 = FXCollections.observableArrayList();

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Division FROM first_level_divisions WHERE Country_ID = 3;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                getAllDivisionsNamesCanadav2.add(resultSet.getString("Division"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getAllDivisionsNamesCanadav2;
    }

    /**
     * convertDivision method. This method takes a division and converts it to a divisionID.
     * @param divisionStatetoConvert division that needs to be converted to an divisionID.
     * @return divisionID divisionID of the division.
     * */
    @FXML
    public static int convertDivision(String divisionStatetoConvert) {

        int divisionID = 0;
        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Division_ID FROM first_level_divisions WHERE Division = " + "'" + divisionStatetoConvert + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                divisionID = resultSet.getInt("Division_ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return divisionID;
    }

    /**
     * convertDivisionIDToDivision method. This method takes a divisionID and converts it to a division.
     * @param divisionIDToDivision divisionID that needs to be converted to an division.
     * @return divisionName division of the divisionID.
     * */
    @FXML
    public static String convertDivisionIDToDivision(int divisionIDToDivision) {

        String divisionName = null;
        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Division FROM first_level_divisions WHERE Division_ID = " + "'" + divisionIDToDivision + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                divisionName = resultSet.getString("Division");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return divisionName;
    }

    /**
     * getDivisionIDFromMainMenu method. This method takes a division from customers and converts it to a divisionID.
     * @param divisionStatetoConvert division that needs to be converted to an divisionID.
     * @return divisionID divisionID of the division.
     * */
    @FXML
    public static int getDivisionIDFromMainMenu(String divisionStatetoConvert) {

        int divisionID = 0;
        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Division_ID FROM customers WHERE Division = " + "'" + divisionStatetoConvert + "'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                divisionID = resultSet.getInt("Division_ID");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return divisionID;
    }

}
