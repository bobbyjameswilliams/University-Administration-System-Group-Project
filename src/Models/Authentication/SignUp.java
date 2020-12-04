package Models.Authentication;

import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;
import Models.Tables.StudentGrade;
import Models.UserAccounts.User;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Base64;

public class SignUp {
    /**
     * This method Assigns a password to the desired user
     * @param user , user object to signUp
     * @param password , the password to be assigned to the user
     */
    public static void signUpUser(User user, String password){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Password password1 = new Password(password);
            String hash = password1.get_SHA_384_SecurePassword();
            String salt = Base64.getEncoder().encodeToString(password1.getSalt());
            String username = user.getUsername();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Password (username,password,salt)\n" +
                    "VALUES (?,?,?);");
            pstmt.setString(1,username);
            pstmt.setString(2,hash);
            pstmt.setString(3,salt);
            int count = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main (String[] args ) {
        User user = new User("acb18bw","James","Williams","jwilliams1@sheffield.ac.uk");
        UserManipulator.addUser(user);
        signUpUser(user,"test123");
    }
}
