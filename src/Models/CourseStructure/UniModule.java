package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;
import Models.UserAccounts.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UniModule implements CourseStructure{

    private String tableName = "Module";
    private final String primaryColumnName = "moduleCode";
    private String moduleName;
    private String moduleCode;
    private int credits;

    public UniModule(){}

    //Constructor where only degreeCode is needed (remove,exists)
    public UniModule(String moduleCode){
        this.moduleCode = moduleCode;
        this.credits = 20;
    }

    public UniModule(String moduleCode,String moduleName, int credits){
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.credits = credits;
    }

    public static String generateModuleCode(String departmentCode,String moduleNumber){
        return departmentCode + moduleNumber;
    }

    @Override
    public String getCode(){
        return this.moduleCode;
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
       list.add(this.moduleCode);
       list.add(this.moduleName);
       list.add(this.credits);
       return list;
    }

    @Override
    public String[] getVariableNames() {return new String[] {"Module Code","Module Name","Credits"};}

    @Override
    public Class[] getVariableClass() {return new Class[] {String.class,String.class,Integer.class};}

    @Override
    public List<UniModule> getAll(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            List<UniModule> modules = new ArrayList<>();
            String query = "SELECT * FROM " + getTableName();
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                String moduleCode = rs.getString("moduleCode");
                String moduleName = rs.getString("moduleName");
                int credits = rs.getInt("credits");
                modules.add(new UniModule(moduleCode,moduleName,credits));
            }
            return modules;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String[] getAllModuleCodes(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            List<String> moduleCodes = new ArrayList<>();
            String query = "SELECT * FROM Module";
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                String moduleCode = rs.getString("moduleCode");
                moduleCodes.add(moduleCode);
            }
            return moduleCodes.toArray(new String[moduleCodes.size()]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(){
        String values = this.moduleCode + "','" + this.moduleName + "','" + this.credits;
        DBController.executeCommand("INSERT INTO Module VALUES ('"+values+"');");
    }

    @Override
    public void remove(){
        UserManipulator userManipulator = new UserManipulator();
        userManipulator.remove(this.moduleCode,"Module","moduleCode");
    }

}
