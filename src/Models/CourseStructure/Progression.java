package Models.CourseStructure;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.math.BigDecimal;
import Models.DatabaseBehaviours.DBController;
import Models.CourseStructure.Degree;
import Models.DatabaseBehaviours.UserManipulator;
import Models.UserAccounts.*;

public class Progression {

    private static final int UNDERGRAD_CREDITS = 120;
    private static final int POSTGRAD_CREDITS = 180;

    private static final float LEVELS_ONE_TO_THREE_PASS = 39.5F;
    private static final float LEVEL_FOUR_PASS = 49.5F;

    private Student student;
    private UserManipulator studentDB;
}

