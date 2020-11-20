package Models;

import java.util.*;
import java.sql.*;

public class Student extends User {
	
	private int regNumber; 
	private int degreeCode; 
	private int levelOfStudy;

	public Student(String username,String forename,String surname,String emailAddress,int regNumber, int degreeCode, int levelOfStudy) {
		super(username, forename, surname, emailAddress);
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