package Models.Tables.Registrar;

import Models.CourseStructure.LevelOfStudy;

public class InspectRegTableRow {

    private String moduleCode;
    private String moduleName;
    private int credits;

    public LevelOfStudy getLevelOfStudyTaken() {
        return levelOfStudyTaken;
    }

    private LevelOfStudy levelOfStudyTaken;

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getCredits() {
        return credits;
    }

    public InspectRegTableRow(String moduleCode, String moduleName, int credits, LevelOfStudy levelOfStudyTaken) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.credits = credits;
        this.levelOfStudyTaken = levelOfStudyTaken;
    }

}
