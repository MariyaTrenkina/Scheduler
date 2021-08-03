/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Appointment;
import classes.Contact;
import classes.Lists;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mariya.Trenkina
 */
public class Report_Appointments_By_ContactController implements Initializable {
    @FXML
    private TableView<Appointment> appointmentTableView;

    @FXML
    private TableColumn<Appointment, Integer> aptIDColumn;

    @FXML
    private TableColumn<Appointment, String> titleColumn;

    @FXML
    private TableColumn<Appointment, String> typeColumn;

    @FXML
    private TableColumn<Appointment, String> descriptionColumn;

    @FXML
    private TableColumn<Appointment, String> startDateColumn;

    @FXML
    private TableColumn<Appointment, String> endDateColumn;

    @FXML
    private TableColumn<Appointment, Integer> customerIDColumn;

    @FXML
    private Label titleLabel;

    @FXML
    private Label contactsLabel;

    @FXML
    private ComboBox<Contact> contactsComboBox;
    
    @FXML
    private Button backBtn;
    private Locale userLocale;
    private ResourceBundle rb;

    @FXML
    void onActionBackBtn(ActionEvent event) throws IOException {
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
    Object scene = FXMLLoader.load(getClass().getResource("/view/Report_Form.fxml"));
    stage.setScene(new Scene((Parent) scene));
    stage.show();
    }
    @FXML
    void onActionContactsComboBox(ActionEvent event) {
    ObservableList<Appointment> filteredAppointments = FXCollections.observableArrayList();
    for(Appointment appt : Lists.getAllAppointments()){
    if(appt.getContact() == contactsComboBox.getValue().toString()){
    filteredAppointments.add(appt);
    }
    }
    appointmentTableView.setItems(filteredAppointments);
        aptIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
    }
    /**
     * Initializes the controller class.
     * @param url URL 
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("ReportAppByContact", this.userLocale);
        
        System.out.println(Locale.getDefault());
        titleLabel.setText(this.rb.getString("appointmentsByContact"));
        contactsLabel.setText(this.rb.getString("contact"));
        aptIDColumn.setText(this.rb.getString("appointmentId"));
        titleColumn.setText(this.rb.getString("title"));
        descriptionColumn.setText(this.rb.getString("description"));
        typeColumn.setText(this.rb.getString("type"));
        startDateColumn.setText(this.rb.getString("startDateTime"));
        endDateColumn.setText(this.rb.getString("endDateTime"));
        customerIDColumn.setText(this.rb.getString("customerId"));
        backBtn.setText(this.rb.getString("back"));
        contactsComboBox.setItems(Lists.getAllContacts());
        
    }    
    
}
