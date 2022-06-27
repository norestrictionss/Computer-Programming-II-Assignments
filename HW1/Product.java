import java.util.Calendar;
/* STUDENT NAME: BARIÞ GÝRAY AKMAN
STUDENT ID: 150121822
PURPOSE: This project aims to construct a department, managers, employees and customers and print 
whole informations about them depending on input file located in the same directory path. 
*/
public class Product {
	private String productName;
	private java.util.Calendar saleDate;
	private double price;
	
	// Parameterized constructor
	public Product(String sName, java.util.Calendar sDate, 
				   double price) throws Exception {
		this.setProductName(sName);
		this.saleDate=sDate;
		this.price=price;
	}
	// Getter-setter methods
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) throws Exception{

		if(productName.length()<3) throw new Exception("Length of productName can't be"
					+ "less than 3 characters.");
		this.productName = productName;

		
	}
	public java.util.Calendar getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(java.util.Calendar saleDate) {
		this.saleDate = saleDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	// Overriden toString method
	@Override
	public String toString() {
		return "Product [productName="+getProductName()+
		", transactionDate="+getSaleDate().get(Calendar.DAY_OF_MONTH)+"/"+
				(getSaleDate().get(Calendar.MONTH)+1)+"/"+getSaleDate().get(Calendar.YEAR)+", price="+getPrice()+"]";
	}
}
