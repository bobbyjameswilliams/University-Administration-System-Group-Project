package Models.Authentication;

import Models.DatabaseBehaviours.DBController;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Base64;

public class Login {

    public static boolean loginAuthenticated(String username, String password){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Password WHERE username = '" + username + "' ;");
            String salt = "";
            String hash = "";
            while (rs.next()){
               hash = rs.getString("password");
               salt = rs.getString("salt");
            }
            byte[] bytes = Base64.getDecoder().decode(salt);
            Password password1 = new Password(password,bytes);
            return password1.get_SHA_384_SecurePassword().equals(hash);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main (String[] args){
        System.out.println(loginAuthenticated("Lembrei","test123"));
    }

}
