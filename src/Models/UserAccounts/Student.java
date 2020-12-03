package Models.UserAccounts;

import Models.CourseStructure.LevelOfStudy;
import Models.DatabaseBehaviours.DBController;
import Models.Tables.Registrar.InspectRegTableRow;
import Models.Tables.Registrar.RegistrarTableRow;
import Models.Tables.StudentGrade;

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
		return this.getRegNumber() +"','"+ this.getUsername() +"','" + this.getDegreeCode() + "','" + this.getLevelOfStudy();
	}
	public Object[] getStudentDetailsAsArrayForInserting() {
		Object[] studentDetails = new Object[4];
		studentDetails[0] = this.getRegNumber();
		studentDetails[1] = this.getUsername();
		studentDetails[2] = this.getDegreeCode();
		studentDetails[3] = this.getLevelOfStudy();
		return studentDetails;
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

	public void setLevelOfStudy(String levelOfStudy) {
	    this.levelOfStudy = LevelOfStudy.valueOf(levelOfStudy.toUpperCase());
	}

	public void updateLevelOfStudy(){
		try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

			PreparedStatement pstmt = con.prepareStatement("UPDATE Student SET yearOfStudy =? \n "+
																" WHERE regNumber =?;");
			pstmt.setString(1,this.getLevelOfStudy().toString());
			pstmt.setInt(2,this.getRegNumber());
			pstmt.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
		try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

			PreparedStatement pstmt = con.prepareStatement("SELECT credits FROM StudentModule JOIN Module " +
																"ON Module.moduleCode = StudentModule.moduleCode\n " +
																"WHERE regNumber =? AND StudentModule.levelOfStudyTaken =?;");

			pstmt.setInt(1,this.getRegNumber());
			pstmt.setString(2,this.getLevelOfStudy().toString());
			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()){
				creditsTaken += Integer.parseInt(rs.getString("credits"));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return creditsTaken;
	}

	public List<InspectRegTableRow> getAllModulesTaken(){
		String query = "SELECT Module.moduleCode, Module.moduleName, Module.credits, StudentModule.levelOfStudyTaken FROM Student\n" +
				" INNER JOIN StudentModule ON Student.regNumber = StudentModule.regNumber\n" +
				" INNER JOIN Module ON StudentModule.moduleCode = Module.moduleCode\n" +
				" WHERE Student.regNumber = ?;";
		System.out.println(query);
		List<InspectRegTableRow> inspectRegTableRows = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
			PreparedStatement pstmt = con.prepareStatement("SELECT Module.moduleCode, Module.moduleName, Module.credits, StudentModule.levelOfStudyTaken FROM Student\n" +
																" INNER JOIN StudentModule ON Student.regNumber = StudentModule.regNumber\n" +
																" INNER JOIN Module ON StudentModule.moduleCode = Module.moduleCode\n" +
																" WHERE Student.regNumber = ?;");
			pstmt.setInt(1,this.getRegNumber());

			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()){
				String moduleCode = rs.getString("moduleCode");
				String moduleName = rs.getString("moduleName");
				int credits = rs.getInt("credits");
				LevelOfStudy levelOfStudyTaken = LevelOfStudy.valueOf(rs.getString("levelOfStudyTaken"));
				inspectRegTableRows.add(new InspectRegTableRow(moduleCode,moduleName,credits,levelOfStudyTaken));
			}
			return inspectRegTableRows;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static boolean exist(String username){
		try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){

			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM User\n" +
																"WHERE username=? ;");
			pstmt.setString(1,username);


			ResultSet rs = pstmt.executeQuery();
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

	public List<StudentGrade> getModules() {

		List<StudentGrade> studentModuleGrades = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM StudentModule\n " +
																"WHERE regNumber =? ;");
			pstmt.setInt(1,this.getRegNumber());

			ResultSet rs =  pstmt.executeQuery();
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

		String tutorForeName;
		String tutorSurname;
		String tutorEmail;
		List<String> personalTutorInfo = new ArrayList<String>();
		try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
			PreparedStatement pstmt = con.prepareStatement("SELECT forename, surname, emailAddress FROM PersonalTutor " +
																"JOIN Employee ON PersonalTutor.employeeNumber = Employee.employeeNumber " +
																"JOIN User ON User.username = Employee.username\n " +
																"WHERE PersonalTutor.regNumber =?;");
			pstmt.setInt(1,this.getRegNumber());

			ResultSet rs =  pstmt.executeQuery();
			while(rs.next()){
				personalTutorInfo.add(rs.getString("forename"));
				personalTutorInfo.add(rs.getString("surname"));
				personalTutorInfo.add(rs.getString("emailAddress"));
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return personalTutorInfo;
	}

}