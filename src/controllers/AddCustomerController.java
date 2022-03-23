package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utilities.CountriesQuery;
import utilities.DivisionsQuery;
import utilities.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the AddCustomer controller class that will add a customer to the database.
 * */
public class AddCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    String query = null;
    Connection connection = JDBC.openConnection();

    @FXML
    private TextField customerIDTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private ComboBox<String> countryComboBox;
    @FXML
    private ComboBox<String> divisionStateComboBox;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;

    /**
     * onActionAdd method. This method is executed when the Add button is pressed. It will scrape all the info from the
     * fields and add the information to the database via the addCustomer method and switches to the CustomerInformation
     * view. divisionStatetoConvert is converted to an Integer via the convertDivision method that passes in the state
     * to be converted.
     * @param event onActionAdd method is executed when button is pressed.
     * */
    @FXML
    void onActionAdd(ActionEvent event) throws IOException {

        String divisionStatetoConvert = divisionStateComboBox.getSelectionModel().getSelectedItem();
        int divisionID = DivisionsQuery.convertDivision(divisionStatetoConvert);
        String customerName = nameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phone = phoneNumberTextField.getText();
        String createdBy = "test";
        Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedBy = "test";

        addCustomer(customerName, address, postalCode, phone, createDate,
                createdBy, lastUpdate, lastUpdatedBy, divisionID);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/CustomerInformation.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionCancel method. This method is executed when the Cancel button is pressed. It will switch to the
     * CustomerInformation view without saving.
     * @param event onActionCancel method is executed when button is pressed.
     * */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?");
        alert1.setTitle("Cancel");
        Optional<ButtonType> result = alert1.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/views/CustomerInformation.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * addCustomer method. This method takes all the information from the variables and inserts them via SQL query
     * into the database.
     * @param customerName String of the Customer's name.
     * @param address String of the Customer's address.
     * @param postalCode String of the Customer's postal code.
     * @param phone String of the Customer's phone number.
     * @param createDate Timestamp of when the Customer was created.
     * @param createdBy String of who created the Customer.
     * @param lastUpdate Timestamp of when the Customer's information was last updated.
     * @param lastUpdatedBy String of who last updated the Customer's information.
     * @param divisionID Integer of the converted division name.
     * */
    @FXML
    public void addCustomer(String customerName, String address, String postalCode, String phone, Timestamp createDate,
                            String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID) {

        try {
            query = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)" +
            " VALUES (" +
                    "'" + customerName + "'," +
                    "'" + address + "'," +
                    "'" + postalCode + "'," +
                    "'" + phone + "'," +
                    "'" + createDate + "'," +
                    "'" + createdBy + "'," +
                    "'" + lastUpdate + "'," +
                    "'" + lastUpdatedBy + "'," +
                    "'" + divisionID + "'" +
                    ")";

            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * selectCountry method. This method takes the country selected and filters/populates the divisionStateComboBox
     * based on which country is selected.
     * @param event activated once a country is selected from the ComboBox.
     * */
    @FXML
    void selectCountry(ActionEvent event) {
        String country = countryComboBox.getSelectionModel().getSelectedItem().toString();
        if(country.equals("U.S")) {
            divisionStateComboBox.setItems(DivisionsQuery.populateAllDivisionsNamesUS());
        } else if (country.equals("UK")) {
            divisionStateComboBox.setItems(DivisionsQuery.populateAllDivisionsNamesUK());
        } else
            divisionStateComboBox.setItems(DivisionsQuery.populateAllDivisionsNamesCanada());
    }

    /**
     * initialize method. This method is executed when the AddCustomer.fxml scene is called and sets up the view, loading
     * the countryComboBox.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(CountriesQuery.populateAllCountriesNames());
    }
}
