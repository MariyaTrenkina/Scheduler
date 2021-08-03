/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utills;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *DBConnection Class
 * @author Mariya.Trenkina
 */
public class DBConnection {
    //JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/WJ07bUN";
    
    //JDBC URL
    private static final String jdbcURL = protocol + vendorName + ipAddress;
    
    //Driver Interface reference
    private static String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    private static String username = "U07bUN";
    private static String password = "53688982692";

    /**
     *return SQL connection
     * @return return SQL connection
     */
    public static Connection startConnection(){
    try{
        Class.forName(MYSQLJDBCDriver);
        conn = (Connection)DriverManager.getConnection(jdbcURL,username, password);
    }
    catch(ClassNotFoundException e){
    }
    catch(SQLException e){
    }
    return conn;
    }
    
    /**
     *SQLException SQL error catch
     * @throws SQLException SQL error catch
     */
    public static void closeConnection() throws SQLException{
    conn.close();
    }
  }
    

