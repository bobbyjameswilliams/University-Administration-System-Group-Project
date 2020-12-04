package Models.Tables.Teacher.Row;

public class GraduateRableRow {

    private int regNumber;
    private String Achievement;
    private String Qualification;



    public GraduateRableRow(int regNumber, String achievement, String qualification) {
        this.regNumber = regNumber;
        Achievement = achievement;
        Qualification = qualification;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public String getAchievement() {
        return Achievement;
    }

    public String getQualification() {
        return Qualification;
    }
}
