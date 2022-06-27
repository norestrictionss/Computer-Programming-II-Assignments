import java.util.Calendar;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
STUDENT ID: 150121822
PURPOSE: This project aims to construct a department, managers, employees and customers and print 
whole informations about them depending on input file located in the same directory path. 
*/
public class Employee extends Person {
	private double salary;
	private Calendar hireDate;
	private Department department;
	private static int numberOfEmployees;
	// Parameterized constructor
	public Employee(int id, String firstName, String lastName,
			String gender, Calendar birthDate, String maritalStatus,
			String hasDriverLicence, double salary, Calendar hireDate, 
			Department department) throws Exception {
			super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
			this.setSalary(salary);
			this.hireDate=hireDate;
			this.department=department;
			Employee.numberOfEmployees++;
	}
	// Overloaded constructor
	public Employee(Person person, double salary, Calendar hireDate, Department department) throws Exception {
			super(person.getId(), person.getFirstName(), person.getLastName(), 
					person.getGender(), person.getBirthDate(), person.getMaritalStatus(), 
					person.getHasDriverLicence());
			this.setSalary(salary);
			this.hireDate=hireDate;
			this.department=department;
			Employee.numberOfEmployees++;
	}
	// Getter-setter methods
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary){
		this.salary = salary;
	}
	public Calendar getHireDate() {
		return hireDate;
	}
	public void setHireDate(Calendar hireDate) {
		this.hireDate = hireDate;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public int getNumberofEmployees() {
		return numberOfEmployees;
	}
	public void setNumberofEmployees(int numberOfEmployees) {
		Employee.numberOfEmployees = numberOfEmployees;
	}
	// This method raises salary of an Employee depending on percentage fetched by method.
	public double raiseSalary(double percent) throws Exception{

		if(percent<=1 && percent>=0) this.setSalary(this.getSalary() + getSalary()*percent);
		else throw new Exception("Percentage must be in the interval of 1 and 0.");

		return getSalary();
	}
	// This method raises salary of an Employee depending on amount(in type int) fetched by method.
	public double raiseSalary(int amount) throws Exception{
		
		try {
			if(amount>0) this.setSalary(this.getSalary() + amount);
			else throw new Exception("Amount of salary raising must be only for positive integers.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getSalary();
	}
	// Overriden toString method
	@Override
	public String toString() {
		return "Employee [salary="+getSalary()+"hireDate="+getHireDate().get(Calendar.DAY_OF_MONTH)+
				"/"+(getHireDate().get(Calendar.MONTH)+1)+"/"+getHireDate().get(Calendar.YEAR)+"]";
	}

}
