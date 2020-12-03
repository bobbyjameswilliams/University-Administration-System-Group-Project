package Controllers.Registrar;

import Models.DatabaseBehaviours.DBController;
import Models.UserAccounts.Student.InsufficientCreditEnrollment;
import Models.UserAccounts.Student.InsufficientGradeAttainment;
import Models.UserAccounts.Student.Student;
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
            // TODO Exception handling needs to be changes
        } catch (InsufficientCreditEnrollment ex){
            inspectionFrame.errLabel.setText("Insufficient Module Credits");
        } catch (InsufficientGradeAttainment ex){
            inspectionFrame.errLabel.setText("Insufficient Grades Attained");
        }
    }

    public void retake(Student student){
        student.retake();
    }

    public List<String> dataForModuleCombo(){
        String query = "SELECT * FROM Module;";
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
}

