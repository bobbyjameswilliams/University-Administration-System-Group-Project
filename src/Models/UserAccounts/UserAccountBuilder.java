package Models.UserAccounts;

import Models.DatabaseBehaviours.DBController;
import Models.Tables.StudentGrade;

import java.sql.*;
import java.util.NoSuchElementException;
import Models.UserAccounts.UserType;

public class UserAccountBuilder {

    private String username;

    public UserAccountBuilder (String username){
        this.username = username;
    }

    public Student studentBuilder() {
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Student " +
                                                                "JOIN User on Student.username = User.username\n " +
                                                                "WHERE Student.username = ?;");
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int regNumber = rs.getInt("regNumber");
                String forename = rs.getString("forename");
                String surname = rs.getString("surname");
                String emailAddress = rs.getString("emailAddress");
                String degreeCode = rs.getString("degreeCode");
                String levelOfStudy = rs.getString("levelOfStudy");
                return new Student(username,forename,surname,emailAddress,regNumber,degreeCode,levelOfStudy);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public UserType getEmployeeRole(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

            PreparedStatement pstmt = con.prepareStatement("SELECT role FROM Employee WHERE username = ?;");
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String role = rs.getString("role");
            return UserType.valueOf(role.toUpperCase());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public <T extends Employee> T employeeBuilder(T employee) {
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Employee " +
                    "JOIN User On User.username = Employee.username " +
                    "WHERE Employee.username = ?;");
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            employee.setUsername(rs.getString("username"));
            employee.setForename(rs.getString("forename"));
            employee.setSurname(rs.getString("surname"));
            employee.setEmailAddress(rs.getString("emailAddress"));
            employee.setEmployeeNumber(rs.getInt("employeeNumber"));
            return employee;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
