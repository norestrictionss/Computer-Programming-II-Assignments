/* STUDENT NAME: BARIÞ GÝRAY AKMAN
 STUDENT ID: 150121822
 PURPOSE: THIS PROGRAM AIMS TO STORE SMART DEVICES LOCATED IN HOUSE. 
 AFTER STORAGE PROCESS, THE PROGRAM TRIES SAMPLE TEST CASES DEFINED IN 
 CLASSES.(INFORMATIONS ARE 
 PRINTED) THIS SAMPLE CASES INCLUDES TURNING ON/OFF LIGHTS, ACTIVATING 
 THE RECORD STATUS OF THE CAMERA ETC.
 */
import java.util.Calendar;
public class SmartPlug extends SmartObject implements Programmable {
	private boolean status;
	private Calendar programTime;
	private boolean programAction;
	public SmartPlug(String alias, String macId) {
		this.setAlias(alias);
		this.setMacId(macId);
		this.programTime=Calendar.getInstance();
	}
	// Turns on the plug
	public void turnOn() {
		if(this.getConnectionStatus()==true) {
			if(this.getStatus()==false) {
				System.out.print("Smart Plug - "+this.getAlias()+" is turned on now");
				this.printCurrentTime();
				this.setStatus(true);
				this.setProgramAction(false);
			}
			else if(this.getStatus()==true){
				System.out.println("Smart Plug - "+this.getAlias()+" has been already turned on");
				
			}
		}
		else this.controlConnection();
		
	}
	// Turns off the plug
	public void turnOff() {
		if(this.getConnectionStatus()==true) {
			if(this.getStatus()==true) {
				System.out.print("Smart Plug - "+this.getAlias()+" is turned off now");
				this.printCurrentTime();
				this.setStatus(false);
				this.setProgramAction(true);
			}
			else if(!this.getStatus()==false){
				System.out.println("Smart Plug - "+this.getAlias()+" has been already turned off");
				
			}
		}
		else this.controlConnection();
	}
	// Sample test case written in that method
	public boolean testObject() {
	  if(this.getConnectionStatus()) {
		  this.toString();
		  SmartObjectToString();
		  this.turnOn();
		  this.turnOff();
		  System.out.println("Test completed for SmartPlug\n");
		  return true;
	  }
	  return this.controlConnection();
	}
	// It sets timer depending on current time.
	@Override
	public void setTimer(int seconds) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(this.getConnectionStatus()==true) {
			if(this.getStatus()==true) {
				Calendar calendar2=Calendar.getInstance();
				this.setProgramTime(calendar2);
				System.out.println("Smart Plug - "+this.getAlias()+" will be turned off "+seconds+" seconds later!");
				this.printCurrentTime();
				this.getProgramTime().add(Calendar.SECOND, seconds);

			}
			else if(this.getStatus()==false){
				Calendar calendar2=Calendar.getInstance();
				this.setProgramTime(calendar2);
				System.out.println("Smart Plug - "+this.getAlias()+" will be turned on "+seconds+" seconds later!");
				this.printCurrentTime();
				this.getProgramTime().add(Calendar.SECOND, seconds);

			}
		}
		else this.controlConnection();
		
	}
	// It sets program time to null
	@Override
	public void cancelTimer() {
		// TODO Auto-generated method stub
		if(this.getConnectionStatus())
			this.setProgramTime(null);
		else this.controlConnection();
	}
	// Runs program according to equality of program time and current time
	@Override
	public void runProgram() {
		// TODO Auto-generated method stub
		
		boolean situation=this.getProgramTime().get(Calendar.HOUR_OF_DAY)==Calendar.getInstance().get(Calendar.HOUR_OF_DAY) &&  
				this.getProgramTime().get(Calendar.MINUTE)==Calendar.getInstance().get(Calendar.MINUTE) && 
				this.getProgramTime().get(Calendar.SECOND)==Calendar.getInstance().get(Calendar.SECOND);
		if(this.getConnectionStatus()) {
			if(situation && !this.getProgramAction()){
				System.out.println("RunProgram -> Smart Plug - "+this.getAlias()); 
				this.turnOff();
			}
			else if(situation && this.getProgramAction()) {
				System.out.println("RunProgram -> Smart Plug - "+this.getAlias()); 
				this.turnOn();
			}
		}
		else if(!this.getConnectionStatus()) this.controlConnection();
	}
	// This method shuts down the object
	@Override
	public boolean shutDownObject() {
		if(this.getConnectionStatus()==true) {
			this.SmartObjectToString();
			if(this.getStatus()==true) this.setStatus(false);
			return true;
		}
		return this.controlConnection();
	}
	// Setter getter methods
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Calendar getProgramTime() {
		return programTime;
	}
	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}
	public boolean getProgramAction() {
		return programAction;
	}
	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}
	public String toString() {
		return "Test is starting for SmartPlug";
	}
	// This method prints program time information of the smart plug
	public void printCurrentTime() {
		System.out.println("(Current time: "+this.getProgramTime().get(Calendar.HOUR_OF_DAY)+":"+
				this.getProgramTime().get(Calendar.MINUTE)+":"+this.getProgramTime().get(Calendar.SECOND)+")");
	}

}
