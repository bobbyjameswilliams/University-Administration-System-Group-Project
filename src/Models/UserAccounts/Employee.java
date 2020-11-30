package Models.UserAccounts;

public abstract class Employee extends User   {
	
	private int employeeNumber;

	// dummy constructor
	public Employee(){
		super("","","","");
		this.employeeNumber = 0;
	}

	public Employee(String username,String forename,String surname,String emailAddress,int employeeNumber){
		super(username, forename, surname, emailAddress);
		this.employeeNumber = employeeNumber;
	}
	public int getEmployeeNumber() {
		return employeeNumber;
	}

	protected void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public boolean equals(Employee employee){
		return this.getUsername().equals(employee.getUsername()) && this.getForename().equals(employee.getForename()) &&
				this.getEmployeeNumber() == employee.getEmployeeNumber();
	}

}