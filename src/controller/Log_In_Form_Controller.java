/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Appointment;
import classes.C195;
import classes.Exceptions;
import classes.Lists;
import classes.TimeConvert;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import utills.DBConnection;
import utills.DBQuery;

/**
 *
 * @author Mariya.Trenkina
 */
public class Log_In_Form_Controller implements Initializable {
    @FXML
    private Label timeZoneLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private Label timeZoneValueLabel;

    @FXML
    private Label languageValueLabel;

   @FXML
    private Label label;

    @FXML
    private TextField userIdTxtBox;

    @FXML
    private TextField passwordTxtBox;

    @FXML
    private Button logInBtn;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label passwordLabel;
    private Locale userLocale;
    private ResourceBundle rb;

    @FXML
    void onActionLogIn(ActionEvent event) throws SQLException, IOException {
        TimeConvert timeConvert = new TimeConvert();
        LocalDateTime currentDate = java.time.LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDateFormatted = currentDate.format(formatter);
        C195 main = new C195();
        Exceptions exception = new Exceptions();
        DBQuery dbQuery = new DBQuery();
        File logonActivity = new File("login_activity.txt");
        if (logonActivity.createNewFile()) {
        System.out.println("File created: " + logonActivity.getName());
      } else {
        System.out.println(logonActivity.getName()+" already exists.");
      }
    if (userIdTxtBox.getText() == null || userIdTxtBox.getText().isEmpty()) {
			exception.popUp(this.rb.getString("errorTitle"), this.rb.getString("errorTextUsernamePassword"));
			return;
		}else{
        if (main.login(userIdTxtBox.getText(), passwordTxtBox.getText())){
            try {
      FileWriter fw = new FileWriter(logonActivity,true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("User: "+C195.loggedInUser+" - Log on Success - Timestamp: "+currentDateFormatted+"\n");
      bw.close();
      
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();}
            dbQuery.getAllContacts();
                Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                Object scene = FXMLLoader.load(getClass().getResource("/view/Main_Form.fxml"));
                stage.setScene(new Scene((Parent) scene));
                stage.show();
                upcomingAppointment();
                 
                }
        else {
            try {
      FileWriter fw = new FileWriter(logonActivity,true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("User: "+userIdTxtBox.getText()+" - Log on Failed - Timestamp: "+currentDateFormatted+"\n");
      bw.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();}
            System.out.println("Log In Failed");
        }

        
    }

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //computer current language setting
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("LoginFields", this.userLocale);
        
        //username
        userIdLabel.setText(this.rb.getString("username"));
        //computer current time zone
        ZoneId locationID = ZoneId.systemDefault();
        //timezone
        timeZoneLabel.setText(this.rb.getString("timezone"));
        timeZoneValueLabel.setText(locationID.toString());
        //language
        languageLabel.setText(this.rb.getString("language"));
        languageValueLabel.setText(Locale.getDefault().toString());
        //password
        passwordLabel.setText(this.rb.getString("password"));
        //login btn
        logInBtn.setText(this.rb.getString("logIn"));
        
    }    
    
    /**
     *retrieve upcoming appointment or state no appointment within 15 minutes
     * @throws SQLException SQL error catch
     */
    public void upcomingAppointment() throws SQLException{   
    DBQuery dbQuery = new DBQuery();
    dbQuery.getMonthlyAppointments();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String logOnTime = java.time.LocalDateTime.now().format(formatter);
    LocalDateTime logOnTimeLDT = LocalDateTime.parse(logOnTime, formatter);
    LocalDateTime logOnTimePlusFifteenLDT = logOnTimeLDT.plusMinutes(15);
    boolean upcomingAppointments = false;
    for(Appointment appointment : Lists.getMonthlyAppointments() ){
        
    LocalDateTime appointmentStartTime = LocalDateTime.parse(appointment.getStartDateTime(), formatter);
    
    if(appointmentStartTime.isBefore(logOnTimePlusFifteenLDT) && appointmentStartTime.isAfter(logOnTimeLDT)){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle(this.rb.getString("ALERT"));
           alert.setHeaderText(this.rb.getString("UPCOMING_APPOINTMENT_WITHIN_15_MINUTES"));
           alert.setContentText(this.rb.getString("APPOINTMENT_WITH_ID") +appointment.getAppointmentID()+ this.rb.getString("STARTS_WITHIN_15_MINUTES_AT") +appointment.getStartDateTime());
           alert.showAndWait();
           
           upcomingAppointments = true;
    }
    
    }
    if(!upcomingAppointments){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle(this.rb.getString("ALERT"));
           alert.setHeaderText(this.rb.getString("NO_UPCOMIN_APPOINTMENT_WITHIN_15_MINUTES"));
           alert.showAndWait();
           
    }
    }
}
