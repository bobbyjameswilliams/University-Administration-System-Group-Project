package Controllers.Registrar;

import Models.DatabaseBehaviours.DBController;
import Models.Tables.Registrar.InspectRegTableRow;
import Models.UserAccounts.Student;
import Views.Registrar.InspectRegistration;

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

