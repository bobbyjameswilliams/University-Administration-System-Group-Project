package Models;

import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
public class DBController {

    public static Connection getConnection(){
        final String url = "jdbc:mysql://stusql.dcs.shef.ac.uk/team045";
        final String user = "team045" ;
        final String password = "5e15b333";
        try{
            Connection con = DriverManager.getConnection(url,user,password);
            return con;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
