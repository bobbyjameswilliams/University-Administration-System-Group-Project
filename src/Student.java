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
	 /** 
     * Get the Modules Codes for the Modules of the current valueu assign to the Level of Study
     * @return a Result set with all Modules Codes with the same level of study as the student
     * @throws SQLException
     */
	public ResultSet getModulesOfCurrentLevelOfStudyOfStudent() throws SQLException {
		String query = "SELECT S.moduleCode \n"+
		   			   "FROM  StudentModule S INNER JOIN Module M \n"+
		   			   "ON S.moduleCode = M.moduleCode" + 
		   			   "WHERE (regNumber='"+this.regNumber+"' AND levelOfStudy = '"+this.levelOfStudy+"');";
		
		Statement stmt = null;
		try {
			stmt = DBController.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) stmt.close();
		}
		return null;
	}
	/** 
     * Get the Modules Codes for the Modules of the student assigned to the provided regNumber and to the Level of Study
     * @param int regNumber, The number of the student
     * @param int levelOfStudy, the level of study of the student
     * @return a Result set with all Modules Codes with the same level of study as the student
     * @throws SQLException
     */
	public ResultSet getModulesOfCurrentLevelOfStudyOfStudent(int regNumber, int levelOfStudy) throws SQLException {
		String query = "SELECT S.moduleCode \n"+
		   			   "FROM  StudentModule S INNER JOIN Module M \n"+
		   			   "ON S.moduleCode = M.moduleCode" + 
		   			   "WHERE (regNumber='"+regNumber+"' AND levelOfStudy = '"+levelOfStudy+"');";
		
		Statement stmt = null;
		try {
			stmt = DBController.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) stmt.close();
		}
		return null;
	}
	/** 
     * Get the Modules Codes for the Modules of the provided  Level of Study
     * @param int levelOfStudy, the level of study of the student
     * @return a Result set with all Modules Codes with the same level of study 
     * @throws SQLException
     */
	public ResultSet getModulesOfLevelOfStudy(int levelOfStudy) throws SQLException {
		String query = "SELECT S.moduleCode \n"+
		   			   "FROM  StudentModule S INNER JOIN Module M \n"+
		   			   "ON S.moduleCode = M.moduleCode" + 
		   			   "WHERE (levelOfStudy = '"+levelOfStudy+"');";
		
		Statement stmt = null;
		try {
			stmt = DBController.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) stmt.close();
		}
		return null;
	}
	/** 
     * Get the Modules Codes for the Modules assigned to a student
     * @return a Result set with all Modules Codes assigned to the student
     * @throws SQLException
     */
	public ResultSet getAllModules() throws SQLException {
		String query = "SELECT moduleCode \n"+
		   			   "FROM StudentModule \n" + 
		   			   "WHERE regNumber='"+this.regNumber+"';";
		Statement stmt = null;
		try {
			stmt = DBController.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) stmt.close();
		}
		return null;
	}
	/** 
     * Get the Modules Codes for the Modules of a specific Student(regNumber)
     * @param int regNumber, The number of the student
     * @return a Result set with all Modules Codes assigend to the provided regNumber
     * @throws SQLException
     */
	public ResultSet getAllModules(int regNumber) throws SQLException {
		String query = "SELECT moduleCode \n"+
		   			   "FROM StudentModule \n" + 
		   			   "WHERE regNumber='"+regNumber+"';";
		Statement stmt = null;
		try {
			stmt = DBController.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) stmt.close();
		}
		return null;
	}
	/** 
     * Get the personal tutor(s) for a Student
     * @return a Result set of the tutor(s) assigned to the student
     * @throws SQLException
     */
	public ResultSet getPersonalTutor() throws SQLException {
	    String query = "SELECT employeeNumber \n"+
 			   			"FROM PersonalTutor \n" + 
 			   			"WHERE regNumber='"+this.regNumber+"';";
	    Statement stmt = null;
	    try {
	    	stmt = DBController.getConnection().createStatement();
	    	ResultSet rs = stmt.executeQuery(query);
	    	return rs;
	    }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	if (stmt != null) stmt.close();
		}
		return null;
	}
	/** 
     * Get the personal tutor(s) for a Student
     * @return a Result set of the tutor(s) assigned to the student
     * @throws SQLException
     */
	public ResultSet getPersonalTutor(int regNumber) throws SQLException {
	    String query = "SELECT employeeNumber \n"+
 			   			"FROM PersonalTutor \n" + 
 			   			"WHERE regNumber='"+regNumber+"';";
	    Statement stmt = null;
	    try {
	    	stmt = DBController.getConnection().createStatement();
	    	ResultSet rs = stmt.executeQuery(query);
	    	return rs;
	    }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    }finally {
	    	if (stmt != null) stmt.close();
		}
		return null;
	}

	public ResultSet getYearlyGrades() {
		return null;
	}

}