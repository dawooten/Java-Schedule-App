package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Customers;
import utilities.CountriesQuery;
import utilities.DivisionsQuery;
import utilities.JDBC;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the UpdateCustomer controller class that will update a customer in the database.
 * */
public class UpdateCustomerController implements Initializable {

    private Customers customerToUpdate = CustomerInformationController.customerToUpdate;

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
    private Button updateButton;
    @FXML
    private Button cancelButton;

    /**
     * onActionUpdate method. This method is executed when the Update button is pressed. It will scrape all the info from
     * the fields and update the information to the database via the updateCustomer method and switches to the
     * CustomerInformation view. divisionStatetoConvert is converted to an Integer via the convertDivision method that
     * passes in the state to be converted.
     * @param event onActionUpdate method is executed when button is pressed.
     * */
    @FXML
    void onActionUpdate(ActionEvent event) throws IOException {

        int customerID = Integer.parseInt(customerIDTextField.getText());
        String customerName = nameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phone = phoneNumberTextField.getText();
        String createdBy = "admin";
        Timestamp createDate = Timestamp.valueOf(LocalDateTime.now());
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedBy = "admin";
        String divisionStatetoConvert = divisionStateComboBox.getSelectionModel().getSelectedItem();
        int divisionID = DivisionsQuery.convertDivision(divisionStatetoConvert);

        updateCustomer(customerID, customerName, address, postalCode, phone, createDate,
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
     * initialize method. This method is executed when the UpdateCustomer.fxml scene is called and sets up the view.
     * All the fields are populated from customerToUpdate item that was selected on the CustomerInformation.fxml
     * screen.  The country ComboBox is set to the country that was selected based on the divisionID. The division
     * ComboBox is generated by converting the IDs to names and populating based on which country is selected.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIDTextField.setText(String.valueOf(customerToUpdate.getCustomerID()));
        nameTextField.setText(customerToUpdate.getCustomerName());
        addressTextField.setText(customerToUpdate.getAddress());
        postalCodeTextField.setText(customerToUpdate.getPostalCode());
        phoneNumberTextField.setText(customerToUpdate.getPhone());
        countryComboBox.setItems(CountriesQuery.populateAllCountriesNames());

        if(customerToUpdate.getDivisionID() >= 1 && customerToUpdate.getDivisionID() <= 54) {
            countryComboBox.getSelectionModel().select(0);
        } else if (customerToUpdate.getDivisionID() >= 101 && customerToUpdate.getDivisionID() <= 104) {
            countryComboBox.getSelectionModel().select(1);
        } else {
            countryComboBox.getSelectionModel().select(2);
        }

        int divisionIDToDivision = customerToUpdate.getDivisionID();
        String divisionNames = DivisionsQuery.convertDivisionIDToDivision(divisionIDToDivision);
        divisionStateComboBox.getSelectionModel().select(divisionNames);
    }

    /**
     * updateCustomer method. This method takes all the information from the variables and updates them via SQL query
     * into the database.
     * @param customerID Integer of the Customer's ID. Auto-generated.
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
    public void updateCustomer(int customerID, String customerName, String address, String postalCode, String phone, Timestamp createDate,
                            String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionID) {

        try {

            query = "UPDATE customers SET Customer_Name = " + "'" + customerName + "'," +
                            "Address = " + "'" + address + "'," +
                            "Postal_Code = " + "'" + postalCode + "'," +
                            "Phone = " + "'" + phone + "'," +
                            "Create_Date = " + "'" + createDate + "'," +
                            "Created_By = " + "'" + createdBy + "'," +
                            "Last_Update = " + "'" + lastUpdate + "'," +
                            "Last_Updated_By = " + "'" + lastUpdatedBy + "'," +
                            "Division_ID = " + "" + divisionID +  " " +
                            "WHERE Customer_ID = " + customerID + "";

            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}