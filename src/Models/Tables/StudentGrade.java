package Models.Tables;

import Models.DatabaseBehaviours.DBController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class StudentGrade {

    private int regNumber;
    private String moduleCode;
    private String forename;
    private String surname;
    private String levelOfStudyTaken;
    private int grade;
    private int resit;

    protected final String url = "jdbc:mysql://stusql.dcs.shef.ac.uk/team045";
    protected final String user = "team045" ;
    protected final String password = "5e15b333";

    public StudentGrade(int regNumber, String moduleCode,String forename, String surname, int grade, int resit) {
        this.regNumber = regNumber;
        this.moduleCode = moduleCode;
        this.forename = forename;
        this.surname = surname;
        this.grade = grade;
        this.resit = resit;
    }

    public StudentGrade(String moduleCode,String levelOfStudyTaken,int grade, int resit) {
        this.moduleCode = moduleCode;
        this.levelOfStudyTaken = levelOfStudyTaken;
        this.grade = grade;
        this.resit = resit;
    }

    public int getRegNumber() { return  regNumber;}

    public String getModuleCode() {return moduleCode;}

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getLevelOfStudyTaken() {return levelOfStudyTaken;}

    public int getGrade() {
        return grade;
    }

    public int getResit() {
        return resit;
    }

    public void setGrade(int grade) {
        this.grade = grade;
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

            PreparedStatement pstmt = con.prepareStatement("UPDATE StudentModule SET grade =? \n"+
                                                                " WHERE regNumber =? AND moduleCode =? ;");
            pstmt.setInt(1,this.getGrade());
            pstmt.setInt(2,this.getRegNumber());
            pstmt.setString(3, this.getModuleCode());

            int count = pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
