import java.util.Calendar;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
STUDENT ID: 150121822
PURPOSE: This project aims to construct a department, managers, employees and customers and print 
whole informations about them depending on input file located in the same directory path. 
*/
public class Project {
	private String projectName;
	private Calendar startDate;
	private boolean state;

	// Parameterized constructor
	public Project(String pName, Calendar startDate, String state) throws Exception {
		this.setProjectName(pName);
		this.startDate=startDate;
		this.setState(state);
	}
	// Getter-setter methods
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) throws Exception {

		if(projectName.length()<3) throw new Exception("Length of projectName can't be"
					+ "less than 3 characters.");
		else this.projectName = projectName;
		
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public String getState() {
		return state+"";
	}

	public void setState(String state) throws Exception {

		if(state.length()<3) throw new Exception("Length of state can't be less than"
					+ "3 characters.");
		if(state.equals("Open")) this.state=true;
		else if(state.equals("Close")) this.state=false;
		else throw new Exception("This input is invalid for state variable.");

	}
	// Project closer method
	public void close() {
		if(state==true) state=false;
	}
	// Overriden toString method
	@Override
	public String toString() {
		return "Project [projectName="+getProjectName()+", startDate="+getStartDate().get(Calendar.DAY_OF_MONTH)+
				"/"+(getStartDate().get(Calendar.MONTH)+1)+"/"+getStartDate().get(Calendar.YEAR)+", state="+getState()+"]";
	}
}
