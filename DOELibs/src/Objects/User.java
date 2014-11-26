package Objects;

public class User {
	public int userId;
	public String firstName;
	public String lastName;
	public String email;
	public boolean isStaff;
	public String validationCode;
	public String manuelSettings;
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public boolean isLoggedIn() {
		return validationCode != null;
	}
}