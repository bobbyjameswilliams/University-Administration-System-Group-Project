package Models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    private Administrator administrator;

    @BeforeEach
    public void init(){
        administrator = new Administrator("test","test","test","test",324);
    }

    @Test
    public void departmentTest(){
        administrator.addUniversityDepartment("Engineering");
        administrator.removeUniversityDepartment("Engineering");
    }

    @Test
    public void addEmployeeTest(){
        // Obviously can't add your irl, but youre not gonna be able to login if you dont exist
        administrator.addEmployee(administrator,EmployeeRole.ADMIN);
        administrator.removeEmployee(administrator.getEmployeeNumber());
    }

    @Test
    public void addModuleTest(){
        administrator.addModule("COM2004",35,2);
        administrator.removeModule("COM2004");
    }

    @Test
    public void degreeTest(){
        administrator.addDegree("Computer Science",3,false);
        // This will ned to be changed
        administrator.removeDegree("COM060");
    }

}