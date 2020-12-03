package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DegreeBuilder {

    public static Degree build(String degreeCode){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            String query = "SELECT * FROM Degree WHERE degreeCode= '" + degreeCode + "' ;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            String courseName = rs.getString("courseName");
            int lengthOfStudy = rs.getInt("lengthOfStudy");
            boolean yearInIndustry = Boolean.getBoolean(rs.getString("yearInIndustry"));
            Qualification qualification = Qualification.valueOf(rs.getString("qualification"));
            return new Degree(degreeCode,courseName,lengthOfStudy,yearInIndustry,qualification);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

}
