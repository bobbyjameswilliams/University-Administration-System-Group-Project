package Controllers.Registrar;

import Models.Tables.Registrar.RegistrarTableRow;
import Models.UserAccounts.Registar;
import Models.UserAccounts.Student.*;;
import Views.Registrar.RegistrarWelcomeScreen;

public class RegistrarWelcomeScreenController {
    private RegistrarWelcomeScreen registrarFrame;
    private Registar registrar;

    public RegistrarWelcomeScreenController(){
        this.registrar = new Registar();
        this.registrarFrame = new RegistrarWelcomeScreen(registrar, this);
        registrarFrame.setVisible(true);
    }
    public RegistrarWelcomeScreenController(Registar registrar){
        this.registrar = registrar;
        this.registrarFrame = new RegistrarWelcomeScreen(registrar, this);
        registrarFrame.setVisible(true);
    }

    public void inspectStudentRegistration(RegistrarTableRow row){
        if(row.getDegreeCode() != null && row.getLevelOfStudy() != null) {
            InspectRegistrationController inspectFrame = new InspectRegistrationController(new Student(row.getUserName(), row.getForeName(), row.getSurName(), row.getEmail(), row.getRegNumber(), row.getDegreeCode(), row.getLevelOfStudy()));
        }

    }
    public void assignStudent(RegistrarTableRow row, String degreeCode, String levelOfStudy){
        row.setRegistration(degreeCode, levelOfStudy);
    }
    public void unassignStudent(RegistrarTableRow row){
        row.unassignRegistration();
    }

    public static void main(String args[]){
        RegistrarWelcomeScreenController controller = new RegistrarWelcomeScreenController();
    }
}
