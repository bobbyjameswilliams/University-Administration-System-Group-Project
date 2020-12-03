package Models.UserAccounts.Student;

import Models.CourseStructure.CompulsoryModule;
import Models.CourseStructure.LevelOfStudy;
import Models.CourseStructure.UniModule;
import Models.DatabaseBehaviours.DBController;
import Models.Tables.Registrar.InspectRegTableRow;
import Models.Tables.Registrar.RegistrarTableRow;
import Models.Tables.StudentGrade;
import Models.UserAccounts.User;

import java.sql.*;
import java.util.*;

public class Student extends User {
	
	private int regNumber; 
	private String degreeCode;
	private LevelOfStudy levelOfStudy;

	//for adding a new Student to the DB
	public Student(String forename,String surname){
		super(forename,surname);
		// wont have decided yet when being added by admin
		this.degreeCode = null;
		// Fair to assume that students start at Level 1
		this.levelOfStudy = LevelOfStudy.ONE;
	}

	public Student(String forename,String surname, String degreeCode, String levelOfStudy) {
		super(forename, surname);
		this.degreeCode = degreeCode;
		this.levelOfStudy = LevelOfStudy.valueOf(levelOfStudy.toUpperCase());
	}

	//for editing a Student that is already in the DB
	public Student(String username,String forename,String surname,String emailAddress,int regNumber, String degreeCode, String levelOfStudy) {
		super(username, forename, surname, emailAddress);
		this.regNumber = regNumber;
		this.degreeCode = degreeCode;
		this.levelOfStudy = LevelOfStudy.valueOf(levelOfStudy.toUpperCase());
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
		return getRegNumber() +"','"+ getUsername() +"','" + getDegreeCode() + "','" + getLevelOfStudy();
	}


	public int getRegNumber() {
		return regNumber;
	}

	public String getDegreeCode() {
		return degreeCode;
	}

	public LevelOfStudy getLevelOfStudy() {
		return levelOfStudy;
	}

	public void setLevelOfStudy(LevelOfStudy levelOfStudy) {
		this.levelOfStudy = levelOfStudy;
	}

	public LevelOfStudy getNextLevelOfStudy(){
		return LevelOfStudy.getNext(this.levelOfStudy);
	}

	public void updateLevelOfStudy() throws InsufficientCreditEnrollment,InsufficientGradeAttainment{
		if (this.metCredits() == false)	{
			throw new InsufficientCreditEnrollment();
		}
		if (this.metGrades() == false) {
			throw new InsufficientGradeAttainment();
		}
		LevelOfStudy nextLevel = this.getNextLevelOfStudy();
		if (nextLevel == this.getLevelOfStudy())	{
			return;
		}
		this.setLevelOfStudy(nextLevel);
		// getlevel will now return update level
		String query = "UPDATE Student SET levelOfStudy = '" + this.getLevelOfStudy().toString() + "' WHERE regNumber = '" + this.getRegNumber() + "';";
		DBController.executeCommand(query);
		this.autoEnroll();
	}

	public boolean metGrades(){
		List<StudentGrade> modules = this.getModules();
		for (StudentGrade module: modules){
			if (module.getGrade() < 40) return false;
		}
		return true;
	}

	public boolean metCredits(){
		return this.getCreditRequirements() == this.getCreditsTaken();
	}

	public int getCreditRequirements(){
		if (this.levelOfStudy == LevelOfStudy.P){
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
					"WHERE regNumber = '"+this.getRegNumber()+"' AND StudentModule.levelOfStudyTaken = '" + this.getLevelOfStudy().toString() + "';";
			ResultSet rs =  stmt.executeQuery(query);
			while(rs.next()){
				creditsTaken += Integer.parseInt(rs.getString("credits"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return creditsTaken;
	}

	public List<InspectRegTableRow> getAllModulesTaken(){
		String query  = "SELECT StudentModule.moduleCode,StudentModule.grade,Module.credits,StudentModule.levelOfStudyTaken," +
				"Module.moduleName FROM StudentModule JOIN Module ON StudentModule.moduleCode = Module.moduleCode" +
				" WHERE StudentModule.regNumber =" + this.getRegNumber() + ";";
		System.out.println(query);
		List<InspectRegTableRow> inspectRegTableRows = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
			Statement stmt = con.createStatement();
			ResultSet rs =  stmt.executeQuery(query);
			while(rs.next()){
				String moduleCode = rs.getString("moduleCode");
				String moduleName = rs.getString("moduleName");
				String grade = rs.getString("grade");
				int credits = rs.getInt("credits");
				LevelOfStudy levelOfStudyTaken = LevelOfStudy.valueOf(rs.getString("levelOfStudyTaken"));
				inspectRegTableRows.add(new InspectRegTableRow(moduleCode,moduleName,grade,credits,levelOfStudyTaken));
			}
			return inspectRegTableRows;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static boolean exist(String username){
		try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM Student WHERE username = '" + username + "';";
			ResultSet rs =  stmt.executeQuery(query);
			// if student doesn't exist
			return rs.isBeforeFirst();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean canGraduate() {
		return false;
	}

	public boolean canProgressToNextLevel() {
		return false;
	}

	public void enroll(UniModule module){
		String values = this.getRegNumber() + "','" + module.getCode() + "','" + this.getLevelOfStudy().toString();
		String query = "INSERT INTO StudentModule (regNumber,moduleCode,levelOfStudyTaken) VALUES ('" + values + "');";
		DBController.executeCommand(query);
	}

	public void autoEnroll(){
		List<CompulsoryModule> compulsoryModules = new CompulsoryModule().getAll(this.degreeCode,this.levelOfStudy);
		for (CompulsoryModule compulsoryModule : compulsoryModules){
			String values = this.getRegNumber() + "','" + compulsoryModule.getModuleCode() + "','" + this.getLevelOfStudy().toString();
			String query = "INSERT INTO StudentModule (regNumber,moduleCode,levelOfStudyTaken) VALUES ('" + values + "');";
			DBController.executeCommand(query);
		}
	}

	public List<StudentGrade> getModules() {
		String query = "SELECT * FROM StudentModule WHERE regNumber = " + this.getRegNumber();
		List<StudentGrade> studentModuleGrades = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String moduleCode = rs.getString("moduleCode");
				String levelOfStudyTaken = rs.getString("levelOfStudyTaken");
				int grade = rs.getInt("grade");
				int resit = rs.getInt("resit");
				studentModuleGrades.add(new StudentGrade(moduleCode,levelOfStudyTaken,grade, resit));
			}
		}
		catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return studentModuleGrades;
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
		String query = "SELECT forename, surname, emailAddress FROM PersonalTutor JOIN Employee ON PersonalTutor.employeeNumber = " +
				"Employee.employeeNumber JOIN User ON User.username = Employee.username WHERE PersonalTutor.regNumber = " + this.regNumber;
		Statement stmt = null;
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

}