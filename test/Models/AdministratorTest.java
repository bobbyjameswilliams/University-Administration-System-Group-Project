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

}