package Controllers.Registrar;

import Models.CourseStructure.CompulsoryModule;
import Models.CourseStructure.CompusloryModuleConstraint;
import Models.DatabaseBehaviours.DBController;
import Models.Tables.Registrar.InspectRegTableRow;
import Models.UserAccounts.Student.InsufficientCreditEnrollment;
import Models.UserAccounts.Student.InsufficientGradeAttainment;
import Models.UserAccounts.Student.*;;
import Views.Registrar.InspectRegistration;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InspectRegistrationController {
    private InspectRegistration inspectionFrame;

    public InspectRegistrationController(Student student){
        this.inspectionFrame = new InspectRegistration(student,this);
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

    public List<String> dataForModuleCombo(Student student){
        String query = "SELECT moduleCode FROM Module WHERE moduleCode NOT IN (SELECT moduleCode FROM StudentModule WHERE regNumber = "+student.getRegNumber()+");";
        System.out.println(query);
        List<String> moduleCodes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
            Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(query);
            while(rs.next()){
                moduleCodes.add(rs.getString("moduleCode"));
            }
            return moduleCodes;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void assignOptionalModule(String moduleCode, Student student){
        String query = "INSERT INTO StudentModule (regNumber, moduleCode, grade, resit, levelOfStudyTaken) VALUES(" + student.getRegNumber() + ", \"" + moduleCode + "\" ," + 0 + ", FALSE, \"" + "TWO\")"  ;
        DBController.executeCommand(query);
    }

    public void removeOptionalModule(InspectRegTableRow row, Student student) {
        // If trying to remove compulsory module issue exception
        if (CompulsoryModule.isCompulsoryModule(student.getDegreeCode(),row.getModuleCode())) new CompusloryModuleConstraint();
        String query = "DELETE FROM StudentModule WHERE regNumber = " + student.getRegNumber() + " AND moduleCode = \"" + row.getModuleCode() + "\";";
        DBController.executeCommand(query);
    }
}

