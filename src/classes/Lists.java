
package classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *Lists Class
 * @author Mariya.Trenkina
 */
public class Lists {
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static ObservableList<State> allStates = FXCollections.observableArrayList();
    private static ObservableList<State> associatedStates = FXCollections.observableArrayList();
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    
    /**
     * get All Contacts
     * @return get All Contacts
     */
    public static ObservableList<Contact> getAllContacts() {
        return allContacts;
    
    }
    
    /**
     * add Contact
     * @param newContact add Contact
     */
    public static void addContact(Contact newContact) {
        allContacts.add(newContact);
    }
     
    /**
     * get All Customers
     * @return get All Customers
     */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    
    }

    /**
     * get allAppointments list
     * @return get allAppointments list
     */
    public static ObservableList<Appointment> getAllAppointments() {
        return allAppointments;
    
    }

    /**
     * get monthlyAppointments list
     * @return get monthlyAppointments list
     */
    public static ObservableList<Appointment> getMonthlyAppointments() {
        return monthlyAppointments;
    
    }
    
    /**
     * get weeklyAppointments list
     * @return get weeklyAppointments list
     */
    public static ObservableList<Appointment> getWeeklyAppointments() {
        return weeklyAppointments;
    
    }
    
    /**
     * add Customer
     * @param newCustomer add Customer
     */
    public static void addCustomer(Customer newCustomer) {
        allCustomers.add(newCustomer);
    }

    /**
     * get allCountries list
     * @return get allCountries list
     */
    public static ObservableList<Country> getAllCountries() {
        return allCountries;
    
    }

    /**
     * add Country
     * @param newCountry add Country
     */
    public static void addCountry(Country newCountry) {
        allCountries.add(newCountry);
    }
    
    /**
     * get allStates list
     * @return get allStates list
     */
    public static ObservableList<State> getAllStates() {
        return allStates;
    
    }

    /**
     * add State
     * @param newState add State
     */
    public static void addState(State newState) {
        allStates.add(newState);
    }

    /**
     * add to allAppointments list
     * @param newAppointment add to allAppointments list
     */
    public static void addAllAppointment(Appointment newAppointment) {
        allAppointments.add(newAppointment);
    }

    /**
     * add to monthlyAppointments list
     * @param newAppointment add to monthlyAppointments list
     */
    public static void addMonthlyAppointment(Appointment newAppointment) {
        monthlyAppointments.add(newAppointment);
    }
    
    /**
     * add to weeklyAppointments list
     * @param newAppointment add to weeklyAppointments list
     */
    public static void addWeeklyAppointment(Appointment newAppointment) {
        weeklyAppointments.add(newAppointment);
    }
    
    /**
     * get associatedStates list
     * @return get associatedStates list
     */
    public static ObservableList<State> getAssociatedStates() {
        return associatedStates;
    
    }

    /**
     * add to associatedStates list
     * @param newState add to associatedStates list
     */
    public static void addAssociatedState(State newState) {
        associatedStates.add(newState);
    }

    /**
     *Clear list "associatedStates"
     */
    public static void clearAssociatedStates() {
        associatedStates.clear();
    }

    /**
     *Clear list "allCustomers"
     */
    public static void clearCustomers() {
        allCustomers.clear();
    }

    /**
     *Clear list "allAppointments"
     */
    public static void clearAllAppointments() {
      allAppointments.clear();
    }

    /**
     *Clear list "monthlyAppointments"
     */
    public static void clearMonthlyAppointments() {
      monthlyAppointments.clear();
    }
    
    /**
     *Clear list "weeklyAppointments"
     */
    public static void clearWeeklyAppointments() {
        weeklyAppointments.clear();
    }
}
