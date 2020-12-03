package Controllers.Teacher;

import Controllers.Registrar.InspectRegistrationController;
import Controllers.Registrar.InspectTeacherController;
import Controllers.Registrar.RegistrarWelcomeScreenController;
import Models.Tables.Registrar.RegistrarTableRow;
import Models.Tables.StudentGrade;
import Models.UserAccounts.Registar;
import Models.UserAccounts.Student.Student;
import Models.UserAccounts.Teacher;
import Models.UserAccounts.UserAccountBuilder;
import Views.Registrar.RegistrarWelcomeScreen;

public class TeacherWelcomeScreenController {

    private RegistrarWelcomeScreen registrarFrame;
    private Teacher teacher;

    public TeacherWelcomeScreenController(Teacher teacher ){
        this.teacher = teacher;
    }

    public void inspectStudentRegistration(StudentGrade row){
        Student student = UserAccountBuilder.studentBuilderFromReg(row.getRegNumber());
        if(student.getDegreeCode() != null && student.getLevelOfStudy() != null) {
            new InspectTeacherController(student);
        }
    }

    public static void main(String args[]){
        new TeacherWelcomeScreenController(new UserAccountBuilder("Teacher5").employeeBuilder(new Teacher()));
    }
}