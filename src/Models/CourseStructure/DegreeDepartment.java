package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DegreeDepartment implements CourseStructure{
    private int uniqueId;
    private String departmentCode;
    private String degreeCode;

    private String tableName = "DegreeDepartment";
    private String primaryColumnName = "uniqueID";

    public DegreeDepartment() {}

    public DegreeDepartment(int uniqueId,String departmentCode,String degreeCode) {
        this.uniqueId = uniqueId;
        this.degreeCode = degreeCode;
        this.departmentCode = departmentCode;
    }

    @Override
    public String getCode(){
        return Integer.toString(uniqueId);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String getPrimaryColumn() {
        return primaryColumnName;
    }

    @Override
    public ArrayList<Object> getVariables(){
        ArrayList<Object> list = new ArrayList<>();
        list.add(this.uniqueId);
        list.add(this.departmentCode);
        list.add(this.degreeCode);
        return list;
    }

    @Override
    public String[] getVariableNames() {return new String[] {"Unique ID","Department Code","Degree Code"};}

    @Override
    public Class[] getVariableClass() {return new Class[] {Integer.class,String.class,String.class};}

    @Override
    public List<DegreeDepartment> getAll(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            List<DegreeDepartment> degreeDepartments = new ArrayList<>();
            String query = "SELECT * FROM " + getTableName();
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                int uniqueId = rs.getInt("uniqueID");
                String departmentCode = rs.getString("departmentCode");
                String degreeCode = rs.getString("degreeCode");
                degreeDepartments.add(new DegreeDepartment(uniqueId,departmentCode,degreeCode));
            }
            return degreeDepartments;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(){
        String values = this.departmentCode + "','" + this.degreeCode;
        DBController.executeCommand("INSERT INTO DegreeDepartment VALUES ('"+values+"');");
    }

    @Override
    public void remove(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            String query = "DELETE FROM DegreeDepartment WHERE degreeCode = '" + this.degreeCode + "' AND departmentCode = '" + this.departmentCode+ "' ;";
            stmt.execute(query);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
