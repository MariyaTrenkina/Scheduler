/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Appointment;
import classes.Customer;
import classes.Exceptions;
import classes.Lists;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utills.DBQuery;

/**
 * FXML Controller class
 * <p>Rubric D: Lambda #2 utilized for custom popUp message with (title, header, and body) arguments in parameters (x,y,z).Use of the lambda was needed for flexible popup template.</p>
 * @author Mariya.Trenkina
 */
public class Appointments_FormController implements Initializable {
    @FXML
    private TableView<Appointment> appointmentTableView;
      @FXML
    private TableColumn<Appointment, Integer> appointmentIdColumn;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Appointment, String> locationColumn;

    @FXML
    private TableColumn<Appointment, String> contactColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, String> startDateTimeColumn;

    @FXML
    private TableColumn<Appointment, String> endDateTimeColumn;

    @FXML
    private TableColumn<Appointment, Integer> customerIdColumn;

    @FXML
    private Label appointmentsLabel;

    @FXML
    private RadioButton weeklyViewRadioBtn;

    @FXML
    private RadioButton monthlyViewRadioBtn;
    
    @FXML
    private RadioButton allViewRadioBtn;
    @FXML
    private Button backBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button modifyBtn;
    private Locale userLocale;
    private ResourceBundle rb;

    @FXML
    void onActionAddBtn(ActionEvent event) throws IOException {
Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
    Object scene = FXMLLoader.load(getClass().getResource("/view/Add_Appointment.fxml"));
    stage.setScene(new Scene((Parent) scene));
    stage.show();
    }

    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/Main_Form.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();
    }

    @FXML
    void onActionDeleteBtn(ActionEvent event) throws SQLException {
    DBQuery dbQuery = new DBQuery();
    Exceptions exception = new Exceptions();
    if(appointmentTableView.getSelectionModel().getSelectedItem() != null){
        int appointmentId = appointmentTableView.getSelectionModel().getSelectedItem().getAppointmentID();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, this.rb.getString("Are_you_sure_you_want_to_cancel_appointment_with_ID") + appointmentTableView.getSelectionModel().getSelectedItem().getAppointmentID()+ this.rb.getString ("and_End")+appointmentTableView.getSelectionModel().getSelectedItem().getType()+"?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(this.rb.getString("Cancel_Confirmation"));
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
        dbQuery.deleteAppointmentSQL(appointmentId);
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION, this.rb.getString("Canceled_appointment_with_ID") + appointmentTableView.getSelectionModel().getSelectedItem().getAppointmentID()+ this.rb.getString("and_type")+appointmentTableView.getSelectionModel().getSelectedItem().getType()+"!");
        alert2.setHeaderText(this.rb.getString("Appointment_Canceled"));
        alert2.showAndWait();
        appointmentTableView.getItems().removeAll(appointmentTableView.getSelectionModel().getSelectedItem());
       }
        } else {
        //Rubric D: Lambda #2 utilized for custom popUp message with (title, header, and body) arguments in parameters (x,y,z). Use of the lambda was needed for flexible popup template.
        Exceptions.customPopUp popUp = (x,y,z) -> {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle(x);
           alert.setHeaderText(y);
           alert.setContentText(z);
           alert.showAndWait();
                   };
         popUp.show(this.rb.getString("Error"), this.rb.getString("An_appointment_from_the_table_is_not_selected"), this.rb.getString("Please_select_appointment_from_the_table"));
                   }
       
    
    }

    @FXML
    void onActionModifyBtn(ActionEvent event) throws IOException {
Appointment selectedAppointment = appointmentTableView.getSelectionModel().getSelectedItem();
    if (selectedAppointment != null){ 
    FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Modify_Appointment.fxml"));
        loader.load();
        Modify_AppointmentController modifyAppointmentController = loader.getController();
        modifyAppointmentController.importAppointmentInfo(selectedAppointment);
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle(this.rb.getString("Error_Message"));
           alert.setHeaderText(this.rb.getString("Appointment_not_selected"));
           alert.setContentText(this.rb.getString("Please_select_an_appointment_from_the_table"));
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
    }});}
    }
    @FXML
    void onActionAllViewRadioBtn(ActionEvent event) {
    DBQuery dbQuery = new DBQuery();
          
          Lists.clearMonthlyAppointments();
          Lists.clearWeeklyAppointments();
          Lists.clearAllAppointments();
          try {
              dbQuery.getAllAppointments();
          } catch (SQLException ex) {
              Logger.getLogger(Appointments_FormController.class.getName()).log(Level.SEVERE, null, ex);
          }
    
         //Add data to Tableview
        appointmentTableView.setItems(Lists.getAllAppointments());
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
    @FXML
    void onActionMontlyViewRadioBtn(ActionEvent event) {
    DBQuery dbQuery = new DBQuery();
          
          Lists.clearMonthlyAppointments();
          Lists.clearWeeklyAppointments();
          Lists.clearAllAppointments();
          try {
              dbQuery.getMonthlyAppointments();
          } catch (SQLException ex) {
              Logger.getLogger(Appointments_FormController.class.getName()).log(Level.SEVERE, null, ex);
          }
    
         //Add data to Tableview
        appointmentTableView.setItems(Lists.getMonthlyAppointments());
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }

    @FXML
    void onActionWeeklyViewRadioBtn(ActionEvent event) {
    DBQuery dbQuery = new DBQuery();
          
          Lists.clearMonthlyAppointments();
          Lists.clearWeeklyAppointments();
          Lists.clearAllAppointments();
          try {
              dbQuery.getWeeklyAppointments();
          } catch (SQLException ex) {
              Logger.getLogger(Appointments_FormController.class.getName()).log(Level.SEVERE, null, ex);
          }
    
         //Add data to Tableview
        appointmentTableView.setItems(Lists.getWeeklyAppointments());
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("AppointmentsForm", this.userLocale);
        
        System.out.println(Locale.getDefault());
        appointmentsLabel.setText(this.rb.getString("appointments"));
        appointmentIdColumn.setText(this.rb.getString("appointmentId"));
        titleColumn.setText(this.rb.getString("title"));
        descriptionColumn.setText(this.rb.getString("description"));
        locationColumn.setText(this.rb.getString("location"));
        contactColumn.setText(this.rb.getString("contact"));
        typeColumn.setText(this.rb.getString("type"));
        startDateTimeColumn.setText(this.rb.getString("startDateTime"));
        endDateTimeColumn.setText(this.rb.getString("endDateTime"));
        customerIdColumn.setText(this.rb.getString("customerId"));
        allViewRadioBtn.setText(this.rb.getString("allView"));
        monthlyViewRadioBtn.setText(this.rb.getString("monthlyView"));
        weeklyViewRadioBtn.setText(this.rb.getString("weeklyView"));
        modifyBtn.setText(this.rb.getString("modify"));
        deleteBtn.setText(this.rb.getString("delete"));
        backBtn.setText(this.rb.getString("back"));
        addBtn.setText(this.rb.getString("add"));
        
        
        DBQuery dbQuery = new DBQuery();
          
          Lists.clearMonthlyAppointments();
          Lists.clearWeeklyAppointments();
          Lists.clearAllAppointments();
          try {
              dbQuery.getMonthlyAppointments();
              dbQuery.getWeeklyAppointments();
              dbQuery.getAllAppointments();
          } catch (SQLException ex) {
              Logger.getLogger(Appointments_FormController.class.getName()).log(Level.SEVERE, null, ex);
          }
    
         //Add data to Tableview
        appointmentTableView.setItems(Lists.getAllAppointments());
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }    
    
    
}
