import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;
import java.io.PrintWriter;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
   STUDENT ID: 150121822
   PURPOSE: This project aims to construct a department, managers, employees and customers and print 
   whole informations about them depending on input file located in the same directory path. 
  */
public class Test {
	
	// This method constructs projects for each developer.
	public static void constructProjectArrayList(ArrayList<Project> all_projects, 
			ArrayList<Project> projects, String arguments[]) {
			
			for(int i=2;i<arguments.length;i++) {
				for(int j=0;j<all_projects.size();j++) {
					Project project=all_projects.get(j);
					if(project.getProjectName().equals(arguments[i])) {
						projects.add(project);
					}
				}
			}
			
	}
	// This method set some adjustments on managers and regularemployees polimorphically.
	// Those adjustments have been done depending on instructions given in PDF file.
	public static void setAdjustments(ArrayList<Person> people, ArrayList<Manager> managers) throws Exception{
		for(int j=0;j<managers.size();j++) {
			managers.get(j).distributeBonusBudget();
			managers.get(j).raiseSalary(0.2);
		}
		for(int j=0;j<people.size();j++) {
			if(people.get(j) instanceof Developer) {
				((Developer)people.get(j)).raiseSalary(0.23);
			}
			else if(people.get(j) instanceof SalesEmployee) {
				((SalesEmployee)people.get(j)).raiseSalary(0.18);
			}
			else if(people.get(j) instanceof RegularEmployee) {
				((RegularEmployee)people.get(j)).raiseSalary(0.3);
			}
		}
		int max=0, index_holder=0;
		for(int j=0;j<people.size();j++) {
			if(people.get(j) instanceof SalesEmployee) {
				int total_price=0;
				for(int i=0;i<((SalesEmployee)people.get(j)).getSales().size();i++) {
					total_price+=((SalesEmployee)people.get(j)).getSales().get(i).getPrice();
				}
				if(max<total_price) {
					max=total_price;
					index_holder=j;
				}
			}
			if(j==people.size()-1 && people.get(index_holder) instanceof SalesEmployee)
				((SalesEmployee)people.get(index_holder)).raiseSalary(1000);
		}
	}
	
	// This method set some adjustments on managers, add regularemployees who works under him/her.
	public static void editManagers(ArrayList<Manager> managers, RegularEmployee regularEmployee) {
		int t;
		for(int k=0;k<managers.size();k++) {
			Manager manager=managers.get(k);
			for(t=0;t<manager.getRegularEmployees().size();t++) {
			
				if(manager.getRegularEmployees().get(t).getId()==regularEmployee.getId()) {
					manager.addEmployee(regularEmployee);
					manager.removeEmployee(manager.getRegularEmployees().get(t));
					break;
				}
			}
			if(t==managers.size()) {
				manager.addEmployee(regularEmployee);
			}
		}
	}
	// This method add customers on customers arraylist defined in customer generic type.
	public static void addCustomer(ArrayList<Person> people, ArrayList<Customer> customers, String [] arguments, ArrayList<Product> products) throws Exception {
		for(int i=0;i<people.size();i++) {
			Person person=(Person)people.get(i);
			if(person.getId()==Integer.parseInt(arguments[1])) {
				people.remove(person);
				customers.add(new Customer(person, products));
			}
		}
	}
	// This method construct products for customers and salesemployees.
	public static void constructProductArrayList(ArrayList<Product> all_products, ArrayList<Product> products, String [] arguments){
		Product product=null;
		for(int j=2;j<arguments.length;j++) {
			for(int i=0;i<all_products.size();i++) {
				product=all_products.get(i);
				if(product.getProductName().equals(arguments[j])) {
					products.add(product);
				}
			}
		}
	}
	// After whole adjustments and arraylist operations, whole achievements are being printed.
	// This print operation has been done according to output file sent via Marmara UES.
	public static void printAll(ArrayList<Manager> managers, ArrayList<Customer> customers, ArrayList<Person> people) {
		int i=0;
		System.out.println("************************************************");
		while(i<people.size()) {
			if(people.get(i) instanceof Employee || people.get(i) instanceof Customer) people.remove(people.get(i));
			else i++;
		}
		arrangeEmployees(managers);
		for(int j=0;j<managers.size();j++) {
			System.out.println(managers.get(j));
			for(int k=0;k<managers.get(j).getRegularEmployees().size();k++) {
				System.out.println("			"+(k+1)+". "+managers.get(j).getRegularEmployees().get(k));
			}
			if(j!=managers.size()-1)
				System.out.println("\n************************************************");
		}
		System.out.println("\n\n\n" + 
				"**********************CUSTOMERS************************");
		for(int j=0;j<customers.size();j++)
			System.out.println(customers.get(j));
		System.out.println("\n\n\n" + 
				"**********************PEOPLE************************");
		for(int j=0;j<people.size();j++)
			System.out.println(people.get(j));
		
	}
	// It adjusts the order of employees depending on hierarchy amongst them.
	public static void arrangeEmployees(ArrayList<Manager> managers) {
		
		for(int j=0;j<managers.size();j++) {
			ArrayList<RegularEmployee> regularEmployees=new ArrayList<>();
			for(int i=0;i<managers.get(j).getRegularEmployees().size();i++) {
				if(managers.get(j).getRegularEmployees().get(i) instanceof Developer)
					regularEmployees.add(managers.get(j).getRegularEmployees().get(i));
			}
			for(int i=0;i<managers.get(j).getRegularEmployees().size();i++) {
				if(managers.get(j).getRegularEmployees().get(i) instanceof SalesEmployee)
					regularEmployees.add(managers.get(j).getRegularEmployees().get(i));
			}
			for(int i=0;i<managers.get(j).getRegularEmployees().size();i++) {
				if(managers.get(j).getRegularEmployees().get(i) instanceof RegularEmployee && 
					!(managers.get(j).getRegularEmployees().get(i) instanceof SalesEmployee ||
								managers.get(j).getRegularEmployees().get(i) instanceof Developer))
					regularEmployees.add(managers.get(j).getRegularEmployees().get(i));
			}
			managers.get(j).setRegularEmployees(regularEmployees);
		}
	}
	// This method writes whole existing people on file depending on hierarchy.
	public static void writeFile(ArrayList<Manager> managers, ArrayList<Customer> customers, ArrayList<Person> people, PrintWriter output) {
		int i=0;
		String writing="";
		writing+="************************************************\n";
		while(i<people.size()) {
			if(people.get(i) instanceof Employee || people.get(i) instanceof Customer) people.remove(people.get(i));
			else i++;
		}
		arrangeEmployees(managers);
		for(int j=0;j<managers.size();j++) {
			writing+=managers.get(j)+"\n";
			for(int k=0;k<managers.get(j).getRegularEmployees().size();k++) {
				writing+="			"+(k+1)+". "+managers.get(j).getRegularEmployees().get(k)+"\n";
			}
			if(j!=managers.size()-1)
				writing+="************************************************\n";
		}
		writing+="\n\n\n" + 
				"**********************CUSTOMERS************************\n";
		for(int j=0;j<customers.size();j++)
			writing+=customers.get(j)+"\n";
		
		writing+="\n\n" + 
				"**********************PEOPLE************************\n";
		for(int j=0;j<people.size();j++)
			writing+=people.get(j)+"\n";
		output.write(writing);
		output.close();
	}
	public static void main(String[] args) throws Exception{
		ArrayList<Person> people=new ArrayList<>(); 
		ArrayList<Manager> managers=new ArrayList<>();
		ArrayList<Customer> customers=new ArrayList<>();
		ArrayList<Department> departments=new ArrayList<>();
		ArrayList<Product> all_products=new ArrayList<>();
		ArrayList<Project> all_projects=new ArrayList<>();
		PrintWriter output=null;
		Scanner input=null;
		try {
			output=new PrintWriter("output.txt");
			input=new Scanner(new File("input.txt"));
		}
		catch(FileNotFoundException ex){
			throw ex;
		}
		// File reading loop continues until whole lines are read and ended up.
		// Initially, the column which one's turn has come is splitted depending on blanks inside it.
		while(input.hasNextLine()) {
		
			Calendar calendar=Calendar.getInstance();
			
			String [] arguments=input.nextLine().split(" ");
			// If structures have been established depending on first argument in arguments array.
			
			// If structure for construction of  Department instance class
			if(arguments[0].equals("Department")) {
				departments.add(new Department(Integer.parseInt(arguments[1]), arguments[2]));
				
			}
			// If structure for construction of  Project instance class
			else if(arguments[0].equals("Project")){
				String date[]=arguments[2].split("/");
				calendar.set(Integer.parseInt(date[2]), Integer.parseInt(date[1])-1, Integer.parseInt(date[0]));
				all_projects.add(new Project(arguments[1], calendar, arguments[3]));
			}
			// If structure for construction of Customer instance class
			else if(arguments[0].equals("Customer")) {
				ArrayList<Product> products=new ArrayList<>();
				Product product=null;
				constructProductArrayList(all_products, products, arguments);
				addCustomer(people, customers, arguments, products);
			}
			// If structure for construction of Developer instance class
			else if(arguments[0].equals("Developer")) {
				ArrayList<Project> projects=new ArrayList<>();
				RegularEmployee developer=null;
				constructProjectArrayList(all_projects, projects, arguments);
				for(int i=0;i<people.size();i++) {
					
					if(people.get(i) instanceof RegularEmployee && ((RegularEmployee)people.get(i)).getId()==Integer.parseInt(arguments[1]) ) {
						developer=new Developer((RegularEmployee)people.get(i), projects);
						people.add(developer);
						people.remove(people.get(i));
					}
				}
				editManagers(managers, developer);
				
			}
			// If structure for construction of Product instance class
			else if(arguments[0].equals("Product")) {
				String date[]=arguments[2].split("/");
				calendar.set(Integer.parseInt(date[2]), Integer.parseInt(date[1])-1, Integer.parseInt(date[0]));
				all_products.add(new Product(arguments[1], calendar, Double.parseDouble(arguments[3])));
			}
			// If structure for construction of Employee instance class
			else if(arguments[0].equals("Employee")) {
				String date[]=arguments[3].split("/");
				calendar.set(Integer.parseInt(date[2]), Integer.parseInt(date[1])-1, Integer.parseInt(date[0]));
				Person person=null;
				for(int i=0;i<people.size();i++) {
					
					if(people.get(i) instanceof Person && ((Person)people.get(i)).getId()==Integer.parseInt(arguments[1])) {
						person=(Person)people.get(i);
						people.remove((Person)people.get(i));
					}
				}
				for(int j=0;j<departments.size();j++) {
						Department department=departments.get(j);
						if(department.getDepartmentName().equals(arguments[4])) {
							people.add(new Employee(person, Double.parseDouble(arguments[2]), calendar, department));
							
						}
					
				}
			}
			// If structure for construction of RegularEmployee instance class
			else if(arguments[0].equals("RegularEmployee")) {
				Employee regularEmployee=null;
				for(int i=0;i<people.size();i++) {
					if(people.get(i) instanceof Employee &&
							((Employee)people.get(i)).getId()==Integer.parseInt(arguments[1])) {
						regularEmployee=new RegularEmployee((Employee)people.get(i), Integer.parseInt(arguments[2]));
						people.add(regularEmployee);
						people.remove(people.get(i));

					}
				}
				for(int k=0;k<managers.size();k++) {
					
						Manager manager=managers.get(k);
							
						if(manager.getDepartment().getDepartmentId()==regularEmployee.getDepartment().getDepartmentId()) {
								
							manager.addEmployee((RegularEmployee)regularEmployee);
						}
					
				}
			}
			// If structure for construction of SalesEmployee instance class
			else if(arguments[0].equals("SalesEmployee")) {
				ArrayList<Product> products=new ArrayList<>();
				RegularEmployee salesEmployee=null;
	
				constructProductArrayList(all_products, products, arguments);
				for(int i=0;i<people.size();i++) {
					if(people.get(i) instanceof RegularEmployee && 
							((RegularEmployee)people.get(i)).getId()==Integer.parseInt(arguments[1])) {
						salesEmployee=new SalesEmployee((RegularEmployee)people.get(i), products);
						people.add(salesEmployee);
						people.remove(people.get(i));

					}
				}
				editManagers(managers, salesEmployee);
			}
			// If structure for construction of Person instance class
			else if(arguments[0].equals("Person")) {
				String date[]=arguments[5].split("/");

				calendar.set(Integer.parseInt(date[2]), Integer.parseInt(date[1])-1, Integer.parseInt(date[0]));

				people.add(new Person(Integer.parseInt(arguments[3]), arguments[1], arguments[2],
						arguments[4], calendar, arguments[6], arguments[7]));
				
			}
			// If structure for construction of Manager instance class
			else if(arguments[0].equals("Manager")) {
				Employee manager=null;
				for(int i=0;i<people.size();i++) {
					if(people.get(i) instanceof Employee &&
							((Employee)people.get(i)).getId()==Integer.parseInt(arguments[1])) {
						manager=new Manager((Employee)people.get(i), Double.parseDouble(arguments[2]));
						
						people.remove(people.get(i));
						for(int j=0;j<people.size();j++) {
							if(people.get(j) instanceof RegularEmployee && ((RegularEmployee)people.get(j)).getDepartment().getDepartmentId()==manager.getDepartment().getDepartmentId()) {
								((Manager)manager).addEmployee((RegularEmployee)people.get(j));
							}
						}
						managers.add((Manager)manager);

					}
				}
			}
		}
		input.close();
		setAdjustments(people, managers);
		writeFile(managers, customers, people, output);
		printAll(managers, customers, people);
	}
}
