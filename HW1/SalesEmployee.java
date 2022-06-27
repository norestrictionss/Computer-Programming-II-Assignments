import java.util.ArrayList;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
STUDENT ID: 150121822
PURPOSE: This project aims to construct a department, managers, employees and customers and print 
whole informations about them depending on input file located in the same directory path. 
*/
import java.util.Calendar;
public class SalesEmployee extends RegularEmployee {
	private ArrayList<Product> sales;
	public static int numberOfSalesEmployees;
	
	// Parameterized constructor
	public SalesEmployee(int id, String firstName, String lastName, 
						String gender, Calendar birthDate, String maritalStatus, String hasDriverLicence, 
					    double salary, Calendar hireDate, Department department, double pScore, ArrayList<Product> s) throws Exception {
		
		super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence, salary, hireDate, department, 
			  pScore);
		this.sales=s;
		SalesEmployee.numberOfSalesEmployees++;
	}
	// Overloaded constructor
	public SalesEmployee(RegularEmployee re, ArrayList<Product> s) throws Exception {
		super(re.getId(), re.getFirstName(), re.getLastName(), re.getGender(), re.getBirthDate(), 
			  re.getMaritalStatus(), re.getHasDriverLicence(), re.getSalary(), re.getHireDate(), 
			  re.getDepartment(), re.getPerformanceScore());
		this.sales=s;
		SalesEmployee.numberOfSalesEmployees++;
	}
	// Getter-setter methods
	public ArrayList<Product> getSales() {
		return sales;
	}

	public void setSales(ArrayList<Product> sales) {
		this.sales = sales;
	}
	public boolean addSale(Product s) {
		return sales.add(s);
	}
	public boolean removeSale(Product s) {
		return sales.remove(s);
	}
	public static int getNumberOfSalesEmployees() {
		return numberOfSalesEmployees;
	}
	public static void setNumberOfSalesEmployees(int numberOfSalesEmployees) {
		SalesEmployee.numberOfSalesEmployees = numberOfSalesEmployees;
	}
	// Overriden toString method
	@Override
	public String toString() {
		return "SalesEmployee\n				Person Info [id="+getId()+", firstName="+getFirstName()+
				", lastName="+getLastName()+", gender="+getGender()
				+"]\n				Employee Info [salary="+getSalary()+ ", hireDate=" +getHireDate().get(Calendar.DAY_OF_MONTH)+"/"+(getHireDate().get(Calendar.MONTH)+1)+"/"+
				getHireDate().get(Calendar.YEAR)+"]\n"
				+"				RegularEmployee Info [performanceScore="+getPerformanceScore()+", bonus="+getBonus()+"]\n"+
				"				"+sales+"";
	}
}
