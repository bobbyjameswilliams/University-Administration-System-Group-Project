package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Administrator extends Employee {

    public Administrator(String username,String forename,String surname,String emailAddress,int employeeNumber){
        super(username, forename, surname, emailAddress, employeeNumber);
    }

    private void remove(String toRemove,String tableName, String primaryKey){
        try (Connection connection = DBController.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM " + tableName + " where " + primaryKey + " = '"+toRemove+"';");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addModule(String moduleCode,int credits,int levelOfStudy){
        try (Connection connection = DBController.getConnection()){
            Statement stmt = connection.createStatement();
            String values = moduleCode + "," + credits + "," + levelOfStudy;
            stmt.execute("INSERT INTO Module VALUES ('"+values+"');");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeModule(String moduleCode){
        this.remove(moduleCode,"Module","moduleCode");
    }

    public void addEmployee(){

    }

    public void removeEmployee(int employeeNumber){
        // MySQL database set up so deletion of employee cascades to user table as well
        String empNumber = Integer.toString(employeeNumber);
        this.remove(empNumber,"Employee","employeeNumber");
    }

    public void addDegree(){

    }

    public void removeDegree(String degreeCode){
        this.remove(degreeCode,"Degree","degreeCode");
    }

    public void addUniversityDepartment(String departmentName){
        try (Connection connection = DBController.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO Department VALUES ('"+departmentName+"');");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeUniversityDepartment(String departmentName){
        this.remove(departmentName,"Department","departmentName");
    }

}
