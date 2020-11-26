package Models.Tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class StudentGrade {

    private int regNumber;
    private String forename;
    private String surname;
    private int grade;
    private int resit;

    protected final String url = "jdbc:mysql://stusql.dcs.shef.ac.uk/team045";
    protected final String user = "team045" ;
    protected final String password = "5e15b333";

    public StudentGrade(int regNumber, String forename, String surname, int grade, int resit) {
        this.regNumber = regNumber;
        this.forename = forename;
        this.surname = surname;
        this.grade = grade;
        this.resit = resit;
    }
    public StudentGrade(int grade, int resit) {
        this.grade = grade;
        this.resit = resit;
    }


    public int getRegNumber() { return  regNumber;}

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public int getGrade() {
        return grade;
    }

    public int getResit() {
        return resit;
    }

    public void setGrade(int grade) {
        this.grade = grade;
        try (Connection con = DriverManager.getConnection(this.url,this.user,this.password)){
            Statement stmt = con.createStatement();
            String query = "UPDATE StudentModule SET grade = " + this.getGrade() + " WHERE regNumber = " + this.getRegNumber() + ";";
            stmt.execute(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
