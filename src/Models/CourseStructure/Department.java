package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Department implements CourseStructure{

    private final String tableName = "Department";
    private final String primaryColumnName = "departmentCode";
    private String departmentCode;
    private String departmentName;

    public Department(){

    }

    public Department(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Department(String departmentCode, String departmentName) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
    }

    public String getCode() {
        return departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getPrimaryColumn() {
        return primaryColumnName;
    }

    public void add(){
        DBController.executeCommand("INSERT INTO Department VALUES ('"+this.departmentCode + "','" + this.departmentName+"');");
    }

    public void remove(){
        UserManipulator.remove(this.departmentCode,"Department","departmentCode");
    }

    public List<Department> getAll(){
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

    public ArrayList<Object> getVariables(){
        ArrayList<Object> list = new ArrayList<>();
        list.add(this.departmentCode);
        list.add(this.departmentName);
        return list;
    }

    public String[] getVariableNames(){
        return new String[] {"Department Code","Department Name"};
    }

    public Class[] getVariableClass(){
        return new Class[] {String.class,String.class};
    }

}
