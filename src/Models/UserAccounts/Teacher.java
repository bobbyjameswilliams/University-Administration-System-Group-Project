package Models.UserAccounts;

import Models.DatabaseBehaviours.DBController;
import Models.Tables.StudentGrade;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.*;
import java.util.*;

public class Teacher extends Employee {

    public Teacher (String username,String forename,String surname,String emailAddress,int employeeNumber){
        super(username, forename, surname, emailAddress, employeeNumber);
    }


	public List<StudentGrade> getGradesOfStudents() {
    	String query = "SELECT StudentModule.regNumber, forename, surname, grade, resit FROM StudentModule INNER JOIN TeachesModule ON " +
				"StudentModule.moduleCode = TeachesModule.moduleCode INNER JOIN Student ON Student.regNumber = StudentModule.regNumber " +
				"INNER JOIN User ON User.username = Student.username WHERE TeachesModule.employeeNumber = " + this.getEmployeeNumber() + ";";
		System.out.println(query);
		List<StudentGrade> studentGrades = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(this.url,this.user,this.password)){
			Statement stmt = con.createStatement();
			ResultSet rs =  stmt.executeQuery(query);
			while(rs.next()){
				int regNumber = rs.getInt("regNumber");
				String forename = rs.getString("forename");
				String surname = rs.getString("surname");
				int grade = rs.getInt("grade");
				int resit = rs.getInt("resit");
				studentGrades.add(new StudentGrade(regNumber,forename,surname,grade,resit));
			}
			// Count should never be greater than one, I believe
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return studentGrades;
    }


    /** 
     * Get the regNumbers for the tutees of a especific Teacher
     * @return a Result set with all regNumbers from the teacher's tutees
     * @throws SQLException
     */
    public ResultSet getTutees() throws SQLException {
    	    String query = "SELECT regNumber \n"+
    	    			   "FROM PersonalTutor \n" + 
    	    			   "WHERE employeeNumber='"+this.getEmployeeNumber()+"';";
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
     * Get the Module Code for the Module taught by a especific Teacher
     * @return a Result set with all regNumbers from the teacher's tutees
     * @throws SQLException
     */

    public ResultSet getModulesTaught() throws SQLException{
    	 String query = "SELECT moduleCode \n"+
  			   			"FROM TeachesModule \n" + 
  			   			"WHERE employeeNumber='"+this.getEmployeeNumber()+"';";
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

}


