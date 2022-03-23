package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Customers;
import utilities.JDBC;
import utilities.ListManager;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is the CustomerInformation controller class that will display all customers in TableView, give options to
 * add/update/delete customers, and return to the AppointmentInformation view.
 * */
public class CustomerInformationController implements Initializable {

    Stage stage;
    Parent scene;

    String query = null;
    Connection connection = JDBC.openConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Customers customers = null;
    public static Customers customerToUpdate;

    @FXML
    private TableView<Customers> customerTblView;
    @FXML
    private TableColumn<Customers, Integer> customerIDTableColumn;
    @FXML
    private TableColumn<Customers, String> nameTableColumn;
    @FXML
    private TableColumn<Customers, String> addressTableColumn;
    @FXML
    private TableColumn<Customers, String> postalCodeTableColumn;
    @FXML
    private TableColumn<Customers, String> phoneNumberTableColumn;
    @FXML
    private TableColumn<Customers, String> createDateTableColumn;
    @FXML
    private TableColumn<Customers, String> createdByTableColumn;
    @FXML
    private TableColumn<Customers, LocalTime> lastUpdateTableColumn;
    @FXML
    private TableColumn<Customers, String> lastUpdatedByTableColumn;
    @FXML
    private TableColumn<Customers, Integer> divisionIDTableColumn;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button updateCustomerButton;
    @FXML
    private Button deleteCustomerButton;
    @FXML
    private Button mainMenuButton;

    /**
     * onActionAddCustomer method. This method switches to the AddCustomer.fxml screen.
     * @param event onActionAddCustomer method is executed when button is pressed.
     * */
    @FXML
    void onActionAddCustomer(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionUpdateCustomer method. This method switches to the UpdateCustomer.fxml screen.
     * @param event onActionUpdateCustomer method is executed when button is pressed.
     * */
    @FXML
    void onActionUpdateCustomer(ActionEvent event) throws IOException {

        customerToUpdate = customerTblView.getSelectionModel().getSelectedItem();

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/UpdateCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * onActionDeleteCustomer method. This method deletes the selected customer and associated appointments. It will
     * alert the user if they are sure they want to delete the customer and associated appointments. If confirmed, the
     * method will delete the appointment from the database then delete the customer, and finally refresh the TableView.
     * @param event onActionDeleteCustomer method is executed when button is pressed.
     * */
    @FXML
    void onActionDeleteCustomer(ActionEvent event) {

        try {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete? All associated appointments will also be deleted.");
            alert1.setTitle("Delete Customer and Appointments");
            Optional<ButtonType> result = alert1.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {
                customers = customerTblView.getSelectionModel().getSelectedItem();
                query = "DELETE FROM appointments WHERE Customer_ID = " + customers.getCustomerID();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();

                customers = customerTblView.getSelectionModel().getSelectedItem();
                query = "DELETE FROM customers WHERE Customer_ID = " + customers.getCustomerID();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
                refreshCustomers();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * onActionMainMenu method. This method switches to the AppointmentInformation.fxml screen.
     * @param event onActionMainMenu method is executed when button is pressed.
     * */
    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/views/AppointmentInformation.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * initialize method. This method is executed when the CustomerInformation.fxml scene is called and sets up the
     * TableView and shows all the appointments via the refreshAppointments method.
     * @param url used to resolve relative paths for the root object.
     * @param resourceBundle used to localize the root object.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshCustomers();
        customerIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeTableColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        createDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByTableColumn.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedByTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        divisionIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
    }

    /**
     * refreshCustomers method. This method is refreshes all customers in the database. The AllCustomers list is
     * cleared out and refreshed with all customers from the database. The customers are then displayed via TableView.
     * */
    @FXML
    public void refreshCustomers() {

        try {
            ListManager.getAllCustomers().clear();

            query = "SELECT * FROM customers";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ListManager.getAllCustomers().add(new Customers(
                        resultSet.getInt("Customer_ID"),
                        resultSet.getString("Customer_Name"),
                        resultSet.getString("Address"),
                        resultSet.getString("Postal_Code"),
                        resultSet.getString("Phone"),
                        resultSet.getTimestamp("Create_Date").toLocalDateTime(),
                        resultSet.getString("Created_By"),
                        resultSet.getTimestamp("Last_Update"),
                        resultSet.getString("Last_Updated_By"),
                        resultSet.getInt("Division_ID")));
                customerTblView.setItems(ListManager.getAllCustomers());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
