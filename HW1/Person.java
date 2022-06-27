import java.util.Calendar;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
STUDENT ID: 150121822
PURPOSE: This project aims to construct a department, managers, employees and customers and print 
whole informations about them depending on input file located in the same directory path. 
*/
public class Person {
	private int id;
	private String firstName;
	private String lastName;
	private byte gender;
	private java.util.Calendar birthDate;
	private byte maritalStatus;
	private boolean hasDriverLicence;
	// min-3-character necessity has been realized in methods. 
	// try-catch block with exceptions have been issued in methods.
	// Parameterized constructor
	public Person(int id, String firstName, String lastName, String gender, 
		java.util.Calendar birthDate, String maritalStatus, String hasDriverLicence) throws Exception {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setMaritalStatus(maritalStatus);
		this.setGender(gender);
		this.setBirthDate(birthDate);
		this.setHasDriverLicence(hasDriverLicence);
		
	}
	// Getter-setter methods
	public String getGender() {
		if(gender==1) return "Man";
		
		return "Woman";
	}
	// Sets gender(byte) attribute depending on gender(String) parameter fetched by method.
	public void setGender(String gender) throws Exception  {

		if(gender.length()<3) throw new Exception("Length of gender can't be less than 3 "
					+ "characters");
		if(gender.equals("Man")) this.gender=1;
		else if(gender.equals("Woman")) this.gender=2;


		
	}
	// Returns marital status circumstance as a string depending on maritalstatus(byte) attribute.
	public String getMaritalStatus() {
		if(maritalStatus==1) return "Single";
		else if(maritalStatus==2) return "Married";
		return null;
	}
	// Sets marital status(byte) attribute depending on maritalstatus(String) variable fetched by method.
	public void setMaritalStatus(String maritalStatus) throws Exception{

		if(maritalStatus.length()<3) throw new Exception("Length of maritalStatus can't be"+
					" less than 3 characters.");
		if(maritalStatus.equals("Single")) this.maritalStatus=1;
		else if(maritalStatus.equals("Married")) this.maritalStatus=2;

	}
	// Returns hasDriverLicence as a string depending on hasDriverlicence(byte) attribute.
	public String getHasDriverLicence() {
		if(hasDriverLicence==true) return "Yes";
		else if(hasDriverLicence==false) return "No";
		return null;
	}
	// Sets hasDriverLicence attribute depending on hasDriverlicence(String) variable fetched by method.
	public void setHasDriverLicence(String hasDriverLicence) {
		if(hasDriverLicence.equals("Yes")) this.hasDriverLicence=true;
		else if(hasDriverLicence.equals("No")) this.hasDriverLicence=false;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws Exception{
		if(firstName.length()<3) throw new Exception("Length of firstName can't be less than "
					+ "3 characters.");
		else this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws Exception{

		if(lastName.length()<3) throw new Exception("Length of lastName can't be less than"
					+ "3 characters");
		else this.lastName = lastName;

	}

	public java.util.Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.util.Calendar birthDate) {
		this.birthDate = birthDate;
	}
	// Overriden toString method
	@Override
	public String toString() {
		return "Person [id="+getId()+", firstName="+getFirstName()
		+", lastName="+getLastName()+", gender="
		+getGender()+", birthDate="
		+ getBirthDate().get(Calendar.DAY_OF_MONTH)+"/"+(getBirthDate().get(Calendar.MONTH)+1)+"/"+getBirthDate().get(Calendar.YEAR)+", maritalStatus="+
		getMaritalStatus()+", hasDriverLicence="+getHasDriverLicence()+"]";
	}
}
