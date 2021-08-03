/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utills;

import classes.Appointment;
import classes.C195;
import classes.Contact;
import classes.Country;
import classes.Customer;
import classes.Lists;
import classes.State;
import classes.TimeConvert;
import controller.Customers_FormController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *DBQuery Class
 * @author Mariya.Trenkina
 */
public class DBQuery {
    private static Statement statement;
    
    //create statement object

    /**
     *set Statement
     * @param conn connection
     * @throws SQLException SQL error catch
     */
    public static void setStatement(Connection conn) throws SQLException{
    statement = conn.createStatement();
    }
    
    //return statement object

    /**
     *get Statement
     * @return get Statement
     */
    public static Statement getStatement(){
    return statement;
    }
    
    /**
     *Update Customer information in SQL
     * @param customerID get customerID
     * @param customerName get customerName
     * @param address get address
     * @param postalCode get postalCode
     * @param phone get phone
     * @param lastUpdatedBy getlastUpdatedBy
     * @param lastUpdate getlastUpdate
     * @param divisionId get divisionId
     * @throws SQLException SQL Error Catch
     */
     
    
    public void updateCustomerSQL(int customerID, String customerName, String address, String postalCode, String phone, String lastUpdatedBy, int divisionId, String lastUpdate) throws SQLException{
    Connection conn = DBConnection.startConnection();
    //connect to database
             DBQuery.setStatement(conn);
             //create statement object
             Statement statement = DBQuery.getStatement();//get statement reference
             String updateCustomer = "UPDATE WJ07bUN.customers SET " +
                     "Customer_Name = '"+customerName+"', " +
                     "Address = '"+address+"', " +
                     "Postal_Code = '"+postalCode+"', " +
                     "Phone = '"+phone+"', " +
                     "Last_Updated_By = '"+lastUpdatedBy+"', " +
                     "Division_ID = '"+divisionId+"', " +
                     "Last_Update = '"+lastUpdate+"' " +
                     "WHERE Customer_ID = "+customerID;
             statement .execute(updateCustomer);//execute statement
             if(statement .getUpdateCount() > 0 ){
             System.out.println(statement.getUpdateCount()+ " row(s) affected!");
             } else{
             System.out.println("No change!");
             }
    }

    /**
     *Add Customer in SQL
     * @param customerName customer Name
     * @param address address
     * @param postalCode postalCode
     * @param phone phone
     * @param createdBy createdBy
     * @param lastUpdatedBy lastUpdatedBy
     * @param divisionId division Id
     * @throws SQLException SQL error catch
     */
    public void addCustomerSQL(String customerName, String address, String postalCode, String phone, String createdBy, String lastUpdatedBy, int divisionId) throws SQLException{
    Connection conn = DBConnection.startConnection();
    //connect to database
             DBQuery.setStatement(conn);
             //create statement object
             Statement statement = DBQuery.getStatement();//get statement reference
             String insertCustomer = "INSERT INTO WJ07bUN.customers (Customer_Name, Address, Postal_Code, Phone,Created_By,Last_Updated_By,Division_ID)\n" +
    "VALUES ('"+customerName+"','"+address+"', '"+postalCode+"', '"+phone+"','"+createdBy+"','"+lastUpdatedBy+"','"+divisionId+"')";
             statement .execute(insertCustomer);//execute statement
             if(statement .getUpdateCount() > 0 ){
             System.out.println(statement.getUpdateCount()+ " row(s) affected!");
             } else{
             System.out.println("No change!");
             }
             
    }
    
    /**
     *Delete Customer from SQL
     * @param customerId customerId
     * @throws SQLException SQL error catch
     */
    public void deleteCustomerSQL(int customerId) throws SQLException{
    Connection conn = DBConnection.startConnection();
    DBQuery.setStatement(conn);
    Statement statement = DBQuery.getStatement();
    String deleteCustomer = "DELETE FROM WJ07bUN.customers WHERE Customer_ID = '"+customerId+"'";
    statement .execute(deleteCustomer);
    if(statement .getUpdateCount() > 0 ){
             System.out.println(statement.getUpdateCount()+ " row(s) affected!");
             } else{
             System.out.println("No change!");
             }
    }

    /**
     *Update Appointment in SQL
     * @param appointmentID appointment ID
     * @param title title
     * @param description description
     * @param location location
     * @param lastUpdated lastUpdated
     * @param type type
     * @param start start
     * @param end end
     * @param customerID customerID
     * @param userID user ID
     * @param contactID contact ID
     * @throws SQLException SQL error catch
     */
    public void updateAppointmentSQL(int appointmentID,String title, String description, String location, String lastUpdated, String type, String start, String end, int customerID, int userID, int contactID) throws SQLException{
    Connection conn = DBConnection.startConnection();
    //connect to database
             DBQuery.setStatement(conn);
             //create statement object
             Statement statement = DBQuery.getStatement();//get statement reference
             String updateAppointment = "UPDATE WJ07bUN.appointments SET " +
                     "Title = '"+title+"', " +
                     "Description = '"+description+"', " +
                     "Location = '"+location+"', " +
                     "Type = '"+type+"', " +
                     "Start = '"+start+"', " +
                     "End = '"+end+"', " +
                     "Last_Update = '"+lastUpdated+"', " +
                     "Last_Updated_By = '"+C195.loggedInUser+"', " +
                     "Customer_ID = '"+customerID+"', " +
                     "User_ID = '"+userID+"', " +   
                     "Contact_ID = '"+contactID+"' " +
                     "WHERE Appointment_ID = "+appointmentID;
             statement .execute(updateAppointment);//execute statement
             if(statement .getUpdateCount() > 0 ){
             System.out.println(statement.getUpdateCount()+ " row(s) affected!");
             } else{
             System.out.println("No change!");
             }
    }

    /**
     *Add Appointment in SQL
     * @param title title  
     * @param description description
     * @param location location
     * @param type type
     * @param start start
     * @param end end
     * @param customerID customer ID
     * @param userID user ID
     * @param contactID contact ID
     * @throws SQLException SQL error catch
     */
    public void addAppointmentSQL(String title, String description, String location, String type, String start, String end, int customerID, int userID, int contactID) throws SQLException{
             Connection conn = DBConnection.startConnection();
    //connect to database
             DBQuery.setStatement(conn);
             //create statement object
             Statement statement = DBQuery.getStatement();//get statement reference
             String insertAppointment = "INSERT INTO WJ07bUN.appointments (Title, Description, Location, Type, Start, End,Created_By,Last_Updated_By,Customer_ID,User_ID,Contact_ID)\n" +
    "VALUES ('"+title+"','"+description+"', '"+location+"', '"+type+"','"+start+"','"+end+"','"+C195.loggedInUser+"','"+C195.loggedInUser+"','"+customerID+"','"+userID+"','"+contactID+"')";
             statement .execute(insertAppointment);//execute statement
             if(statement .getUpdateCount() > 0 ){
             System.out.println(statement.getUpdateCount()+ " row(s) affected!");
             } else{
             System.out.println("No change!");
             }
             
    }

    /**
     *Delete Appointment from SQL
     * @param appointmentID appointment ID
     * @throws SQLException SQL error catch
     */
    public void deleteAppointmentSQL(int appointmentID) throws SQLException{
    Connection conn = DBConnection.startConnection();
    DBQuery.setStatement(conn);
    Statement statement = DBQuery.getStatement();
    String deleteAppointment = "DELETE FROM WJ07bUN.appointments WHERE Appointment_ID = '"+appointmentID+"'";
    statement .execute(deleteAppointment);
    if(statement .getUpdateCount() > 0 ){
             System.out.println(statement.getUpdateCount()+ " row(s) affected!");
             } else{
             System.out.println("No change!");
             }
    }
    
    //gets countries from sql table and puts into observable list

    /**
     *get info from countries table
     * @throws SQLException SQL error catch
     */
    public void pullAllCountries() throws SQLException{
    Connection conn = DBConnection.startConnection();
             //connect to database
             DBQuery.setStatement(conn);
             //create statement object
             Statement statement = DBQuery.getStatement();//get statement reference
             String selectCountries = "SELECT * FROM countries";//select statement
             statement .execute(selectCountries);//execute statement
             ResultSet rsCountries = statement.getResultSet();//get ResultsSet
             while(rsCountries.next()){

                 //Set variables from each row
                 int countryId = rsCountries.getInt("Country_ID");
                 String country = rsCountries.getString("Country");
                 Lists.addCountry(new Country(countryId, country));
             }
    }
    //gets states from sql table and puts into observable list

    /**
     *get info from first_level_divisions table
     * @throws SQLException SQL error catch
     */
     public void pullAllStates() throws SQLException{
    Connection conn = DBConnection.startConnection();
             //connect to database
             DBQuery.setStatement(conn);
             //create statement object
             Statement statement = DBQuery.getStatement();//get statement reference
             String selectStates = "SELECT * FROM first_level_divisions";//select statement
             statement .execute(selectStates);//execute statement
             ResultSet rsStates = statement.getResultSet();//get ResultsSet
             while(rsStates.next()){

                 //Set variables from each row
                 int divisionId = rsStates.getInt("Division_ID");
                 String division = rsStates.getString("Division");
                 int countryId = rsStates.getInt("COUNTRY_ID");
                 Lists.addState(new State(divisionId, division, countryId ));
             }
    }
    //gets all customers from sql table and puts into observable list

    /**
     *get info from customers table
     */
    public void getAllCustomers(){
 try {

             Connection conn = DBConnection.startConnection();
             //connect to database
             DBQuery.setStatement(conn);
             //create statement object
             Statement statement = DBQuery.getStatement();//get statement reference
             String selectCustomers = "SELECT * FROM customers";//select statement
             statement .execute(selectCustomers);//execute statement
             ResultSet rsCustomers = statement.getResultSet();//get ResultsSet
             
             //forward scroll ResultSet
             while(rsCustomers.next()){

                 //Set variables from each row
                 int customerID = rsCustomers.getInt("Customer_ID");
                 String customerName = rsCustomers.getString("Customer_Name");
                 String address = rsCustomers.getString("Address");
                 String postalCode = rsCustomers.getString("Postal_Code");
                 String phone = rsCustomers.getString("Phone");
                 LocalDateTime date = rsCustomers.getTimestamp("Create_Date").toLocalDateTime();
                 String createdBy = rsCustomers.getString("Created_By");
                 LocalDateTime lastUpdate = rsCustomers.getTimestamp("Last_Update").toLocalDateTime();
                 int divisionID = rsCustomers.getInt("Division_ID");
                 for (State state : Lists.getAllStates()) {
                 if (state.getDivisionId() == divisionID){
                 State customerState = state;
                 for (Country country : Lists.getAllCountries()) {
                 if (country.getCountryId() == customerState.getCountryId()){
                 Country customerCountry = country;
                 Lists.addCustomer(new Customer(address, customerID, customerName, postalCode, phone, customerState, customerCountry));
                 };
                 }
                 };
                 }
             }
                 
         }catch (SQLException ex) {
             Logger.getLogger(Customers_FormController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    /**
     *get devisionID from first_level_divisions table
     * @param division division
     * @return get DivisionId from Division
     * @throws SQLException SQL error catch
     */
    public int getDivisionIdFromDivision(String division) throws SQLException{
    Connection conn = DBConnection.startConnection();
             //connect to database
             DBQuery.setStatement(conn);
             //create statement object
             Statement statement = DBQuery.getStatement();//get statement reference
             String selectDivision = "SELECT division_ID FROM first_level_divisions WHERE Division = '"+division+"'";//select statement
             statement .execute(selectDivision);//execute statement
             ResultSet rsDivision = statement.getResultSet();//get ResultsSet
             while(rsDivision.next()){

                 //Set variables from each row
                 int divisionId = rsDivision.getInt("Division_ID");
                 return divisionId;
             }
        return 0;
    }       

    /**
     *get info from contacts table
     * @throws SQLException SQL error catch
     */
    public void getAllContacts() throws SQLException{
    Connection conn = DBConnection.startConnection();
     DBQuery.setStatement(conn);
             Statement statement = DBQuery.getStatement();//get statement reference
             String selectContacts = "SELECT * FROM contacts";//select statement
             statement .execute(selectContacts);//execute statement
             ResultSet rsContacts = statement.getResultSet();
             
      while(rsContacts.next()){
      int contactID = rsContacts.getInt("Contact_ID");
      String contactName = rsContacts.getString("Contact_Name");
      String email = rsContacts.getString("Email");
      
      Lists.addContact(new Contact(contactID, contactName, email));
      }
          
    }

    /**
     *get info from appointments table
     * @throws SQLException SQL error catch
     */
    public void getAllAppointments() throws SQLException{
    TimeConvert timeConvert = new TimeConvert();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    Connection conn = DBConnection.startConnection();
     DBQuery.setStatement(conn);
             Statement statement = DBQuery.getStatement();//get statement reference
             String selectAppointments = "SELECT * FROM appointments";//select statement
             statement .execute(selectAppointments);//execute statement
             ResultSet rsAppointments= statement.getResultSet();
             
    while(rsAppointments.next()){
    int appointmentID = rsAppointments.getInt("Appointment_ID");
    String title = rsAppointments.getString("Title");
    String description = rsAppointments.getString("Description");
    String location = rsAppointments.getString("Location");
    String type = rsAppointments.getString("Type");
    String start = rsAppointments.getString("Start");
    LocalDateTime startLDT = LocalDateTime.parse(start, formatter);
    String startTime = timeConvert.convertLDTToLocalTimeString(startLDT);
    String end = rsAppointments.getString("End");
    LocalDateTime endLDT = LocalDateTime.parse(end, formatter);
    String endTime = timeConvert.convertLDTToLocalTimeString(endLDT);
    int customerID = rsAppointments.getInt("Customer_ID");
    int userID = rsAppointments.getInt("User_ID");
    int contactID = rsAppointments.getInt("Contact_ID");
    System.out.println(contactID);
    String contactName;
    for (Contact contact : Lists.getAllContacts()){
        if(contact.getContactID() == contactID){
        contactName = contact.getContactName();
        Lists.addAllAppointment(new Appointment(appointmentID, title, description, location, contactName, type, startTime, endTime, customerID, userID));
        }
        
    }
    
    
    
    }
             
    }

    /**
     *get current month info from appointments table
     * @throws SQLException SQL error catch
     */
    public void getMonthlyAppointments() throws SQLException{
    TimeConvert timeConvert = new TimeConvert();
    LocalDateTime currentDate = java.time.LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String currentDateFormatted = currentDate.format(formatter);
    LocalDateTime datePlusMonth = currentDate.plusMonths(1);
    String datePlusMonthFormatted = datePlusMonth.format(formatter);
    Connection conn = DBConnection.startConnection();
     DBQuery.setStatement(conn);
             Statement statement = DBQuery.getStatement();//get statement reference
             String selectAppointments = "SELECT * FROM appointments WHERE Start BETWEEN '"+currentDateFormatted+"' AND '"+datePlusMonthFormatted+"' ";//select statement
             statement .execute(selectAppointments);//execute statement
             ResultSet rsAppointments= statement.getResultSet();
             
    while(rsAppointments.next()){
    int appointmentID = rsAppointments.getInt("Appointment_ID");
    String title = rsAppointments.getString("Title");
    String description = rsAppointments.getString("Description");
    String location = rsAppointments.getString("Location");
    String type = rsAppointments.getString("Type");
    String start = rsAppointments.getString("Start");
    LocalDateTime startLDT = LocalDateTime.parse(start, formatter);
    String startTime = timeConvert.convertLDTToLocalTimeString(startLDT);
    String end = rsAppointments.getString("End");
    LocalDateTime endLDT = LocalDateTime.parse(end, formatter);
    String endTime = timeConvert.convertLDTToLocalTimeString(endLDT);
    int customerID = rsAppointments.getInt("Customer_ID");
    int userID = rsAppointments.getInt("User_ID");
    int contactID = rsAppointments.getInt("Contact_ID");
    String contactName;
    for (Contact contact : Lists.getAllContacts()){
        if(contact.getContactID() == contactID){
        contactName = contact.getContactName();
        Lists.addMonthlyAppointment(new Appointment(appointmentID, title, description, location, contactName, type, startTime, endTime, customerID, userID));
        }
        
    }
    
    
    
    }
             
    }
    
    /**
     *get current week info from appointments table
     * @throws SQLException SQL error catch
     */
    public void getWeeklyAppointments() throws SQLException{
    TimeConvert timeConvert = new TimeConvert();
    LocalDateTime currentDate = java.time.LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String currentDateFormatted = currentDate.format(formatter);
    System.out.println(currentDate);
    LocalDateTime datePlusWeek = currentDate.plusWeeks(1);
    String datePlusWeekFormatted = datePlusWeek.format(formatter);
    Connection conn = DBConnection.startConnection();
     DBQuery.setStatement(conn);
             Statement statement = DBQuery.getStatement();//get statement reference
             String selectAppointments = "SELECT * FROM appointments WHERE Start BETWEEN '"+currentDateFormatted+"' AND '"+datePlusWeekFormatted+"' ";//select statement
             statement .execute(selectAppointments);//execute statement
             ResultSet rsAppointments= statement.getResultSet();
             
    while(rsAppointments.next()){
    int appointmentID = rsAppointments.getInt("Appointment_ID");
    String title = rsAppointments.getString("Title");
    String description = rsAppointments.getString("Description");
    String location = rsAppointments.getString("Location");
    String type = rsAppointments.getString("Type");
    String start = rsAppointments.getString("Start");
    LocalDateTime startLDT = LocalDateTime.parse(start, formatter);
    String startTime = timeConvert.convertLDTToLocalTimeString(startLDT);
    String end = rsAppointments.getString("End");
    LocalDateTime endLDT = LocalDateTime.parse(end, formatter);
    String endTime = timeConvert.convertLDTToLocalTimeString(endLDT);
    int customerID = rsAppointments.getInt("Customer_ID");
    int userID = rsAppointments.getInt("User_ID");
    int contactID = rsAppointments.getInt("Contact_ID");
    String contactName;
    for (Contact contact : Lists.getAllContacts()){
        if(contact.getContactID() == contactID){
        contactName = contact.getContactName();
        Lists.addWeeklyAppointment(new Appointment(appointmentID, title, description, location, contactName, type, startTime, endTime, customerID, userID));
        }
        
    }
             
    
    }
     }
}
