/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.teknikum.utilities;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author danlun2
 */
public class ConnectionFactory {
    
    public static Connection getConnection() throws Exception{
    
        String url = "jdbc:mysql://localhost/timeline";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = (com.mysql.jdbc.Connection)DriverManager.getConnection(url, username, password);
        return connection;
    }
    
}
