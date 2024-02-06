
package coffeeshop;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataBase {
    
    public static Connection connectDb(){
        
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/arthurcoffee", "root","");
           return connect;
        } catch(Exception e) {e.printStackTrace();}
        return null;
    }
}
