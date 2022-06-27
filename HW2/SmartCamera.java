/* STUDENT NAME: BARIÞ GÝRAY AKMAN
 STUDENT ID: 150121822
 PURPOSE: THIS PROGRAM AIMS TO STORE SMART DEVICES LOCATED IN HOUSE. 
 AFTER STORAGE PROCESS, THE PROGRAM TRIES SAMPLE TEST CASES DEFINED IN 
 CLASSES.(INFORMATIONS ARE 
 PRINTED) THIS SAMPLE CASES INCLUDES TURNING ON/OFF LIGHTS, ACTIVATING 
 THE RECORD STATUS OF THE CAMERA ETC.
 */
import java.util.Calendar;

public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera>{
	private boolean status;
	private int batteryLife;
	private boolean nightVision;
	// Parametrized constructor
	public SmartCamera(String alias, String macId, 
			boolean nightVision, int batteryLife) {
		this.setAlias(alias);
		this.setMacId(macId);
		this.setNightVision(nightVision);
		this.setBatteryLife(batteryLife);
	}
	// Getter setter methods
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getBatteryLife() {
		return batteryLife;
	}
	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}
	public boolean getNightVision() {
		return nightVision;
	}
	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}
	// Sample test case included by method given below.
	@Override
	public boolean testObject() {
		// TODO Auto-generated method stub
		if(this.getConnectionStatus()) {
			 SmartObjectToString();
			 System.out.println("Test is starting for SmartCamera day time");
			 this.recordOn(true);
			 this.turnOff();
			 System.out.println("Test is starting for SmartCamera night time");
			 this.recordOn(false);
			 this.turnOff();
			 System.out.println("Test completed for SmartCamera\n");
			 return true;
		}
		return this.controlConnection();
	}
	@Override
	public boolean shutDownObject() {
		if(this.getConnectionStatus()==true) {
			this.SmartObjectToString();
			if(this.getStatus()) this.setStatus(false);
			return true;
		}
		return this.controlConnection();
	}
	// This compares current class with parameter of the method.
	public int compareTo(SmartCamera smartCamera) {
		if(this.getBatteryLife()>smartCamera.getBatteryLife()) 
			return 1;
		else if(this.getBatteryLife()==smartCamera.getBatteryLife())
			return 0;
		else 
			return -1;
	}
	// This method defines motion detection depending on specific conditions.
	@Override
	public boolean controlMotion(boolean hasMotion, boolean isDay) {
		// TODO Auto-generated method stub
		if(!hasMotion) 
			System.out.println("Motion not detected!");
		else 
			System.out.println("Motion detected!");
		
		if(isDay)
		    recordOn(isDay);
		else if(!isDay && this.getNightVision()) 
			recordOn(isDay);
		
		return true;
		
	}
	// This method starts recording by turning off the camera.
	// If conditions aren't satisfied, it will end up with different case given below.
	public void recordOn(boolean isDay) {
		if(this.getConnectionStatus()) {
			if(!this.getStatus()) {
				if(!isDay && !nightVision)
					System.out.println("Sorry! Smart Camera - "+this.getAlias()
							+ " does not have night vision feature.");
				else {
					System.out.println("Smart Camera - "+this.getAlias()+" is "
							+ "turned on now");
					this.setStatus(true);
				}
			}
			else
				System.out.println("Smart Camera - "+this.getAlias() +" has already been turned on");
			
		}
		else this.controlConnection();
			
		
	}
	// This method ends up recording by turning off the camera.
	public void recordOff() {
		if(this.getConnectionStatus()) {
			this.setStatus(false);
			if(this.getStatus()) {
				System.out.println("Smart Camera - "+this.getAlias()+
						" is turned off now");
			}
			else 
				System.out.println("Smart Camera - "+this.getAlias() +
						" has been already turned off");
		}
		else this.controlConnection();
	}
	// It turns off the camera
	public void turnOff() {
		if(this.getConnectionStatus()) {
			if(this.getStatus()) {
				System.out.println("Smart Camera - "+this.getAlias()+" is turned off now");
				this.setStatus(false);
			}
			else if(!this.getStatus()){
				System.out.println("Smart Camera - "+this.getAlias()
						+ " has been already turned off");
			}
		}
		else this.controlConnection();
	}
	// It turns on the camera
	public void turnOn() {
		if(this.getConnectionStatus()) {
			if(!this.getStatus()) {
				System.out.print("Smart Camera - "+this.getAlias()+" is turned on now");
				this.setStatus(true);
				
			}
			else if(this.getStatus()){
				System.out.println("Smart Camera - "+this.getAlias() 
						+ " has been already turned on");
			}
		}
		else this.controlConnection();
	}
	// It prints the up-to-date features of camera
	public void printRecordInformation() {
		System.out.println("SmartCamera -> "+this.getAlias()+
				" Cam's battery life is "+
				this.getBatteryLife()+" status is recording");
	}
	public String toString() {
		return "Test is starting for SmartCamera";
	}
}
