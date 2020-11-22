package Models.UserAccounts;

import java.sql.*;

public class Student extends User {
	
	private int regNumber; 
	private String degreeCode;
	private int levelOfStudy;

	public Student(String username,String forename,String surname,String emailAddress,int regNumber, String degreeCode, int levelOfStudy) {
		super(username, forename, surname, emailAddress);
		this.regNumber = regNumber;
		this.degreeCode = degreeCode;
		this.levelOfStudy = levelOfStudy;
	}

	public int getRegNumber() {
		return regNumber;
	}

	public String getDegreeCode() {
		return degreeCode;
	}

	public int getLevelOfStudy() {
		return levelOfStudy;
	}

	public void setLevelOfStudy(int newLevelOfStudy) {
	    this.levelOfStudy = newLevelOfStudy;
	}

	public int getCreditRequirements(){
		if (this.levelOfStudy > 3 ){
			// post grad
			return 180;
		}
		// undergrad
		return 120;
	}

	public int getCreditsTaken(){
		int creditsTaken = 0;
		try (Connection con = DriverManager.getConnection(this.url,this.user,this.password)){
			Statement stmt = con.createStatement();
			String query = "SELECT credits FROM StudentModule JOIN Module ON Module.moduleCode = StudentModule.moduleCode " +
					"WHERE regNumber = '"+this.getRegNumber()+"' AND levelOfStudy = '" + this.getLevelOfStudy() + "';";
			ResultSet rs =  stmt.executeQuery(query);
			while(rs.next()){
				creditsTaken += Integer.parseInt(rs.getString("credits"));
			}
			// Count should never be greater than one, I believe
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return creditsTaken;
	}


	public boolean canGraduate() {
		return false;
	}

	public boolean canProgressToNextLevel() {
		return false;
	}

	public ResultSet getModules() {
		return null;
	}

	public ResultSet getPersonalTutor() {
		return null;
	}

	public ResultSet getYearlyGrades() {
		return null;
	}

}