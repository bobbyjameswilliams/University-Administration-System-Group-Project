package Models.UserAccounts;

import Models.DatabaseBehaviours.DBController;
import Models.Tables.StudentGrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserAccountBuilder {

    public static Student studentBuilder(String username){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Student JOIN User on Student.username = User.username WHERE Student.username = '" + username + "';";
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                int regNumber = rs.getInt("regNumber");
                String forename = rs.getString("forename");
                String surname = rs.getString("surname");
                String emailAddress = rs.getString("emailAddress");
                String degreeCode = rs.getString("degreeCode");
                int levelOfStudy = rs.getInt("yearOfStudy");
                return new Student(username,forename,surname,emailAddress,regNumber,degreeCode,levelOfStudy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
