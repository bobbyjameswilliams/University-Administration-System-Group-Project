package Models;

import javax.management.StandardEmitterMBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Administrator extends Employee {

    public Administrator(String username,String forename,String surname,String emailAddress,int employeeNumber){
        super(username, forename, surname, emailAddress, employeeNumber);
    }

    private void remove(String toRemove,String tableName, String primaryKey){
        DBController.executeCommand("DELETE FROM " + tableName + " where " + primaryKey + " = '"+toRemove+"';");
    }

    public void addModule(String moduleCode,int credits,int levelOfStudy){
        String values = moduleCode + "','" + credits + "','" + levelOfStudy;
        System.out.println("INSERT INTO Module VALUES ('"+values+"');");
        DBController.executeCommand("INSERT INTO Module VALUES ('"+values+"');");
    }

    public void removeModule(String moduleCode){
        this.remove(moduleCode,"Module","moduleCode");
    }

    private void addUser(User user) {
        DBController.executeCommand("INSERT INTO User VALUES ('"+user.getUserDetails()+"');");
    }

    public void addEmployee(Employee employee, EmployeeRole role){
        this.addUser(employee);
        String values = employee.getEmployeeNumber() + "','" + employee.getUsername() + "','Teacher";
        DBController.executeCommand("INSERT INTO Employee VALUES ('"+values+"');");
    }

    public void removeEmployee(int employeeNumber){
        String empNumber = Integer.toString(employeeNumber);
        final String url = "jdbc:mysql://stusql.dcs.shef.ac.uk/team045";
        final String user = "team045" ;
        final String password = "5e15b333";
        try (Connection con = DriverManager.getConnection(url,user,password)){
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery("SELECT * FROM Employee WHERE employeeNumber = " + empNumber);
            rs.next();
            String username = rs.getString("username");
            // Removing user will remove the associated employee
            this.remove(username,"User","username");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void addDegree(String courseName, int lengthOfStudy, boolean yearInIndustry){
        String degreeCode = "COM060";
        String values = degreeCode + "','" + courseName + "','" + Integer.toString(lengthOfStudy) + "','" +  boolToInt(yearInIndustry) ;
        DBController.executeCommand("INSERT INTO Degree VALUES ('"+values+"');");
}

    public void removeDegree(String degreeCode){
        this.remove(degreeCode,"Degree","degreeCode");
    }

    public void addUniversityDepartment(String departmentName){
        DBController.executeCommand("INSERT INTO Department VALUES ('"+departmentName+"');");
    }

    public void removeUniversityDepartment(String departmentName){
        this.remove(departmentName,"Department","departmentName");
    }

}
