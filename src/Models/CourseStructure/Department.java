package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;
import Models.Tables.StudentGrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Department {

    public String getDepartmentCode() {
        return departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    private String departmentCode;
    private String departmentName;

    public Department(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Department(String departmentCode, String departmentName) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
    }

    public void addUniversityDepartment(){
        DBController.executeCommand("INSERT INTO Department VALUES ('"+this.departmentCode + "','" + this.departmentName+"');");
    }

    public void removeUniversityDepartment(){
        UserManipulator.remove(this.departmentCode,"Department","departmentCode");
    }

    public static List<Department> getAllDepartments(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            List<Department> departments = new ArrayList<>();
            String query = "SELECT * FROM Department";
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                String departmentCode = rs.getString("departmentCode");
                String departmentName = rs.getString("departmentName");
                departments.add(new Department(departmentCode,departmentName));
            }
            return departments;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void updateDepartmentName(String departmentName){
        this.setDepartmentName(departmentName);
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            String query = "UPDATE Department SET departmentName = '" + this.departmentName + "' WHERE departmentCode = '" + this.departmentCode + "';";
            System.out.println(query);
            stmt.execute(query);
        } catch (Exception ex){

        }
    }
}
