package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Degree implements CourseStructure{

    private String degreeCode;
    private String courseName;
    private int lengthOfStudy;
    private boolean yearInIndustry;

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

    @Override
    public String getCode() {
        return this.degreeCode;
    }

    public void add(){
        String values = this.degreeCode + "','" + this.courseName + "','" + this.lengthOfStudy + "','" + this.boolToInt(this.yearInIndustry);
        DBController.executeCommand("INSERT INTO Degree VALUES ('"+values+"');");
    }

    public void remove(){
        UserManipulator userManipulator = new UserManipulator();
        userManipulator.remove(this.degreeCode,"Degree","degreeCode");
    }

    public boolean exists(){
        try (Connection con = DriverManager.getConnection(this.url,this.user,this.password)){
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery("SELECT COUNT(1) FROM Degree Where degreeCode = '"+degreeCode+"';");
            rs.next();
            int count = Integer.parseInt(rs.getString("COUNT(1)"));
            // Count should never be greater than one, I believe
            return count >= 1;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
