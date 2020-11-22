package Models.UserAccounts;

public abstract class Employee extends User   {
	
	private int employeeNumber;

	public Employee(String username,String forename,String surname,String emailAddress,int employeeNumber){
		super(username, forename, surname, emailAddress);
		this.employeeNumber = employeeNumber;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

}