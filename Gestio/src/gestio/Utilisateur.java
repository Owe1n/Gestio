package gestio;

public class Utilisateur {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String imageUserPath;
	private int autorithyLevel;
	
	public Utilisateur(String firstName, String lastName, String email, String password, String imageUserPath, int autorithyLevel){
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.imageUserPath = imageUserPath;
		this.autorithyLevel = autorithyLevel;
	}
	public Utilisateur(){
	
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImageUserPath() {
		return imageUserPath;
	}
	public void setImageUserPath(String imageUserPath) {
		this.imageUserPath = imageUserPath;
	}
	public int getAutorithyLevel() {
		return autorithyLevel;
	}
	public void setAutorithyLevel(int autorithyLevel) {
		this.autorithyLevel = autorithyLevel;
	}

	
}
