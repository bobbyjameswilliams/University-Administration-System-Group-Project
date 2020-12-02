package Models.Tables.Registrar;



public class RegistrarTableRow {
private int regNumber;
private String userName;
private String degreeCode;
private String levelOfStudy;
private String foreName;
private String surName;
private String email;

    public int getRegNumber() {
        return regNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getDegreeCode() {
        return degreeCode;
    }

    public String getLevelOfStudy() {
        return levelOfStudy;
    }

    public String getForeName() {
        return foreName;
    }

    public String getSurName() {
        return surName;
    }

    public String getEmail() {
        return email;
    }

    public RegistrarTableRow(int regNumber, String userName, String degreeCode, String levelOfStudy, String foreName, String surName, String email) {
        this.regNumber = regNumber;
        this.userName = userName;
        this.degreeCode = degreeCode;
        this.levelOfStudy = levelOfStudy;
        this.foreName = foreName;
        this.surName = surName;
        this.email = email;
    }
}
