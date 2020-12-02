package Models.UserAccounts;

import Models.CourseStructure.Degree;
import Models.CourseStructure.LevelOfStudy;
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

    // Dummy Class
    public Registar(){
        super();
    }

    public Registar (String username,String forename,String surname,String emailAddress,int employeeNumber){
        super(username,forename, surname,emailAddress , employeeNumber);
    }

    @Override
    public UserType getRole(){
        return UserType.REGISTRAR;
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

}
