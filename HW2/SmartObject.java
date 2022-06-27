/* STUDENT NAME: BARIÞ GÝRAY AKMAN
 STUDENT ID: 150121822
 PURPOSE: THIS PROGRAM AIMS TO STORE SMART DEVICES LOCATED IN HOUSE. 
 AFTER STORAGE PROCESS, THE PROGRAM TRIES SAMPLE TEST CASES DEFINED IN 
 CLASSES.(INFORMATIONS ARE 
 PRINTED) THIS SAMPLE CASES INCLUDES TURNING ON/OFF LIGHTS, ACTIVATING 
 THE RECORD STATUS OF THE CAMERA ETC.
 */
public abstract class SmartObject {
	private String alias;
	private String macId;
	private String IP;
	private boolean connectionStatus;
	// Default constructor
	public SmartObject() {
		this.setConnectionStatus(true);
	}
	// Connect and disconnect methods
	public boolean connect(String IP) {
		this.setIP(IP);
		this.setConnectionStatus(true);
		System.out.println(alias+" connection established");
		
		return true;
	}
	public boolean disconnect() {
		setIP(IP);
		this.setConnectionStatus(false);
		return false;
	}
	// It prints informations of object
	public void SmartObjectToString() {
		System.out.println("This is "+getClass()+" device "+alias);
		System.out.println("	MacId: "+getMacId());
		System.out.println("	IP: "+getIP());
	}
	// It controls the connection of the smart device
	public boolean controlConnection() {
		if(connectionStatus==false) {
			System.out.println("This device is not connected. "+getClass()+" -> "+alias);
			return false;
		}
		return true;
	}
	// Abstract classes
	public abstract boolean testObject();
	public abstract boolean shutDownObject();
	// Setter getter methods
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getMacId() {
		return macId;
	}
	public void setMacId(String macId) {
		this.macId = macId;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String IP) {
		this.IP = IP;
	}
	public boolean getConnectionStatus() {
		return connectionStatus;
	}
	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
	
}
