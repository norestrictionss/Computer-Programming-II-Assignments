import java.util.ArrayList;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
STUDENT ID: 150121822
PURPOSE: This project aims to construct a department, managers, employees and customers and print 
whole informations about them depending on input file located in the same directory path. 
*/
import java.util.Calendar;
public class Customer extends Person {
	private ArrayList<Product> products;
	// Parameterized constructor
	public Customer(int id, String firstName, String lastName, String gender,
			Calendar birthDate, String maritalStatus, String hasDriverLicence, 
			ArrayList<Product> products) throws Exception {
			super(id, firstName, lastName, gender, birthDate, maritalStatus, hasDriverLicence);
			this.products=products;
		
	}
	// Overloaded contructor
	public Customer(Person person, ArrayList<Product> products) throws Exception {
		super(person.getId(), person.getFirstName(), person.getLastName(), person.getGender(), 
				person.getBirthDate(), person.getMaritalStatus(), person.getHasDriverLicence());
		this.setProducts(products);
	}
	// Getter-setter methods
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	// Overloaded toString method
	public String toString() {
		return "Customer [id: "+getId()+", products="+products+"]";
	}
}
