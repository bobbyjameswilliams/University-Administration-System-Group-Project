package users;
public class User {
	
	private String username;
	private String forename;
	private String surname;
	private String emailadress;
	
	public User(String username,String forename,String surname,String emailadress) {
		this.username = username;
		this.forename = forename;
		this.surname = surname;
		this.emailadress = emailadress;
				
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String newUsername) {
	    this.username = newUsername;
	  }
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String newSurname) {
	    this.surname = newSurname;
	  }
	
	public String getForename() {
		return forename;
	}
	public void setForename(String newForename) {
	    this.forename = newForename;
	  }
	
	public String getEmailadress() {
		return emailadress;
	}
	public void setEmailadress(String newEmailadress) {
	    this.emailadress = newEmailadress;
	  }
	
}