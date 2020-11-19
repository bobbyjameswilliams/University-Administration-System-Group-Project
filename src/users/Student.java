package users;

public class Student extends User {
	
	private int regNumber; 
	private int degreeCode; 
	private int levelOfStudy;
	
	public Student(String username,String forename,String surname,String emailadress,int regNumber, int degreeCode, int levelOfStudy) {
		super(username, forename, surname, emailadress);
		 this.regNumber = regNumber;
		 this.degreeCode = degreeCode;
		 this.levelOfStudy = levelOfStudy;
	}
	
	public int getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(int newRegNumber) {
	    this.regNumber = newRegNumber;
	  }
	
	public int getDegreeCode() {
		return degreeCode;
	}
	public void setDegreeCode(int newDegreeCode) {
	    this.degreeCode = newDegreeCode;
	  }
	
	public int getLevelOfStudy() {
		return levelOfStudy;
	}
	public void setLevelOfStudy(int newLevelOfStudy) {
	    this.levelOfStudy = newLevelOfStudy;
	  }
	
	
}