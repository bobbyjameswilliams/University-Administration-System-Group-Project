package Models.UserAccounts;

import Models.DatabaseBehaviours.DBController;
import Models.Tables.StudentGrade;

import java.sql.*;
import java.util.*;

public class Student extends User {
	
	private int regNumber; 
	private String degreeCode;
	private int levelOfStudy;
	//for adding a new Student to the DB
	public Student(String forename,String surname, String degreeCode, int levelOfStudy) {
		super(forename, surname);
		this.regNumber = generateNewRegNumber();
		this.degreeCode = degreeCode;
		this.levelOfStudy = levelOfStudy;
	}
	//for editing a Student that is already in the DB
	public Student(String username,String forename,String surname,String emailAddress,int regNumber, String degreeCode, int levelOfStudy) {
		super(username, forename, surname, emailAddress);
		this.regNumber = regNumber;
		this.degreeCode = degreeCode;
		this.levelOfStudy = levelOfStudy;
	}
	/**
	 * get information about the student including their user details
	 */
	public String getStudentDetails() {
		return super.getUserDetails()+ "','" + getRegNumber() + "','" + getDegreeCode() + "','"+
		       getLevelOfStudy();
	}
	/**
	 * get information for adding a new student so
	 */
	public String getStudentDetailsForInserting() {
		return getRegNumber() +"','"+ getUsername() +"','" + getDegreeCode() + "','"+
				getLevelOfStudy();
	}


	public int getRegNumber() {
		return regNumber;
	}

	public int generateNewRegNumber(){
		int genaratedRegnumber=0;
		try (Connection con = DriverManager.getConnection(this.url,this.user,this.password)){

			String query = "SELECT COUNT(regNumber) \n"+
					       "FROM  Student;";
			Statement stmt = con.createStatement();
			ResultSet rs =  stmt.executeQuery(query);
			rs.next();
			genaratedRegnumber = rs.getInt(1)+1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return genaratedRegnumber;
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

	public void updateLevelOfStudy(){
		try (Connection con = DriverManager.getConnection(this.url,this.user,this.password)){
			Statement stmt = con.createStatement();
			String query = "UPDATE Student SET yearOfStudy = " + this.getLevelOfStudy() + " WHERE regNumber = " + this.getRegNumber();
			stmt.execute(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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

	public List<StudentGrade> getModules() {
		String query = "SELECT * FROM StudentModule WHERE regNumber = " + this.getRegNumber();
		List<StudentGrade> studentModuleGrades = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String moduleCode = rs.getString("moduleCode");
				int grade = rs.getInt("grade");
				int resit = rs.getInt("resit");
				studentModuleGrades.add(new StudentGrade(moduleCode,grade, resit));
			}
		}
		catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return studentModuleGrades;
	}

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
	 * @param levelOfStudy, the level of study of the student
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
	 * @param regNumber, The number of the student
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

	//Get the personal tutor ID to then query for the email and name.
	//Set this at the property for the personal tutor name and email
	//so it can then be got and displayed on the student
	//welcome screen.
	public List<String> getPersonalTutor() throws SQLException {
		String query = "SELECT forename, surname, emailAddress FROM PersonalTutor JOIN Employee ON PersonalTutor.employeeNumber = Employee.employeeNumber JOIN User ON User.username = Employee.username WHERE PersonalTutor.regNumber = " + this.regNumber;
		Statement stmt = null;
		String tutorForeName;
		String tutorSurname;
		String tutorEmail;
		List<String> personalTutorInfo = new ArrayList<String>();
		try {
			stmt = DBController.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				personalTutorInfo.add(rs.getString("forename"));
				personalTutorInfo.add(rs.getString("surname"));
				personalTutorInfo.add(rs.getString("emailAddress"));
			}
			return personalTutorInfo;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) stmt.close();
		}
		return null;
	}



	public ResultSet getYearlyGrades() {
		return null;
	}


}