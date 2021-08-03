/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Appointment;
import classes.Contact;
import classes.Exceptions;
import classes.Lists;
import classes.TimeConvert;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utills.DBQuery;

/**
 * FXML Controller class
 *
 * @author Mariya.Trenkina
 */
public class Add_AppointmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    

     @FXML
    private Label addAppointmentLabel;

    @FXML
    private TextField titleTxtBox;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField descriptionTxtBox;

    @FXML
    private TextField locationTxtBox;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label contactLabel;

    @FXML
    private TextField typeTxtBox;

    @FXML
    private Label appointmentIdLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label customerIdLabel;

    @FXML
    private Label userLabel;

    @FXML
    private Label startDateTimeLabel;

    @FXML
    private Label endDateTimeLabel;

    @FXML
    private TextField customerIdTxtBox;

    @FXML
    private TextField userIdTxtBox;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private DatePicker startDateTimeDatePicker;

    @FXML
    private DatePicker endDateTimeDatePicker;

    @FXML
    private ComboBox<String> startDateTimeHourComboBox;

    @FXML
    private ComboBox<String> startDateTimeMinComboBox;

    @FXML
    private ComboBox<String> endDateTimeHourComboBox;

    @FXML
    private ComboBox<String> endDateTimeMinComboBox;

    @FXML
    private TextField appointmentIdTxtBox;
    
     @FXML
    private Button cancelBtn;

    @FXML
    private Button saveBtn;
    private Locale userLocale;
    private ResourceBundle rb;
    

    @FXML
    void onActionAppointmentId(ActionEvent event) {

    }

    @FXML
    void onActionContact(ActionEvent event) {

    }

    @FXML
    void onActionCustomerId(ActionEvent event) {

    }

    @FXML
    void onActionDescription(ActionEvent event) {

    }

    @FXML
    void onActionEndDateTime(ActionEvent event) {

    }

    @FXML
    void onActionEndDateTimeHour(ActionEvent event) {

    }

    @FXML
    void onActionEndDateTimeMin(ActionEvent event) {

    }

    @FXML
    void onActionLocation(ActionEvent event) {

    }

    @FXML
    void onActionStartDateTime(ActionEvent event) {

    }

    @FXML
    void onActionStartDateTimeHour(ActionEvent event) {

    }

    @FXML
    void onActionStartDateTimeMin(ActionEvent event) {

    }

    @FXML
    void onActionTitle(ActionEvent event) {

    }

    @FXML
    void onActionType(ActionEvent event) {

    }

    @FXML
    void onActionUserId(ActionEvent event) {

    }
    
   
    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/Appointments_Form.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();  
    }
    
    @FXML
    void onActionSaveBtn(ActionEvent event) {
        
        try{
       
       TimeConvert timeConvert = new TimeConvert();
       String startDateString = startDateTimeDatePicker.getValue().toString()+" "+startDateTimeHourComboBox.getValue()+":"+startDateTimeMinComboBox.getValue()+":00";
       String endDateString = endDateTimeDatePicker.getValue().toString()+" "+endDateTimeHourComboBox.getValue()+":"+endDateTimeMinComboBox.getValue()+":00";
             
       //Start Date Conversions
       String startESTDateTimeString =  timeConvert.convertToESTString(startDateString);
       String startUTCDateTimeString =  timeConvert.convertToUTCString(startDateString);
       LocalDateTime startESTDateTimeLocalDateTime = timeConvert.convertToESTLocalDateTime(startDateString);
       //End Date Conversions
       String endESTDateTimeString =  timeConvert.convertToESTString(endDateString);
       String endUTCDateTimeString =  timeConvert.convertToUTCString(endDateString);
       LocalDateTime endESTDateTimeLocalDateTime = timeConvert.convertToESTLocalDateTime(endDateString);
        
       //
       if(withinBusinessHours(startESTDateTimeLocalDateTime,endESTDateTimeLocalDateTime) && validateAppointment() && appointmentOverlapping(startESTDateTimeLocalDateTime,endESTDateTimeLocalDateTime)==false){      
       DBQuery dbQuery = new DBQuery();
       String title = titleTxtBox.getText();
       String description = descriptionTxtBox.getText();
       String location = locationTxtBox.getText();
       String type = typeTxtBox.getText();
       int contactID = contactComboBox.getValue().getContactID();
       int customerID = Integer.parseInt(customerIdTxtBox.getText());
       int userID = Integer.parseInt(userIdTxtBox.getText());
       dbQuery.addAppointmentSQL(title, description, location, type, startUTCDateTimeString, endUTCDateTimeString, customerID, userID, contactID);
       //Back to Customers Form
        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/Appointments_Form.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
       }else{
       //Stop
       }
      }catch(Exception e){
          Exceptions exception = new Exceptions();
          System.out.println(e.getMessage());
          exception.popUp(this.rb.getString("Error"), e.getMessage());}  
        
    }
        @Override
        public void initialize(URL url, ResourceBundle rb) {
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("ManageAppointmentsForm", this.userLocale);
        
        System.out.println(Locale.getDefault());
        addAppointmentLabel.setText(this.rb.getString("addAppointment"));
        appointmentIdLabel.setText(this.rb.getString("appointmentId"));
        titleLabel.setText(this.rb.getString("title"));
        descriptionLabel.setText(this.rb.getString("description"));
        locationLabel.setText(this.rb.getString("location"));
        contactLabel.setText(this.rb.getString("contact"));
        typeLabel.setText(this.rb.getString("type"));
        startDateTimeLabel.setText(this.rb.getString("startDateTime"));
        endDateTimeLabel.setText(this.rb.getString("endDateTime"));
        customerIdLabel.setText(this.rb.getString("customerId"));
        userLabel.setText(this.rb.getString("userId"));
        saveBtn.setText(this.rb.getString("save"));
        cancelBtn.setText(this.rb.getString("cancel"));
               //
        contactComboBox.setItems(Lists.getAllContacts());
    //Start DateTime Values:      
      startDateTimeHourComboBox.getItems().addAll(
      "00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"
      );
      startDateTimeMinComboBox.getItems().addAll(
      "00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
      "21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40",
      "41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"
      );
      //End DateTime Values:      
      endDateTimeHourComboBox.getItems().addAll(
      "00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"
      );
      endDateTimeMinComboBox.getItems().addAll(
      "00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
      "21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40",
      "41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"
      );
      
      
    }    

    /**
     *method to return boolean whether appointments are within business hours
     * @param startDate appointment startDate
     * @param endDate appointment endDate
     * @return within Business Hours status
     */
    public boolean withinBusinessHours(LocalDateTime startDate, LocalDateTime endDate){
      Exceptions exception = new Exceptions();
      LocalTime openingTime = LocalTime.of(8, 0);
      LocalTime closingTime = LocalTime.of(22, 0);
      LocalTime startTime = startDate.toLocalTime();
      LocalTime endTime = endDate.toLocalTime();
      if (startDate.isBefore(endDate)){
      if(startTime.isAfter(openingTime) || startTime.equals(openingTime)){
      if(startTime.isBefore(closingTime) && endTime.isAfter(openingTime)){
      if(endTime.equals(closingTime) || endTime.isBefore(closingTime)){
          return true;
      }else{exception.popUp(this.rb.getString("Error"), this.rb.getString("End_time_is_not_within_business_hours"));}
      }else{exception.popUp(this.rb.getString("Error"), this.rb.getString("Start_and_end_times_are_not_within_business_hours"));}
      }else{exception.popUp(this.rb.getString("Error"), this.rb.getString("Start_time_is_not_within_business_hours"));}
      }else{exception.popUp(this.rb.getString("Error"), this.rb.getString("Start_date_is_not_before_end_date"));}
         return false;
      }
      
    /**
     * data validation method for appointment input fields
     * @return data validation status
     */
    public boolean validateAppointment(){
        Exceptions exception = new Exceptions();
      //title
       try{
        String title = titleTxtBox.getText();
    if(title.trim().isEmpty()){
    exception.popUp(this.rb.getString("Title_text_box_is_empty"), this.rb.getString("Please_fill_in_title_text_box"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
    //description      
     try{
        String description = descriptionTxtBox.getText();
    if(description.trim().isEmpty()){
    exception.popUp(this.rb.getString("Description_text_box_is_empty"), this.rb.getString("Please_fill_in_description_text_box"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
     
    //location
     try{
        String location = locationTxtBox.getText();
    if(location.trim().isEmpty()){
    exception.popUp(this.rb.getString("Location_text_box_is_empty"), this.rb.getString("Please_fill_in_location_text_box"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
    
    //contact
    try{
        Contact contact = contactComboBox.getValue();
    if(contact.toString().isEmpty()){
    exception.popUp(this.rb.getString("Contact_combo_box_is_not_selected"), this.rb.getString("Please_select_a_contact"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Contact_combo_box_is_not_selected"), this.rb.getString("Please_select_a_contact"));
    return false;
    } 
     
    //type
    try{
        String type = typeTxtBox.getText();
    if(type.trim().isEmpty()){
    exception.popUp(this.rb.getString("Type_text_box_is_empty"), this.rb.getString("Please_fill_in_type_text_box"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
    
    //customer id
     try{
        String customerID = customerIdTxtBox.getText();
    if(customerID.trim().isEmpty()){
    exception.popUp(this.rb.getString("CustomerID_text_box_is_empty"),this.rb.getString ("Please_fill_in_customerID_text_box"));
    return false;
    }
    if(customerID.trim().matches("[a-zA-Z]+")){
    exception.popUp(this.rb.getString("CustomerID_text_box_contains_letters"),this.rb.getString ("Please_remove_letters_from_customerID_text_box "));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
     
    //user id
      try{
        String userID = userIdTxtBox.getText();
    if(userID.trim().isEmpty()){
    exception.popUp(this.rb.getString("UserID_text_box_is_empty"),this.rb.getString ("Please_fill_in_userID_text_box"));
    return false;
    }
    if(userID.trim().matches("[a-zA-Z]+")){
    exception.popUp(this.rb.getString("UserID_text_box_contains_letters"), this.rb.getString("Please_remove_letters_from_userID_text_box"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
     
          return true;
      }      

    /**
     *method to return boolean whether appointments overlap or not
     * @param startDate startDate
     * @param endDate endDate
     * @return appointment overlapping status
     */
    public boolean appointmentOverlapping(LocalDateTime startDate, LocalDateTime endDate){
         Exceptions exception = new Exceptions();
         TimeConvert timeConvert = new TimeConvert();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         
        for (Appointment appointment : Lists.getMonthlyAppointments()) {
            
            LocalDateTime compareAppointmentStartDate = LocalDateTime.parse(appointment.getStartDateTime(), formatter);
            LocalDateTime compareLDTStart = timeConvert.convertToLDTtoESTLocalDateTime(compareAppointmentStartDate);
            LocalDateTime compareAppointmentEndDate = LocalDateTime.parse(appointment.getEndDateTime(), formatter);
            LocalDateTime compareLDTEnd = timeConvert.convertToLDTtoESTLocalDateTime(compareAppointmentEndDate);
            if((startDate.isBefore(compareLDTEnd)) && (endDate.isAfter(compareLDTStart))){
            // 
            exception.popUp(this.rb.getString("Overlapping_Appointment"),this.rb.getString ("Overlapping_appointment_with_Start ")+ appointment.getStartDateTime() +this.rb.getString(" and End: ") + appointment.getEndDateTime());
            return true;
            }
        }
            
            
            return false;
    


    }   
      }
      
    

