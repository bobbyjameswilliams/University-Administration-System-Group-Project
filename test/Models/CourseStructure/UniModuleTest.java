package Models.CourseStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UniModuleTest {

    private UniModule module;

    @BeforeEach
    public void init(){
        module = new UniModule("COM1009","Algorithm and Data Structures",20,1);
    }

    @Test
    public void addExistDeleteTest(){
        module.add();
        assertTrue(module.exists());
        module.remove();
        assertFalse(module.exists());
    }

}
