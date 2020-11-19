package users;



public class Employee extends User   {
	
	private int employeeNumber;
	private EmployeeRole role;
	
	public Employee(String username,String forename,String surname,String emailadress ,int employeeNumber, String role ) {
		super( username, forename, surname, emailadress);
		this.employeeNumber = employeeNumber;
		this.role = EmployeeRole.fromRoleName(role);
	}
	public Employee(String username,String forename,String surname,String emailadress ,int employeeNumber, EmployeeRole role ) {
		super( username, forename, surname, emailadress);
		this.employeeNumber = employeeNumber;
		this.role = role;
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