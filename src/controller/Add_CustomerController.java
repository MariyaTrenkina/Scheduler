/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.C195;
import classes.Country;
import classes.Exceptions;
import classes.Lists;
import classes.State;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utills.DBQuery;

/**
 * FXML Controller class
 *
 * @author Mariya.Trenkina
 */
public class Add_CustomerController implements Initializable {

     @FXML
    private Button saveBtn;

    @FXML
    private Label addCustomerLabel;

    @FXML
    private TextField customerNameTxtBox;

    @FXML
    private TextField addressTxtBox;

    @FXML
    private TextField phoneNumberTxtBox;

    @FXML
    private TextField postalCodeTxtBox;

    @FXML
    private TextField customerIdTxtBox;

    @FXML
    private ComboBox<State> stateProvidenceComboBox;

    @FXML
    private ComboBox<Country> countryComboBox;

    @FXML
    private Button cancelBtn;

    @FXML
    private Label customerIdLabel;

    @FXML
    private Label stateProvidenceLabel;

    @FXML
    private Label countryLabel;
    private Locale userLocale;
    private ResourceBundle rb;

    @FXML
    void onActionAddressTxtBox(ActionEvent event) {

    }

    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/Customers_Form.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show();  
    }

    @FXML
    void onActionCustomerIdTxtBox(ActionEvent event) {

    }

    @FXML
    void onActionCustomerNameTxtBox(ActionEvent event) {

    }

    @FXML
    void onActionPhoneNumberTxtBox(ActionEvent event) {

    }

    @FXML
    void onActionPostalCodeTxtBox(ActionEvent event) {

    }

    @FXML
    void onActionSaveBtn(ActionEvent event) throws SQLException, IOException {
    if (validateCustomer()){
    DBQuery dbQuery = new DBQuery();
    String customerName = customerNameTxtBox.getText();
    String address = addressTxtBox.getText();
    String phone = phoneNumberTxtBox.getText();
    String postalCode = postalCodeTxtBox.getText();
    int divisionId = dbQuery.getDivisionIdFromDivision(stateProvidenceComboBox.getValue().getDivision());
    dbQuery.addCustomerSQL(customerName, address, postalCode, phone, C195.loggedInUser, C195.loggedInUser, divisionId);
    //Back to Customers Form
    Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Object scene = FXMLLoader.load(getClass().getResource("/view/Customers_Form.fxml"));
        stage.setScene(new Scene((Parent) scene));
        stage.show(); 
    
    }
    }
    @FXML
    void onActionCountryComboBox(ActionEvent event) {
        
        
        Lists.clearAssociatedStates();
        
    for (State state : Lists.getAllStates()) {
    if (state.getCountryId() == countryComboBox.getValue().getCountryId()){
    Lists.addAssociatedState(state);
    };
    }
    stateProvidenceComboBox.setDisable(false);
    stateProvidenceComboBox.getSelectionModel().select(0);
    stateProvidenceComboBox.setItems(null);
    stateProvidenceComboBox.setItems(Lists.getAssociatedStates());
    }
    
    /**
     * Initializes the controller class.
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("ManageCustomerForm", this.userLocale);
        addCustomerLabel.setText(this.rb.getString("addCustomer"));
        customerIdLabel.setText(this.rb.getString("customerId"));
        customerNameTxtBox.setPromptText(this.rb.getString("name"));
        phoneNumberTxtBox.setPromptText(this.rb.getString("phone"));
        addressTxtBox.setPromptText(this.rb.getString("address"));
        stateProvidenceLabel.setText(this.rb.getString("stateLabel"));
        stateProvidenceComboBox.setPromptText(this.rb.getString("state"));
        countryLabel.setText(this.rb.getString("countryLabel"));
        countryComboBox.setPromptText(this.rb.getString("country"));
        postalCodeTxtBox.setPromptText(this.rb.getString("postalCode"));
        saveBtn.setText(this.rb.getString("save"));
        cancelBtn.setText(this.rb.getString("cancel"));
        
        countryComboBox.setItems(Lists.getAllCountries());
       
    }    

    /**
     *data validation method for customer input fields
     * @return input validation status
     */
    public boolean validateCustomer(){
        Exceptions exception = new Exceptions();
    //customer name
    try{
        String customerName = customerNameTxtBox.getText();
    if(customerName.trim().isEmpty()){
    exception.popUp(this.rb.getString("Customer_name_is_empty"),this.rb.getString ("Please _fill_in_customer_name"));
    return false;
    }
    if(customerName.trim().matches(".*\\d+.*")){
    exception.popUp(this.rb.getString("Customer_name_contains_numbers"),this.rb.getString ("Please_remove_numbers_from_customer_name"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
         
    //adress
     try{
        String customerAddress = addressTxtBox.getText();
    if(customerAddress.trim().isEmpty()){
    exception.popUp(this.rb.getString("Customer_address_is_empty"),this.rb.getString ("Please_fill_in_customer_address"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
         
    //postal code
    try{
        String postalCode = postalCodeTxtBox.getText();
    if(postalCode.trim().isEmpty()){
    exception.popUp(this.rb.getString("Postal_code_is_empty"),this.rb.getString ("Please_fill_in_postal_code"));
    return false;
    }
    if(postalCode.trim().matches("[a-zA-Z]+")){
    exception.popUp(this.rb.getString("Postal_code_contains_letters"),this.rb.getString ("Please_remove_letters_from_postal_code"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
    
    //phone number
    try{
        String phoneNumber = phoneNumberTxtBox.getText();
    if(phoneNumber.trim().isEmpty()){
    exception.popUp(this.rb.getString("Phone_number_is_empty"),this.rb.getString ("Please_fill_in_phone_number"));
    return false;
    }
    if(phoneNumber.trim().matches("[a-zA-Z]+")){
    exception.popUp(this.rb.getString("Phone_number_contains_letters"),this.rb.getString ("Please_remove_letters_from_phone_number"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Error"), e.getMessage());
    return false;
    }
    
    //country
    try{
        Country country = countryComboBox.getValue();
    if(country.toString().isEmpty()){
    exception.popUp(this.rb.getString("Country_is_empty"),this.rb.getString ("Please_choose_a_country"));
    return false;
    }
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("Country_is_empty"),this.rb.getString ("Please_choose_a_country"));
    return false;
    }
    
    //state
    try{
        State state = stateProvidenceComboBox.getValue();
    if(state.toString().isEmpty()){
    exception.popUp(this.rb.getString("State_is_empty"),this.rb.getString ("Please_choose_a_state"));
    return false;
    } 
    }
    catch(Exception e){
    exception.popUp(this.rb.getString("State_is_empty"),this.rb.getString ("Please_choose_a_state"));
    return false;
    }
    return true;
    }
    
    
    
    
    
}
