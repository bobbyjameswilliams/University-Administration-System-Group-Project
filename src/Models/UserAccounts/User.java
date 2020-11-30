package Models.UserAccounts;
public class User {
	
	private String username;
	private String forename;
	private String surname;
	private String emailAddress;

	protected final String url = "jdbc:mysql://stusql.dcs.shef.ac.uk/team045";
	protected final String user = "team045" ;
	protected final String password = "5e15b333";

	public User(String username,String forename,String surname,String emailAddress) {
		this.username = username;
		this.forename = forename;
		this.surname = surname;
		this.emailAddress = emailAddress;
	}
	
	public String getUsername() {
		return username;
	}
	protected void setUsername(String username) {
	    this.username = username;
	  }
	
	public String getSurname() {
		return surname;
	}
	protected void setSurname(String surname) {
	    this.surname = surname;
	  }
	
	public String getForename() {
		return forename;
	}
	protected void setForename(String forename) {
	    this.forename = forename;
	  }
	
	public String getEmailAddress() {
		return emailAddress;
	}
	protected void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	}

	public String getUserDetails() {
		return getUsername() + "','" + getForename() + "','" + getSurname() + "','" + getEmailAddress();
	}

}