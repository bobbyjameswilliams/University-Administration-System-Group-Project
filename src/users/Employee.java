package users;

import java.util.*;
import java.sql.*;
import uni.Module;



public class Employee extends User   {
	
	private int employeeNumber;
	private EmployeeRole role;
	private Collection<Module> teachesModules;
	
	/* Constructers */
	public Employee(String username,String forename,String surname,String emailadress ,int employeeNumber, String role ) {
		super( username, forename, surname, emailadress);
		this.employeeNumber = employeeNumber;
		this.role = EmployeeRole.fromRoleName(role);
		if (this.role == EmployeeRole.TEACHER) {
			this.teachesModules = importTeachedModulesFromDB(employeeNumber);
		}
		else {
			this.teachesModules= null;
		}
	}
	
	public Employee(String username,String forename,String surname,String emailadress ,int employeeNumber, EmployeeRole role ) {
		super( username, forename, surname, emailadress);
		this.employeeNumber = employeeNumber;
		this.role = role;
		if (this.role == EmployeeRole.TEACHER) {
			this.teachesModules = importTeachedModulesFromDB(employeeNumber);
		}
		else {
			this.teachesModules= null;
		}
	}
	
	//import the Modules which have the atribute employeeNumber equal to the Employee created
	
	private Collection<Module> importTeachedModulesFromDB(int employeeNumber) {
		
		return null;
	}
	
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(int newEmployeeNumber) {
	    this.employeeNumber = newEmployeeNumber;
	  }
	
	public EmployeeRole getRole() {
		return role;
	}
	public void setRoleAsEmployeeRole(EmployeeRole newRole) {
	    this.role = newRole;
	  }
	
	public String getRoleAsString() {
		return role.getEmployeeRoleName();
	}
	public void setRoleAsAString(String newRole) {
	    this.role = EmployeeRole.fromRoleName(newRole);
	  }
}