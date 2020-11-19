package uni;

import java.util.*;
import java.sql.*;


public class Degree{
	
	private String degreeCode;
	private String courseName;
	private int lenghtOfStudy;
	private int yearInIndustry;
	private Collection<Module> allDegreeModules;
	
	public Degree(String degreeCode, String courseName, int lenghtOfStudy, int yearInIndustry ) {
		this.degreeCode = degreeCode;
		this.courseName = courseName;
		this.lenghtOfStudy = lenghtOfStudy; 
		this.yearInIndustry = yearInIndustry;
		this.allDegreeModules = importModulesFromDB(degreeCode, courseName);
	}
	
	public Degree(String degreeCode, String courseName, int lenghtOfStudy, int yearInIndustry,Collection<Module> allDegreeModules) {
		this.degreeCode = degreeCode;
		this.courseName = courseName;
		this.lenghtOfStudy = lenghtOfStudy; 
		this.yearInIndustry = yearInIndustry;
		this.allDegreeModules = allDegreeModules;
		
	}
	public Collection<Module> importModulesFromDB(String degreeCode, String courseName){
		Collection<Degree> departmentsDegrees = new ArrayList<Degree>();
		return null;
	}
}