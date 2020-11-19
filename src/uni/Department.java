package uni;

import java.util.*;
import java.sql.*;

public class Department{
	
	private String departmentName;
	private Collection<Degree> allDepartmentDegrees;
	
	public Department(String departmentName,Collection<Degree> allDepartmentDegrees) {
		
		this.departmentName = departmentName;
		this.allDepartmentDegrees = allDepartmentDegrees;
		
	}
	
	public Department(String departmentName) {
		
		this.departmentName = departmentName;
		this.allDepartmentDegrees = importDegreesFromDB(departmentName);
		
	}
	public Collection<Degree> importDegreesFromDB(String departmentName){
		Collection<Degree> departmentsDegrees = new ArrayList<Degree>();
		return departmentsDegrees;
	}
	
	public void addNewDepartmentToDB() {
		
		
	}
	
}