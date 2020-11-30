package Models.UserAccounts;

import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;
import Models.CourseStructure.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Administrator extends Employee {

    // Dummy Class
    public Administrator(){
        super();
    }

    public Administrator(String username,String forename,String surname,String emailAddress,int employeeNumber){
        super(username, forename, surname, emailAddress, employeeNumber);
    }

    public void addModule(String moduleCode,String moduleName,int credits,int levelOfStudy){
        UniModule module = new UniModule(moduleCode,moduleName,credits,levelOfStudy);
        module.add();
    }

    public void removeModule(String moduleCode){
        UniModule module = new UniModule(moduleCode);
        module.remove();
    }

    public void addEmployee(Employee employee, UserType role){
        if (role != UserType.STUDENT) {
            UserManipulator.addUser(employee);
            String values = employee.getEmployeeNumber() + "','" + employee.getUsername() + "','Teacher";
            DBController.executeCommand("INSERT INTO Employee VALUES ('" + values + "');");
        }
    }

    public void removeEmployee(int employeeNumber){
        String empNumber = Integer.toString(employeeNumber);
        try (Connection con = DriverManager.getConnection(url,user,password)){
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery("SELECT * FROM Employee WHERE employeeNumber = " + empNumber);
            rs.next();
            String username = rs.getString("username");
            // Removing user will remove the associated employee
            UserManipulator.remove(username,"User","username");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void addDegree(String degreeCode,String courseName, int lengthOfStudy, boolean yearInIndustry){
        Degree degree = new Degree(degreeCode,courseName,lengthOfStudy,yearInIndustry);
        degree.add();
    }

    public void removeDegree(String degreeCode){
        Degree degree = new Degree(degreeCode);
        degree.remove();
    }

    public void addUniversityDepartment(String departmentName){
        DBController.executeCommand("INSERT INTO Department VALUES ('"+departmentName+"');");
    }

    public void removeUniversityDepartment(String departmentName){
        UserManipulator.remove(departmentName,"Department","departmentName");
    }

}
