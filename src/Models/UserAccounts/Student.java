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
	public Student(String forename,String surname, String degreeCode, String levelOfStudy) {
		super(forename, surname);
		this.regNumber = generateNewRegNumber();
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

	public LevelOfStudy getLevelOfStudy() {
		return levelOfStudy;
	}

	public void setLevelOfStudy(String levelOfStudy) {
	    this.levelOfStudy = LevelOfStudy.valueOf(levelOfStudy.toUpperCase());
	}

	public void updateLevelOfStudy(){
		try (Connection con = DriverManager.getConnection(this.url,this.user,this.password)){
			Statement stmt = con.createStatement();
			String query = "UPDATE Student SET yearOfStudy = " + this.getLevelOfStudy().toString() + " WHERE regNumber = " + this.getRegNumber();
			stmt.execute(query);
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
		try (Connection con = DriverManager.getConnection(this.url,this.user,this.password)){
			Statement stmt = con.createStatement();
			String query = "SELECT credits FROM StudentModule JOIN Module ON Module.moduleCode = StudentModule.moduleCode " +
					"WHERE regNumber = '"+this.getRegNumber()+"' AND StudentModule.levelOfStudyTaken = '" + this.getLevelOfStudy().toString() + "';";
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

	public List<InspectRegTableRow> getAllModulesTaken(){
		String query = "SELECT Module.moduleCode, Module.moduleName, Module.credits FROM Student\n" +
				" INNER JOIN StudentModule ON Student.regNumber = StudentModule.regNumber\n" +
				" INNER JOIN Module ON StudentModule.moduleCode = Module.moduleCode\n" +
				" WHERE Student.regNumber = "+ this.getRegNumber() ;
		System.out.println(query);
		List<InspectRegTableRow> inspectRegTableRows = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(DBController.url,DBController.user,DBController.password)){
			Statement stmt = con.createStatement();
			ResultSet rs =  stmt.executeQuery(query);
			while(rs.next()){
				String moduleCode = rs.getString("moduleCode");
				String moduleName = rs.getString("moduleName");
				int credits = rs.getInt("credits");
				inspectRegTableRows.add(new InspectRegTableRow(moduleCode,moduleName,credits));
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

}