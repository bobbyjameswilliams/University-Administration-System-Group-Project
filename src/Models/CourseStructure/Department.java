package Models.CourseStructure;

import Models.DatabaseBehaviours.DBController;
import Models.DatabaseBehaviours.UserManipulator;

public class Department {

    private String departmentCode;
    private String departmentName;

    public Department(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public Department(String departmentCode, String departmentName) {
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
    }

    public void addUniversityDepartment(){
        DBController.executeCommand("INSERT INTO Department VALUES ('"+this.departmentCode + "','" + this.departmentName+"');");
    }

    public void removeUniversityDepartment(){
        UserManipulator.remove(this.departmentCode,"Department","departmentCode");
    }

}
