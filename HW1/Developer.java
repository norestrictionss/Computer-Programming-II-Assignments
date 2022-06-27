import java.util.ArrayList;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
STUDENT ID: 150121822
PURPOSE: This project aims to construct a department, managers, employees and customers and print 
whole informations about them depending on input file located in the same directory path. 
*/
import java.util.Calendar;
public class Developer extends RegularEmployee {
	private ArrayList<Project> projects;
	public static int numberOfDevelopers;
	// Parameterized constructor
	public Developer(int id, String firstName, String lastName, 
			String gender, Calendar birthDate, String maritalStatus,
			String hasDriverLicence, double salary, Calendar hireDate,
			Department department, double pScore, ArrayList<Project> p) throws Exception {
		
			super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence,
				  salary, hireDate, department, pScore);
			this.projects=p;
			Developer.numberOfDevelopers++;
	}
	// Overloaded constructor
	public Developer(RegularEmployee re, ArrayList<Project> p) throws Exception {
		super(re.getId(), re.getFirstName(), re.getLastName(), re.getGender(),
			 re.getBirthDate(), re.getMaritalStatus(), re.getHasDriverLicence(), 
			 re.getSalary(), re.getHireDate(), re.getDepartment(), re.getPerformanceScore());
			this.projects=p;
			Developer.numberOfDevelopers++;
	}
	// Getter-setter methods
	public ArrayList<Project> getProjects() {
		return projects;
	}
	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}
	public static int getNumberOfDevelopers() {
		return numberOfDevelopers;
	}
	public static void setNumberOfDevelopers(int numberOfDevelopers) {
		Developer.numberOfDevelopers = numberOfDevelopers;
	}
	// This method add project fetched by method to projects arraylist.
	public boolean addProject(Project s) {
		return projects.add(s);
	}
	// This method removes project fetched by a method from projects arraylist.
	public boolean removeProject(Project s) {
		return projects.remove(s);
	}
	// Overriden toString method
	@Override
	public String toString() {
		return "Developer\n 				Person Info [id="+getId()+", firstName="+getFirstName()+
		", lastName="+getLastName()+", gender="+getGender()
		+"]\n				Employee Info [salary="+getSalary()+ ", hireDate=" +getHireDate().get(Calendar.DAY_OF_MONTH)+"/"+(getHireDate().get(Calendar.MONTH)+1)+
		"/"+getHireDate().get(Calendar.YEAR)+"]"+"\n"
		+"				RegularEmployee Info [performanceScore="+getPerformanceScore()+", bonus="+getBonus()+"]\n"
		+"				"+projects;
	}
}
