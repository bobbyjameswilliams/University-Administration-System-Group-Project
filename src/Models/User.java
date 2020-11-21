package Models;
public class User {
	
	private String username;
	private String forename;
	private String surname;
	private String emailAddress;
	
	public User(String username,String forename,String surname,String emailAddress) {
		this.username = username;
		this.forename = forename;
		this.surname = surname;
		this.emailAddress = emailAddress;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
	    this.username = username;
	  }
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
	    this.surname = surname;
	  }
	
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
	    this.forename = forename;
	  }
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	}

	public String getUserDetails() {
		return getUsername() + "','" + getForename() + "','" + getSurname() + "','" + getEmailAddress();
	}

	protected int boolToInt(boolean x){
		return x ? 1 : 0 ;
	}
}