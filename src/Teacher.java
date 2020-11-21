package Models;

import javax.xml.xpath.XPathEvaluationResult;
import java.sql.*;
import java.util.*;

public class Teacher extends Employee {
	
	
	
    public Teacher (String username,String forename,String surname,String emailAddress,int employeeNumber){
        super(username, forename, surname, emailAddress, employeeNumber);
        
    }
    /** 
     * Get the regNumbers for the tutees of a especific Teacher
     * @return a Result set with all regNumbers from the teacher's tutees
     * @throws SQLException
     */
    public ResultSet getTutees() throws SQLException {
    	    String query = "SELECT regNumber \n"+
    	    			   "FROM PersonalTutor \n" + 
    	    			   "WHERE employeeNumber='"+super.employeeNumber+"';";
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
     * Get the regNumbers for the tutees of a especific Teacher
     * @param int employee Number
     * @return a Result set with all regNumbers from the teacher's tutees
     * @throws SQLException
     */
    public ResultSet getTutees(int employeeNumber) throws SQLException {
	    String query = "SELECT regNumber \n"+
	    			   "FROM PersonalTutor \n" + 
	    			   "WHERE employeeNumber='"+employeeNumber+"';";
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
  			   			"WHERE employeeNumber='"+super.employeeNumber+"';";
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
     * @param int employee Number
     * @return a Result set with all regNumbers from the teacher's tutees
     * @throws SQLException
     */

    public ResultSet getModulesTaught(int employeeNumber) throws SQLException{
    	 String query = "SELECT moduleCode \n"+
  			   			"FROM TeachesModule \n" + 
  			   			"WHERE employeeNumber='"+employeeNumber+"';";
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
     * Edit the student grade by providing the regNumber of the student, the module code and the value to which the grade is to be updated
     * @param int regNumber ,The registrared Number for the student for which the grade will be edited
     * @param String moduleCode, The module code for the module for where the grade will be edited
     * @param int newGrade, The new value to be assign to the grade
     * @return 0 or 1 
     * @throws SQLException
     */
    public int editStudentGrade(int regNumber,String moduleCode, int newGrade) throws SQLException{
   	 	String query = "UPDATE StudentModule \n" + 
   	 				   "SET Grade = ?\n" + 
   	 				   "WHERE (regNumber = ? AND moduleCode=?) ;";
   	 	
   	 	
   	 	try(PreparedStatement pstmt  = DBController.getConnection().prepareStatement(query)) {
   	 		
   	 	pstmt.setInt(1, newGrade);
   	 	pstmt.setInt(2, regNumber);
   	 	pstmt.setString(3, moduleCode);
   	 	int count = pstmt.executeUpdate();

   	 	return count;
   	 		
   	 	}
   	 	catch (SQLException e) {
   	 		e.printStackTrace();
   	 	}
   	 	
    	return 0;

    }
    
    /** 
     * Edit the student grade by providing the regNumber of the student, the module code and the value to which the grade is to be updated
     * @param Set<Integer> regNumbers ,Set of regNumbers for every grade to be edited
     * @param Set<String> moduleCodes, Set of module codes for every grade to be edited
     * @param Set<Integer> newGrades, Set of Grades to be Edited
     * @return count of lines Updated 
     * @throws SQLException
     */
    public int editStudentGradeInBulk(Set<Integer> regNumbers ,Set<String> moduleCodes, Set<Integer> newGrades ) throws SQLException{
    	
    	Collection<String> queries = new ArrayList<String>();
        Iterator<Integer> regNumbersIt = regNumbers.iterator();
        Iterator<Integer> newGradesIt = newGrades.iterator();
        Iterator<String> moduleCodesIt = moduleCodes.iterator();
        while(regNumbersIt.hasNext() && newGradesIt.hasNext() && moduleCodesIt.hasNext()){
           
        	String query = "UPDATE StudentModule \n" + 
   	 				   	   "SET Grade = "+newGradesIt.next()+"\n" + 
   	 				       "WHERE (regNumber = "+regNumbersIt.next()+"AND moduleCode="+moduleCodesIt.next()+";";
        	queries.add(query);
        }
        Iterator<String> queriesIt = queries.iterator();
        int count = 0;
        while(queriesIt.hasNext()) {
            Statement stmt = null;
       	 	try {
       	 	    stmt = DBController.getConnection().createStatement();
       	 	    count += stmt.executeUpdate(queriesIt.next());
       	 	}
       	 	catch (SQLException e) {
       	 		e.printStackTrace();
       	 	}finally {
       	 		if (stmt != null) stmt.close();
       	 	}
        }
   	    
   	 	
    	return count;

    }
    
}


