package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompulsoryModule implements CourseStructure {

    private int uniqueId;
    private String degreeCode;
    private String moduleCode;
    private LevelOfStudy levelOfStudy;

    private String tableName = "DegreeCompulsory";
    private String primaryColumnName = "uniqueID";

    public CompulsoryModule() {}

    public CompulsoryModule(int uniqueId, String degreeCode, String moduleCode, LevelOfStudy levelOfStudy) {
        this.uniqueId = uniqueId;
        this.degreeCode = degreeCode;
        this.moduleCode = moduleCode;
        this.levelOfStudy = levelOfStudy;
    }

    @Override
    public String getCode(){
        return Integer.toString(uniqueId);
    }

    public String getModuleCode(){ return this.moduleCode;}

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
        list.add(this.degreeCode);
        list.add(this.moduleCode);
        list.add(this.levelOfStudy);
        return list;
    }

    @Override
    public String[] getVariableNames() {return new String[] {"Unique ID","Degree Code","Module Code","Level Of Study"};}

    @Override
    public Class[] getVariableClass() {return new Class[] {Integer.class,String.class,String.class,String.class};}

    @Override
    public List<CompulsoryModule> getAll(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            List<CompulsoryModule> compulsoryModules = new ArrayList<>();
            String query = "SELECT * FROM " + getTableName();
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                int uniqueId = rs.getInt("uniqueID");
                String degreeCode = rs.getString("degreeCode");
                String moduleCode = rs.getString("moduleCode");
                LevelOfStudy levelOfStudy = LevelOfStudy.valueOf(rs.getString("levelOfStudy"));
                compulsoryModules.add(new CompulsoryModule(uniqueId,degreeCode,moduleCode,levelOfStudy));
            }
            return compulsoryModules;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<CompulsoryModule> getAll(String specificDegreeCode,LevelOfStudy specificLevelOfStudy){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            List<CompulsoryModule> compulsoryModules = new ArrayList<>();
            String query = "SELECT * FROM " + getTableName() + " WHERE degreeCode = '" + specificDegreeCode + "' AND " +
                    "levelOfStudy = '" + specificLevelOfStudy.toString() + "';" ;
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                int uniqueId = rs.getInt("uniqueID");
                String degreeCode = rs.getString("degreeCode");
                String moduleCode = rs.getString("moduleCode");
                LevelOfStudy levelOfStudy = LevelOfStudy.valueOf(rs.getString("levelOfStudy"));
                compulsoryModules.add(new CompulsoryModule(uniqueId,degreeCode,moduleCode,levelOfStudy));
            }
            return compulsoryModules;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(){
        String values = this.degreeCode + "','" + this.moduleCode + "','" + this.levelOfStudy.toString();
        DBController.executeCommand("INSERT INTO DegreeCompulsory VALUES ('"+values+"');");
    }

    @Override
    public void remove(){
        String query = "DELETE FROM DegreeCompulsory WHERE degreeCode = '" + this.degreeCode + "' AND moduleCode = '" + this.moduleCode + "' ;";
        DBController.executeCommand(query);
    }

}
