package Models.CourseStructure;

import Models.UserAccounts.Administrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DegreeTest {

    private Degree degree;

    @BeforeEach
    public void init(){
        degree = new Degree("ENG304","Civil Engineering",3,false);
    }

    @Test
    public void addExistDeleteTest(){
        degree.add();
        assertEquals(true,degree.exists());
        degree.remove();
        assertEquals(false,degree.exists());
    }

}
