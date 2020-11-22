package Models.UserAccounts;

public enum EmployeeRole{

	ADMIN("Admin"), 
	REGISTRAR("Registrar"),
	TEACHER("Teacher");

	private String employeeRole;

	EmployeeRole (String employeeRole) {
			this.employeeRole = employeeRole;
		}
		
		public String getEmployeeRoleName() {
			return employeeRole;
		}

		/**
		 * Get a EmployeeRole from the specified name.
		 * @param employeeRole the name of the EmployeeRole to get. These are the values in the brackets next to the enum value declared above.
		 * @return The corresponding EmployeeRole
		 */
		public static EmployeeRole fromRoleName(String employeeRole) {
			
			for (EmployeeRole r : EmployeeRole.values()) {
				if (r.getEmployeeRoleName().toLowerCase().equals(employeeRole.toLowerCase()))
					return r;
			}
			return null;
		}
	}

