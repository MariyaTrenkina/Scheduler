 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utills.DBConnection;
import utills.DBQuery;

/**
 *
 * @author Mariya.Trenkina
 */
public class C195 extends Application {

    /**
     *
     */
    public Locale userLocale;

    /**
     *
     */
    public ResourceBundle rb;
    Exceptions exception = new Exceptions();

    /**
     *
     */
    public static String loggedInUser;

    @Override
    public void start(Stage stage) throws Exception {
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("LoginFields", this.userLocale);
        Parent root = FXMLLoader.load(getClass().getResource("/view/Log_In_Form.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle(this.rb.getString("title"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * login method
     * @param loginUser get login User
     * @param loginPassword get login Password
     * 
     * @return login status
     * @throws SQLException SQL Error Catch
     */
    //log in method
    public boolean login(String loginUser, String loginPassword) throws SQLException{
        this.userLocale = Locale.getDefault();
        this.rb = ResourceBundle.getBundle("LoginFields", this.userLocale);
    String sql = "SELECT * FROM users WHERE User_Name = '" + loginUser + "' AND Password = '" + loginPassword + "'";
    //try to connect to database 
     try (Connection conn = DBConnection.startConnection()){
     DBQuery.setStatement(conn);
     Statement statement = DBQuery.getStatement();
     statement.execute(sql);
     ResultSet rs = statement.getResultSet();
     //check if user is database
     if(!rs.next()){
     
     //pop up message if username/password are incorrect 
     exception.popUp(rb.getString("errorTitle"), rb.getString("errorTextUsernamePassword"));
			return false;    
                  
     }
     else{     
     do{
     String login_Name = rs.getString("User_Name");
     String login_Password = rs.getString("Password");
     
     loggedInUser = login_Name;
     return true;
     
     }while(rs.next());}          
     }
     catch(Exception e){
     System.out.println("Failed: "+e.getMessage()); 
     }
        return false;
   }

    /**
     *main method
     * @param args main args
     * @throws SQLException SQL error catch
     */
    public static void main(String[] args) throws SQLException {
        DBQuery dbQuery = new DBQuery();
        dbQuery.pullAllCountries();
        dbQuery.pullAllStates();
 
          launch(args);
    
    }
         
        
        
        
      
    }
    

      