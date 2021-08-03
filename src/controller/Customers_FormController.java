/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Customer;
import classes.Exceptions;
import classes.Lists;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utills.DBConnection;
import utills.DBQuery;

/**
 * FXML Controller class
 * <p>Rubric D: Lambda #1 utilized for custom popUp message with (title, header, and body) arguments in parameters (x,y,z).Use of the lambda was needed for flexible popup template.</p>
 * @author Mariya.Trenkina
 */
public class Customers_FormController implements Initializable {
    Stage stage;
    Parent scene;
    
     @FXML
    private Label customersLabel;
     
     @FXML
    private TableView<Customer> customersTableView;
     
    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Customer, String> postalCodeColumn;

    @FXML
    private TableColumn<Customer, String> phoneNumberColumn;
    
    @FXML
    private TableColumn<Customer, String> stateColumn;

    @FXML
    private TableColumn<Customer, String> countryColumn;
    
    @FXML
    private Button addBtn;

    @FXML
    private Button modifyBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button backBtn;
    private Locale userLocale;
    private ResourceBundle rb;

    @FXML
    void onActionAddBtn(ActionEvent event) throws IOException {
    
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
    Object scene = FXMLLoader.load(getClass().getResource("/view/Add_Customer.fxml"));
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
    
    if(customersTableView.getSelectionModel().getSelectedItem() != null){
    int customerId = customersTableView.getSelectionModel().getSelectedItem().getCustomerId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,this.rb.getString ("Delete ") + customersTableView.getSelectionModel().getSelectedItem().getCustomerName()+ " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            try{
        dbQuery.deleteCustomerSQL(customerId);
        customersTableView.getItems().removeAll(customersTableView.getSelectionModel().getSelectedItem());
        }catch(Exception e){
          Exceptions exception = new Exceptions();
          System.out.println(e.getMessage());
          exception.popUp(this.rb.getString("Error"), e.getMessage());} 
       }
        } else {
        //Rubric D: Lambda #1 utilized for custom popUp message with (title, header, and body) arguments in parameters (x,y,z).Use of the lambda was needed for flexible popup template.
        Exceptions.customPopUp popUp = (x,y,z) -> {
        Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle(x);
           alert.setHeaderText(y);
           alert.setContentText(z);
           alert.showAndWait();
        };
          popUp.show(this.rb.getString("Error"),this.rb.getString ("A_customer_from_the_table_is_not_selected"),this.rb.getString("Please_select_a_customer_from_the_tabl"));
                   } 
    
    }

    @FXML
    void onActionModifyBtn(ActionEvent event) throws IOException {
         Customer selectedCustomer = customersTableView.getSelectionModel().getSelectedItem();
    if (selectedCustomer != null){ 
    FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/Modify_Customer.fxml"));
        loader.load();
        Modify_CustomerController modifyCustomerController = loader.getController();
        modifyCustomerController.importCustomerInfo(selectedCustomer);
    stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle(this.rb.getString("Error_Message"));
           alert.setHeaderText(this.rb.getString("Customer_not_selected"));
           alert.setContentText(this.rb.getString("Please_select_a_customer_from_the_table"));
           alert.showAndWait().ifPresent(rs -> {
                 if (rs == ButtonType.OK) {
                       System.out.println(this.rb.getString("Pressed_OK"));
    }});}
    }
    
    
    /**
     * Initializes the controller class.
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //computer current language setting
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("CustomersForm", this.userLocale);
     
        //customers
        customersLabel.setText(this.rb.getString("customers"));
        nameColumn.setText(this.rb.getString("name"));
        phoneNumberColumn.setText(this.rb.getString("phone"));
        addressColumn.setText(this.rb.getString("address"));
        stateColumn.setText(this.rb.getString("state"));
        countryColumn.setText(this.rb.getString("country"));
        postalCodeColumn.setText(this.rb.getString("postalCode"));
        addBtn.setText(this.rb.getString("add"));
        modifyBtn.setText(this.rb.getString("modify"));
        deleteBtn.setText(this.rb.getString("delete"));
        backBtn.setText(this.rb.getString("back"));
        //manage customers
        
        
        //
        DBQuery dbQuery = new DBQuery();
        Lists.clearCustomers();
        dbQuery.getAllCustomers();
    
         //Add data to Tableview
         customersTableView.setItems(Lists.getAllCustomers());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        
         
}
}
