package Models.UserAccounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    private Student student;

    @BeforeEach
    public void init(){
        student = new Student("test","test","test","test",12345,"ENG040",2);
    }

    @Test
    public void getCreditsTakenTest() {
        assertEquals(20,student.getCreditsTaken());
    }

}
