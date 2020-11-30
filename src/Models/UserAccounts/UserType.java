package Models.UserAccounts;

public enum UserType {

	ADMIN("Admin"),
	REGISTRAR("Registrar"),
	TEACHER("Teacher"),
	STUDENT("Student");

	private String userType;

	UserType(String userType) {
		this.userType = userType;
	}

	public String getEmployeeRoleName() {
		return userType;
	}

}

