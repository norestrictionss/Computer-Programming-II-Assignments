import java.util.ArrayList;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
STUDENT ID: 150121822
PURPOSE: This project aims to construct a department, managers, employees and customers and print 
whole informations about them depending on input file located in the same directory path. 
*/
import java.util.Calendar;
public class Manager extends Employee{
	private ArrayList<RegularEmployee> regularEmployees=new ArrayList<>();
	private double bonusBudget;
	
	// Parameterized constructor
	public Manager(int id, String firstName, String lastName, String gender, Calendar
			birthDate, String maritalStatus, String hasDriverLicence, 
			double salary, Calendar hireDate, Department department, 
			double bonusBudget) throws Exception {
	
			super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, 
					hireDate, department);
			this.bonusBudget=bonusBudget;
	}
	// Overloaded constructor
	public Manager(Employee employee, double bonusBudget) throws Exception{
		super(employee.getId(), employee.getFirstName(), 
				employee.getLastName(), employee.getGender(), employee.getBirthDate(), employee.getMaritalStatus(), 
				employee.getHasDriverLicence(), employee.getSalary(), employee.getHireDate(), 
				employee.getDepartment());
		this.bonusBudget=bonusBudget;
	}
	// Getter-setter methods
	public ArrayList<RegularEmployee> getRegularEmployees() {
		return regularEmployees;
	}

	public void setRegularEmployees(ArrayList<RegularEmployee> regularEmployees) {
		this.regularEmployees = regularEmployees;
	}

	public double getBonusBudget() {
		return bonusBudget;
	}

	public void setBonusBudget(double bonusBudget) {
		this.bonusBudget = bonusBudget;
	}
	// Adds fetched regularemployee to regularemployees arraylist.
	public void addEmployee(RegularEmployee e) {
		regularEmployees.add(e);
	}
	// Removes fetched regularemployee from regularemployees arraylist.
	public void removeEmployee(RegularEmployee e) {
		regularEmployees.remove(e);
	}
	// It distributes bonus budget amongst regular employees.
	public void distributeBonusBudget() {
	    int total=0;
	    double unit=0;
	    for(int i=0;i<regularEmployees.size();i++) {
	    	total+=regularEmployees.get(i).getSalary()*regularEmployees.get(i).getPerformanceScore();
	    }
	    unit=(double)bonusBudget/total;
	    for(int i=0;i<regularEmployees.size();i++) {
	    	RegularEmployee e=regularEmployees.get(i);
	    	e.setBonus(unit*e.getPerformanceScore()*e.getSalary());
	    }
	}
	// Overriden toString method
	@Override
	public String toString() {
		return getDepartment()+"\n	Manager [id:"+" "+getId()+", "+getFirstName()+" "+getLastName()+
				" \n		# of Employees: "+regularEmployees.size()+"]";

	}
}
