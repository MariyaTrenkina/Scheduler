/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mariya.Trenkina
 */
public class Main_FormController implements Initializable {

    
    @FXML
    private Button customersBtn;

    @FXML
    private Button appointmentBtn;

    @FXML
    private Button reportsBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Label scheldulingAppLabel;
    private Locale userLocale;
    private ResourceBundle rb;

    @FXML
    void onActionAppointmentBtn(ActionEvent event) throws IOException {
     Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
    Object scene = FXMLLoader.load(getClass().getResource("/view/Appointments_Form.fxml"));
    stage.setScene(new Scene((Parent) scene));
    stage.show();
    }
    //takes from main form to customer form
    @FXML
    void onActionCustomersBtn(ActionEvent event) throws IOException {
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
    Object scene = FXMLLoader.load(getClass().getResource("/view/Customers_Form.fxml"));
    stage.setScene(new Scene((Parent) scene));
    stage.show();
    }
    //exit button
    @FXML
    void onActionExitBtn(ActionEvent event) {
     System.exit(0);
    }

    @FXML
    void onActionrRportsBtn(ActionEvent event) throws IOException {
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
    Object scene = FXMLLoader.load(getClass().getResource("/view/Report_Form.fxml"));
    stage.setScene(new Scene((Parent) scene));
    stage.show();
    }

    /**
     * Initializes the controller class.
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("MainForm", this.userLocale);
        scheldulingAppLabel.setText(this.rb.getString("schedulingAppLabel"));
        customersBtn.setText(this.rb.getString("customers"));
        appointmentBtn.setText(this.rb.getString("appointments"));
        reportsBtn.setText(this.rb.getString("reports"));
        exitBtn.setText(this.rb.getString("exit"));       
                
    }    
    
}
