package Models.UserAccounts;

import Models.DatabaseBehaviours.DBController;
import Models.Tables.StudentGrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.NoSuchElementException;
import Models.UserAccounts.UserType;

public class UserAccountBuilder {

    private String username;

    public UserAccountBuilder (String username){
        this.username = username;
    }

    public Student studentBuilder() throws NoSuchElementException{
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Student JOIN User on Student.username = User.username WHERE Student.username = '" + username + "';";
            ResultSet rs =  stmt.executeQuery(query);
            // if student doesn't exist throw exception
            if (!rs.isBeforeFirst()){ throw new NoSuchElementException() ;}
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

    public UserType getEmployeeRole(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            String query = "SELECT role FROM Employee WHERE username = '" + username + "';";
            ResultSet rs =  stmt.executeQuery(query);
            rs.next();
            String role = rs.getString("role");
            return UserType.valueOf(role);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new NoSuchElementException();
    }

    public <T extends Employee> T employeeBuilder(T employee) {
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM Employee JOIN User On User.username = Employee.username WHERE Employee.username = '" + username + "';";
            ResultSet rs =  stmt.executeQuery(query);
            // if student doesn't exist throw exception
            if (!rs.isBeforeFirst()){ throw new NoSuchElementException() ;}
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
