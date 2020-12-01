package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Degree implements CourseStructure{

    private final String tableName = "Degree";
    private final String primaryColumnName = "degreeCode";

    private String degreeCode;
    private String courseName;
    private int lengthOfStudy;
    private boolean yearInIndustry;

    public Degree(){}

    //Constructor where only degreeCode is needed (remove,exists)
    public Degree(String degreeCode){
        this.degreeCode = degreeCode;
        this.courseName = "Dummy Degree";
        this.lengthOfStudy = 3;
        this.yearInIndustry = false;
    }

    public Degree(String degreeCode, String courseName, int lengthOfStudy, boolean yearInIndustry){
        this.degreeCode = degreeCode;
        this.courseName = courseName;
        this.lengthOfStudy = lengthOfStudy;
        this.yearInIndustry = yearInIndustry;
    }

    public int boolToInt(boolean x){
        return x ? 1 : 0 ;
    }

    public boolean IntToBool(int x){
        return x == 1;
    }

    @Override
    public String getCode() {
        return this.degreeCode;
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
        list.add(this.degreeCode);
        list.add(this.courseName);
        list.add(this.lengthOfStudy);
        list.add(this.yearInIndustry);
        return list;
    }

    @Override
    public String[] getVariableNames(){ return new String[] {"Degree Code","Course Name","Length Of Study","Year in Industry"};}

    @Override
    public Class[] getVariableClass(){ return new Class[] {String.class,String.class,Integer.class,Boolean.class};}

    public void add(){
        String values = this.degreeCode + "','" + this.courseName + "','" + this.lengthOfStudy + "','" + this.boolToInt(this.yearInIndustry);
        DBController.executeCommand("INSERT INTO Degree VALUES ('"+values+"');");
    }

    public void remove(){
        UserManipulator userManipulator = new UserManipulator();
        userManipulator.remove(this.degreeCode,"Degree","degreeCode");
    }

    public List<Degree> getAll(){
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            List<Degree> degrees = new ArrayList<>();
            String query = "SELECT * FROM " + this.getTableName();
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                String degreeCode = rs.getString("degreeCode");
                String courseName = rs.getString("courseName");
                int lengthOfStudy = rs.getInt("lengthOfStudy");
                int yearInIndustry = rs.getInt("yearInIndustry");
                degrees.add(new Degree(degreeCode,courseName,lengthOfStudy,this.IntToBool(yearInIndustry)));
            }
            return degrees;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
