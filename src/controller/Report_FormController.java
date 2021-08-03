/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Appointment;
import classes.Lists;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utills.DBQuery;

/**
 * FXML Controller class
 *
 * @author Mariya.Trenkina
 */
public class Report_FormController implements Initializable {

    
    @FXML
    private Button appByTypeMonthBtn;

    @FXML
    private Button appByContactBtn;

    @FXML
    private Button appForNextSevenDaysBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label reportsLabel;
    private Locale userLocale;
    private ResourceBundle rb;

    @FXML
    void onActionAppByContactBtn(ActionEvent event) throws IOException {
  Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
    Object scene = FXMLLoader.load(getClass().getResource("/view/Report_Appointments_By_Contact.fxml"));
    stage.setScene(new Scene((Parent) scene));
    stage.show();
    }

    @FXML
    void onActionAppByTypeMonthBtn(ActionEvent event) {
        ArrayList<String> dateList = new ArrayList<String>();
        
        String apptType = null;
        String apptStartDate = null;
        String month = null;
        String monthName = null;
        String year = null;
        String date = null;
        String appointmentDetails = null;
       
    for(Appointment appointment : Lists.getAllAppointments()){
        //Type
        apptType = appointment.getType();
        //Start Date
        apptStartDate = appointment.getStartDateTime();
        month = apptStartDate.substring(5, 7);
        monthName = null;
        year = apptStartDate.substring(0, 4);
 if("01".equals(month)){monthName = this.rb.getString("January");};
 if("02".equals(month)){monthName = this.rb.getString("February");};
 if("03".equals(month)){monthName = this.rb.getString("March");};
 if("04".equals(month)){monthName = this.rb.getString("April");};
 if("05".equals(month)){monthName = this.rb.getString("May");};
 if("06".equals(month)){monthName = this.rb.getString("June");};
 if("07".equals(month)){monthName = this.rb.getString("July");};
 if("08".equals(month)){monthName = this.rb.getString("August");};
 if("09".equals(month)){monthName = this.rb.getString("September");};
 if("10".equals(month)){monthName = this.rb.getString("October");};
 if("11".equals(month)){monthName = this.rb.getString("November");};
 if("12".equals(month)){monthName = this.rb.getString("December");};
       date = monthName+year;
       appointmentDetails = monthName+year+"-"+apptType;
       dateList.add(appointmentDetails);
       
        
        
        
    }
     String popUpMessage = "";
     ArrayList<String> usedDates = new ArrayList<String>();
     ArrayList<String> usedTypes = new ArrayList<String>();
     for (String appt : dateList){
     String msg = "empty";
     int seperator = appt.indexOf("-");
       String getDate = appt.substring(0,seperator);
       
       if(!usedDates.contains(getDate)){
           //Date
           popUpMessage = popUpMessage + "\n"+getDate+":"+"\n";
 
       for (String appt2 : dateList){
       
       String getType = appt2.substring(seperator+1);
       int count = 0;
        
       
       if(appt.contains(getDate) == appt2.contains(getDate)){
           //System.out.println("2:"+getType);
           
           for (String appt3 : dateList){
           
           if(appt3.contains(getType) == appt2.contains(getType) ){
               
               count++;
               
               
           }
           
           }
           
           if(!msg.contains(getType+ " = "+count)){
               msg=getType+ " = "+count;
               //Type and count
           popUpMessage = popUpMessage +"\t" +msg+"\n";
           
           }
           
           
             
           
           
           
           
       }
       
       }
       
       usedDates.add(getDate);
       
       }
       //
       
     }
     Dialog<String> dialog = new Dialog<String>();
         ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
           dialog.setTitle(this.rb.getString("num_of_appointments_by_type_and_month"));
           dialog.getDialogPane().getButtonTypes().add(type);
           dialog.setContentText(popUpMessage);
           dialog.showAndWait();
    }

    @FXML
    void onActionAppForNextSevenDaysBtn(ActionEvent event) throws SQLException {   
    DBQuery dbQuery = new DBQuery();
    dbQuery.getWeeklyAppointments();
    boolean upcomingWeekAppointments = false;
    int numberOfApp = 0;
    for(Appointment appointment : Lists.getWeeklyAppointments()){
    numberOfApp++;
    }
    if(numberOfApp > 0){
         Dialog<String> dialog = new Dialog<String>();
         ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
           dialog.setTitle(this.rb.getString("num_of_Appointments_for_Next_seven_Days"));
           dialog.getDialogPane().getButtonTypes().add(type);
           dialog.setContentText(this.rb.getString("total_number_of_appointments_for_the_next_seven_days") + numberOfApp);
           dialog.showAndWait();
           
    }
    else{
    Dialog<String> dialog = new Dialog<String>();
    ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
           dialog.setTitle(this.rb.getString("##_of_Appointments_for_Next_seven_Days"));
           dialog.getDialogPane().getButtonTypes().add(type);
           dialog.setContentText(this.rb.getString("there_are_no_appointments_for_the_next_seven_days"));
           dialog.showAndWait();
    }
    }
    

    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/Main_Form.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("Reports", this.userLocale);
        
        System.out.println(Locale.getDefault());
        reportsLabel.setText(this.rb.getString("reports"));
        appByTypeMonthBtn.setText(this.rb.getString("numberOfAppByTypeAndMonth"));
        appByContactBtn.setText(this.rb.getString("appByContact"));
        appForNextSevenDaysBtn.setText(this.rb.getString("numberOfAppForNextSevenDays"));
        backBtn.setText(this.rb.getString("back"));
        
        
        Lists.clearAllAppointments();
        DBQuery dbQuery = new DBQuery();
        try {
            dbQuery.getAllAppointments();
        } catch (SQLException ex) {
            Logger.getLogger(Report_FormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
