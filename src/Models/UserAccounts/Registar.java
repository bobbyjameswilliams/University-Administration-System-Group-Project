package Models.UserAccounts;

import Models.CourseStructure.Degree;
import Models.CourseStructure.LevelOfStudy;
import Models.CourseStructure.UniModule;
import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;
import Models.Tables.Admin.UserTableRow;
import Models.Tables.Registrar.RegistrarTableRow;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Registar extends Employee {


    //for adding a new registrar to the DB
    public Registar (String forename,String surname){
        super(forename, surname);
    }

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

    public void makeModuleCompulsory(String degreeCode, String moduleCode, LevelOfStudy levelOfStudy){
        Degree degree = new Degree(degreeCode);
        UniModule module = new UniModule(moduleCode);
        if (degree.exists() & module.exists()){
            String values = degree.getCode() + "','" + module.getCode()+"','"+levelOfStudy.toString();
            DBController.executeCommand("INSERT INTO DegreeCompulsory (moduleCode,moduleName,levelOfStudy) VALUES ('"+values+"');");
        }
    }

    // if a student has met all their credit requirements
    public boolean studentMetRequirement(Student student){
        return (student.getCreditsTaken() == student.getCreditRequirements());
    }

    public List<RegistrarTableRow> getAllStudents(){
        String query = "SELECT Student.regNumber, Student.userName, Student.degreeCode, Student.levelOfStudy, User.forename, User.surname, User.emailAddress from Student JOIN User ON Student.username = User.username;";
        System.out.println(query);
        List<RegistrarTableRow> registrarTableRows = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                int regNumber = rs.getInt("regNumber");
                String userName = rs.getString("username");
                String degreeCode = rs.getString("degreeCode");
                String levelOfStudy = rs.getString("levelOfStudy");
                String forename = rs.getString("forename");
                String surname = rs.getString("surname");
                String emailAddress = rs.getString("emailAddress");
                registrarTableRows.add(new RegistrarTableRow(regNumber,userName,degreeCode,levelOfStudy,forename,surname,emailAddress));
            }
            return registrarTableRows;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
