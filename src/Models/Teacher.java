package Models;

import javax.xml.xpath.XPathEvaluationResult;
import java.sql.ResultSet;

public class Teacher extends Employee {

    public Teacher (String username,String forename,String surname,String emailAddress,int employeeNumber){
        super(username, forename, surname, emailAddress, employeeNumber);
    }

    public ResultSet getTutees(){
        return null;
    }

    public ResultSet getModulesTaught(){
        return null;
    }

    public void editStudentGrade(){

    }

}
