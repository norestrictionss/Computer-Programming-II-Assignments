import java.util.Calendar;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
STUDENT ID: 150121822
PURPOSE: This project aims to construct a department, managers, employees and customers and print 
whole informations about them depending on input file located in the same directory path. 
*/
public class RegularEmployee extends Employee {
	private double performanceScore;
	private double bonus;
	
	// Parameterized constructor
	public RegularEmployee(int id, String firstName, String lastName,
			String gender, Calendar birthDate, String maritalStatus, 
			String hasDriverLicence, double salary, Calendar hireDate, 
			Department department, double performanceScore) throws Exception  {
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, 
				hireDate, department);
		
		this.performanceScore=performanceScore;
	}
	// Overloaded constructor
	public RegularEmployee(Employee employee, double perfScore) throws Exception{
		super(employee.getId(), employee.getFirstName(), employee.getLastName(), 
			  employee.getGender(), employee.getBirthDate(), employee.getMaritalStatus(), 
			  employee.getHasDriverLicence(), employee.getSalary(), employee.getHireDate(), 
			  employee.getDepartment());
		this.performanceScore=perfScore;
		
	}
	
	// Setter-getter methods
	public double getPerformanceScore() {
		return performanceScore;
	}

	public void setPerformanceScore(double performanceScore) {
		this.performanceScore = performanceScore;
	}

	public double getBonus() {
		return Math.round(bonus*100)/100.0;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	// Overriden toString method
	@Override
	public String toString() {
		return "RegularEmployee\n 				Person Info [id="+getId()+", firstName="+getFirstName()+
				", lastName="+getLastName()+", gender="+getGender()
				+"]\n				Employee Info [salary="+getSalary()+ ", hireDate=" +getHireDate().get(Calendar.DAY_OF_MONTH)+"/"+(getHireDate().get(Calendar.MONTH)+1)+"/"+
				getHireDate().get(Calendar.YEAR)+"]\n"
				+"				RegularEmployee Info [performanceScore="+getPerformanceScore()+", bonus="+getBonus()+"]"
				;
	}
}
