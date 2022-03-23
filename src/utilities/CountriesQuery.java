package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import models.Countries;
import java.sql.*;

/**
 * This is the CountriesQuery utility class that holds queries and various methods for the Countries controllers.
 * */
public class CountriesQuery {

    /**
     * populateAllCountries method. This method runs a query that gets all the information from countries and adds it all
     * to the AllCountries list.
     * */
    @FXML
    public static void populateAllCountries() {

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT * FROM countries;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                ListManager.getAllCountries().add(new Countries(
                        resultSet.getInt("Country_ID"),
                        resultSet.getString("Country"),
                        resultSet.getTimestamp("Create_Date").toLocalDateTime(),
                        resultSet.getString("Created_By"),
                        resultSet.getTimestamp("Last_Update"),
                        resultSet.getString("Last_Updated_By")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * populateAllCountriesNames method. This method creates a new ObservableList and runs a query that gets all the
     * Countries from countries.
     * @return getAllCountriesNamesv2 ObservableList that holds all Countries from countries.
     * */
    @FXML
    public static ObservableList populateAllCountriesNames() {

        ObservableList<String> getAllCountriesNamesv2 = FXCollections.observableArrayList();

        String query = null;
        Connection connection = JDBC.openConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            query = "SELECT Country FROM countries;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                getAllCountriesNamesv2.add(resultSet.getString("Country"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return getAllCountriesNamesv2;
    }
}

