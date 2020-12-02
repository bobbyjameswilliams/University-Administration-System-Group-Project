package Models.Tables.Registrar;

public class InspectRegTableRow {

    private String moduleCode;
    private String moduleName;
    private int credits;


    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getCredits() {
        return credits;
    }

    public InspectRegTableRow(String moduleCode, String moduleName, int credits) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.credits = credits;
    }
}
