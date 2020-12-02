package Models.UserAccounts;

import Views.Registrar.RegistrarWelcomeScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistarTest {

    private Registar registar;

    @BeforeEach
    public void init(){
        this.registar = new Registar();
    }

    @Test
    public void addRemoveStudent(){
        Student student = new Student("testy01","Mr","test","dsadas@gmail.com",101,"COMU01","ONE");
        for (int i = 0; i < 3; i++) {
            registar.addStudent(student);
            registar.removeStudent(student.getRegNumber());
        }
    }

}