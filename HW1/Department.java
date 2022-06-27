/* STUDENT NAME: BARIÞ GÝRAY AKMAN
   STUDENT ID: 150121822
   PURPOSE: This project aims to construct a department, managers, employees and customers and print 
   whole informations about them depending on input file located in the same directory path. 
  */
public class Department {
	private int departmentId;
	private String departmentName;
	// Parameterized constructor
	// min-3-character rule has been issued in setter methods by try-catch blocks.
	public Department(int departmentId, String departmentName) throws Exception {
		this.setDepartmentId(departmentId);
		this.setDepartmentName(departmentName);
	}
	// Getter-setter methods
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) throws Exception {

		if(departmentId<=0) throw new Exception("departmentId can't be less than 1");
		else this.departmentId = departmentId;
		
	}
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) throws Exception{

		if(departmentName.length()<3) throw new Exception("Length of the "
					+ "department can't be less than 3 characters.");
		else this.departmentName=departmentName;
		

	}
	// Overriden toString method
	@Override
	public String toString() {
		return "Department [departmentId="+departmentId+", departmentName="+departmentName+"]";
	}
	
}
