package Models.CourseStructure;

public enum LevelOfStudy {

    ONE,
    TWO,
    THREE,
    P;

    public static LevelOfStudy[] getAllLevelsOfStudies(){
        LevelOfStudy[] x = {ONE,TWO,THREE,P};
        return x;
    }

}

