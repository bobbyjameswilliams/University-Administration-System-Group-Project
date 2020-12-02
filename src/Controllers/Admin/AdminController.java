package Controllers.Admin;

import Models.CourseStructure.Degree;
import Models.CourseStructure.Department;
import Models.CourseStructure.UniModule;
import Models.UserAccounts.*;
import Views.Admin.AdminWelcomeScreen;

import java.util.List;

public class AdminController {

    private Administrator administrator;

    public AdminController(Administrator administrator) {this.administrator = administrator;}

    public void addModule(String moduleNumber,String moduleName,int credits,String departmentName){
        String departmentCode = Department.getCodeFromName(departmentName);
        String moduleCode = UniModule.generateModuleCode(departmentCode,moduleNumber);
        administrator.addModule(moduleCode,moduleName,credits);
    }

    public void addDepartment(String departmentCode,String departmentName){
        administrator.addUniversityDepartment(departmentCode,departmentName);
    }

    public void addCourse(String departmentName,String degreeSignature,String courseName,int lengthOfStudy,boolean yearInIndustry){
        String departmentCode = Department.getCodeFromName(departmentName);
        String degreeCode = Degree.generateDegreeCode(departmentCode,degreeSignature);
        administrator.addDegree(degreeCode,courseName,lengthOfStudy,yearInIndustry);
    }

    public void addUser(UserType userType,String forename,String surname){
        switch (userType){
            case STUDENT:
                Student student = new Student(forename,surname);
                administrator.addStudent(student);
                return;
            case TEACHER:
                Teacher teacher = new Teacher(forename,surname);
                administrator.addEmployee(teacher);
                return;
            case REGISTRAR:
                Registar  registrar = new Registar(forename,surname);
                administrator.addEmployee(registrar);
                return;
            case ADMIN:
                Administrator admin = new Administrator(forename,surname);
                administrator.addEmployee(admin);
                return;
        }
    }

    public String getAdminForename(){
        return administrator.getForename();
    }

    public String getAdminSurname(){
        return administrator.getSurname();
    }

}
