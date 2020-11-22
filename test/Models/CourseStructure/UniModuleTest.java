package Models.CourseStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniModuleTest {

    private UniModule module;

    @BeforeEach
    public void init(){
        module = new UniModule("COM1003",20,1);
    }

    @Test
    public void addExistDeleteTest(){
        module.add();
        assertEquals(true,module.exists());
        module.remove();
        assertEquals(false,module.exists());
    }

}
