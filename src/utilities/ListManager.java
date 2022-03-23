package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;

/**
 * This is the ListManager utility class that holds all the lists with their getters and setter throughout the application.
 * */
public class ListManager {

    private static ObservableList<Customers> AllCustomers = FXCollections.observableArrayList();
    private static ObservableList<Users> AllUsers = FXCollections.observableArrayList();
    private static ObservableList<Countries> AllCountries = FXCollections.observableArrayList();
    private static ObservableList<String> AllDivisions = FXCollections.observableArrayList();
    private static ObservableList<Contacts> AllContacts = FXCollections.observableArrayList();
    private static ObservableList<Appointments> AllAppointments = FXCollections.observableArrayList();
    private static ObservableList<String> AllContactsNames = FXCollections.observableArrayList();
    private static ObservableList<String> AllCountriesNames = FXCollections.observableArrayList();
    private static ObservableList<String> FilteredDivisionsUS = FXCollections.observableArrayList();
    private static ObservableList<String> FilteredDivisionsUK = FXCollections.observableArrayList();
    private static ObservableList<String> FilteredDivisionsCanada= FXCollections.observableArrayList();
    private static ObservableList<Integer> ConvertedDivision = FXCollections.observableArrayList();
    private static ObservableList<String> AllCustomerNames = FXCollections.observableArrayList();
    private static ObservableList<String> AllUserIDNames = FXCollections.observableArrayList();
    private static ObservableList<String> TypeAppt = FXCollections.observableArrayList();

    /**
     * setAllAppointments setter. Sets allAppointments.
     * @param allAppointments ObservableList allAppointments.
     * */
    public static void setAllAppointments(ObservableList<Appointments> allAppointments) {
        AllAppointments = allAppointments;
    }

    /**
     * getTypeAppt getter. Returns TypeAppt.
     * @return ObservableList TypeAppt.
     * */
    public static ObservableList<String> getTypeAppt() {
        TypeAppt.addAll("Planning Session", "Department Meeting", "Staff Meeting", "Private Meeting", "Blocked");
        return TypeAppt;
    }

    /**
     * getAllUserIDNames getter. Returns AllUserIDNames.
     * @return ObservableList AllUserIDNames.
     * */
    public static ObservableList<String> getAllUserIDNames() {
        return AllUserIDNames;
    }

    /**
     * getAllCustomerNames getter. Returns AllCustomerNames.
     * @return ObservableList AllCustomerNames.
     * */
    public static ObservableList<String> getAllCustomerNames() {
        return AllCustomerNames;
    }

    /**
     * getConvertedDivision getter. Returns ConvertedDivision.
     * @return ObservableList ConvertedDivision.
     * */
    public static ObservableList<Integer> getConvertedDivision() {
        return ConvertedDivision;
    }

    /**
     * getAllContactsNames getter. Returns AllContactsNames.
     * @return ObservableList AllContactsNames.
     * */
    public static ObservableList<String> getAllContactsNames() {
        return AllContactsNames;
    }

    /**
     * getAllCountriesNames getter. Returns AllCountriesNames.
     * @return ObservableList AllCountriesNames.
     * */
    public static ObservableList<String> getAllCountriesNames() {
        return AllCountriesNames;
    }

    /**
     * getAllUsers getter. Returns AllUsers.
     * @return ObservableList AllUsers.
     * */
    public static ObservableList<Users> getAllUsers() {
        return AllUsers;
    }

    /**
     * addUser method. Adds user to AllUsers.
     * @param user user that is added to AllUsers.
     * */
    public static void addUser(Users user) {
        AllUsers.add(user);
    }

    /**
     * getAllCustomers getter. Returns AllCustomers.
     * @return ObservableList AllCustomers.
     * */
    public static ObservableList<Customers> getAllCustomers() {
        return AllCustomers;
    }

    /**
     * addCustomer method. Adds customer to AllCustomers.
     * @param customer customer that is added to AllCustomers.
     * */
    public static void addCustomer(Customers customer) {
        AllCustomers.add(customer);
    }

    /**
     * getAllCountries getter. Returns AllCountries.
     * @return ObservableList AllCountries.
     * */
    public static ObservableList<Countries> getAllCountries() {
        return AllCountries;
    }

    /**
     * getAllDivisions getter. Returns AllDivisions.
     * @return ObservableList AllDivisions.
     * */
    public static ObservableList<String> getAllDivisions() {
        return AllDivisions;
    }

    /**
     * getAllContacts getter. Returns AllContacts.
     * @return ObservableList AllContacts.
     * */
    public static ObservableList<Contacts> getAllContacts() {
        return AllContacts;
    }

    /**
     * getAllAppointments getter. Returns AllAppointments.
     * @return ObservableList AllAppointments.
     * */
    public static ObservableList<Appointments> getAllAppointments() {
        return AllAppointments;
    }

    /**
     * getFilteredDivisionsUS getter. Returns FilteredDivisionsUS.
     * @return ObservableList FilteredDivisionsUS.
     * */
    public static ObservableList<String> getFilteredDivisionsUS() {
        return FilteredDivisionsUS;
    }

    /**
     * getFilteredDivisionsUK getter. Returns FilteredDivisionsUK.
     * @return ObservableList FilteredDivisionsUK.
     * */
    public static ObservableList<String> getFilteredDivisionsUK() {
        return FilteredDivisionsUK;
    }

    /**
     * getFilteredDivisionsCanada getter. Returns FilteredDivisionsCanada.
     * @return ObservableList FilteredDivisionsCanada.
     * */
    public static ObservableList<String> getFilteredDivisionsCanada() {
        return FilteredDivisionsCanada;
    }
}
