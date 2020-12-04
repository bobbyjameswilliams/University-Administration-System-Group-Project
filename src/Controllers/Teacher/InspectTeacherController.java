package Controllers.Teacher;

import Models.CourseStructure.CompulsoryModule;
import Models.CourseStructure.CompusloryModuleConstraint;
import Models.DatabaseBehaviours.DBController;
import Models.Graduation.GradeAttainmentConstraint;
import Models.Graduation.LevelOfStudyConstraint;
import Models.Tables.Registrar.InspectRegTableRow;
import Models.UserAccounts.Student.InsufficientCreditEnrollment;
import Models.UserAccounts.Student.InsufficientGradeAttainment;
import Models.UserAccounts.Student.*;;
import Views.Registrar.InspectRegistration;
import Views.Teacher.TeacherInspect;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InspectTeacherController{
    private TeacherInspect inspectionFrame;

    public InspectTeacherController(Student student){
        this.inspectionFrame = new TeacherInspect(student,this);
        inspectionFrame.setVisible(true);
    }

    public void progressStudent(Student student) {
        try {
            inspectionFrame.errLabel.setText("");
            student.updateLevelOfStudy();
        } catch (InsufficientCreditEnrollment ex){
            inspectionFrame.errLabel.setText("Insufficient Module Credits");
        } catch (InsufficientGradeAttainment ex){
            inspectionFrame.errLabel.setText("Insufficient Grades Attained");
        }
    }

    public void retake(Student student){
        student.retake();
    }

    public void graduate(Student student){
        try {
            student.graduate();
        } catch (LevelOfStudyConstraint | GradeAttainmentConstraint ex ){
            inspectionFrame.errLabel.setText("Conditions not met");
        }
    }
}
