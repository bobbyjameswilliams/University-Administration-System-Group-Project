package Controllers.Registrar;

import Models.Tables.Registrar.RegistrarTableRow;
import Models.UserAccounts.Registar;
import Models.UserAccounts.Student;
import Views.Registrar.InspectRegistration;
import Views.Registrar.RegistrarWelcomeScreen;

import javax.swing.*;
import java.util.List;

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



    public void inspectStudentRegistration(RegistrarTableRow rowIndex){
        new InspectRegistration(new Student());
    }

    public static void main(String args[]){
        RegistrarWelcomeScreenController controller = new RegistrarWelcomeScreenController();
    }
}
