package Controllers.Admin;

import Models.Authentication.SignUp;
import Models.CourseStructure.*;
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

    public void addCourse(String departmentName, String degreeSignature, String courseName, int lengthOfStudy, boolean yearInIndustry, String qual){
        Qualification qualification = Qualification.valueOf(qual);
        String departmentCode = Department.getCodeFromName(departmentName);
        String degreeCode = Degree.generateDegreeCode(departmentCode,degreeSignature);
        administrator.addDegree(degreeCode,courseName,lengthOfStudy,yearInIndustry,qualification);
    }

    public void addCompulsoryModule(String degreeCode,String moduleCode,LevelOfStudy levelOfStudy){
        administrator.addCompulsoryModule(degreeCode,moduleCode,levelOfStudy);
    }

    public void addDegreeDepartment(String departmentCode,String degreeCode){
        administrator.addDegreeDepartment(departmentCode,degreeCode);
    }

    public void addUser(UserType userType,String forename,String surname,String password){
        switch (userType){
            case STUDENT:
                Student student = new Student(forename,surname);
                administrator.addStudent(student);
                SignUp.signUpUser(student,password);
                return;
            case TEACHER:
                Teacher teacher = new Teacher(forename,surname);
                administrator.addEmployee(teacher);
                SignUp.signUpUser(teacher,password);
                return;
            case REGISTRAR:
                Registar  registrar = new Registar(forename,surname);
                administrator.addEmployee(registrar);
                SignUp.signUpUser(registrar,password);
                return;
            case ADMIN:
                Administrator admin = new Administrator(forename,surname);
                administrator.addEmployee(admin);
                SignUp.signUpUser(admin,password);
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
