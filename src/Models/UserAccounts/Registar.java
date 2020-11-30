package Models.UserAccounts;

import Models.CourseStructure.Degree;
import Models.CourseStructure.UniModule;
import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Registar extends Employee {


    //for adding a new registrar to the DB
    public Registar (String forename,String surname){
        super(forename, surname);
    }
    //for editing a teacher that is already in the DB

    // Dummy Class
    public Registar(){
        super();
    }

    public Registar (String username,String forename,String surname,String emailAddress,int employeeNumber){
        super(username,forename, surname,emailAddress , employeeNumber);
    }

    public void addStudent(Student student){
        UserManipulator.addUser(student);
        String values = student.getStudentDetailsForInserting();
        DBController.executeCommand("INSERT INTO Student VALUES ('"+values+"');");
    }

    public void removeStudent(int regNumber){
        String studentRegNumber = Integer.toString(regNumber);
        final String url = "jdbc:mysql://stusql.dcs.shef.ac.uk/team045";
        final String user = "team045" ;
        final String password = "5e15b333";
        try (Connection con = DriverManager.getConnection(url,user,password)){
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery("SELECT * FROM Student WHERE regNumber = " + studentRegNumber);
            rs.next();
            String username = rs.getString("username");
            // Removing user will remove the associated student, due to cascade delete
            UserManipulator.remove(username,"User","username");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void makeModuleCompulsory(String degreeCode, String moduleCode,String moduleName){
        Degree degree = new Degree(degreeCode);
        UniModule module = new UniModule(moduleCode, moduleName);
        if (degree.exists() & module.exists()){
            String values = degree.getCode() + "','" + module.getCode()+"','"+module.getLevelOfStudy();
            DBController.executeCommand("INSERT INTO DegreeCompulsory (moduleCode,moduleName,levelOfStudy) VALUES ('"+values+"');");
        }
    }

    // if a student has met all their credit requirements
    public boolean studentMetRequirement(Student student){
        return (student.getCreditsTaken() == student.getCreditRequirements());
    }

}
