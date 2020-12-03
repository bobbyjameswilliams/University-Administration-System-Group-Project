package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;

import java.sql.*;
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

    @Override
    public void add(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO DegreeCompulsory (degreeCode,moduleCode,levelOfStudy)\n" +
                                                                "VALUES (?,?,?);");
            pstmt.setString(1,this.degreeCode);
            pstmt.setString(2,this.moduleCode);
            pstmt.setString(3,this.levelOfStudy.toString());

            int count = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void remove(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

            PreparedStatement pstmt = con.prepareStatement("DELETE FROM DegreeCompulsory\n " +
                                                                "WHERE degreeCode =? AND moduleCode =? ;");
            pstmt.setString(1,this.degreeCode);
            pstmt.setString(2,this.moduleCode);
            int count = pstmt.executeUpdate();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
