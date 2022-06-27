/* STUDENT NAME: BARIÞ GÝRAY AKMAN
 STUDENT ID: 150121822
 PURPOSE: THIS PROGRAM AIMS TO STORE SMART DEVICES LOCATED IN HOUSE. 
 AFTER STORAGE PROCESS, THE PROGRAM TRIES SAMPLE TEST CASES DEFINED IN 
 CLASSES.(INFORMATIONS ARE 
 PRINTED) THIS SAMPLE CASES INCLUDES TURNING ON/OFF LIGHTS, ACTIVATING 
 THE RECORD STATUS OF THE CAMERA ETC.
 */
import java.util.Calendar;
public class SmartLight extends SmartObject implements LocationControl, Programmable {
	private boolean hasLightTurned;
	private Calendar programTime;
	private boolean programAction;
	// Parametrized constructor
	public SmartLight(String alias, String macId) {
		this.setAlias(alias);
		this.setMacId(macId);
		this.programTime=Calendar.getInstance();
	}
	// It turns on the light
	public void turnOnLight() {
	    if(hasLightTurned==false) {
	    	System.out.println("Smart Light - "+this.getAlias()+" is turned on now (Current time: "+
	    			getProgramTime().get(Calendar.HOUR_OF_DAY)+":"+getProgramTime().get(Calendar.MINUTE)+":"+
	    			getProgramTime().get(Calendar.MINUTE)+")");
	    	this.hasLightTurned=true;
	    }
	    else 
	    	System.out.println("Smart Light - "+this.getAlias()+" has been already turned on");
	}
	// It turns off the light
	public void turnOffLight() {
		if(hasLightTurned==true) {
	    	System.out.println("Smart Light - "+this.getAlias()+" is turned off now (Current time: "+
	    		getProgramTime().get(Calendar.HOUR_OF_DAY)+":"+getProgramTime().get(Calendar.MINUTE)+":"+
	    		getProgramTime().get(Calendar.MINUTE)+")");
	    	this.hasLightTurned=false;
	    }
	    else 
	    	System.out.println("Smart Light - "+this.getAlias()+" has been already turned off");
	}
	// Sample test case
	public boolean testObject() {
		if(this.getConnectionStatus()) {
			this.toString();
			this.SmartObjectToString();
			this.turnOnLight();
			this.turnOffLight();
		}
		else return this.controlConnection();
		System.out.println("Test completed for SmartLight\n");
		return true;
	}
	// Getter setter methods
	public boolean getHasLightTurned() {
		return hasLightTurned;
	}
	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
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
	@Override
	public void setTimer(int seconds) {
		// TODO Auto-generated method stub
		if(this.getConnectionStatus()==true) {
			if(this.getHasLightTurned()) {
				Calendar calendar2=Calendar.getInstance();
				this.setProgramTime(calendar2);
				System.out.println("Smart Light - "+this.getAlias()+" will be turned off "+seconds+" seconds later!");
				System.out.println("(Current time: "+this.getProgramTime().get(Calendar.HOUR_OF_DAY)+":"+
				this.getProgramTime().get(Calendar.MINUTE)+":"+this.getProgramTime().get(Calendar.SECOND)+")");
				this.getProgramTime().add(Calendar.SECOND, seconds);
			}
			else if(!this.getHasLightTurned()){
				Calendar calendar2=Calendar.getInstance();
				this.setProgramTime(calendar2);
				System.out.println("Smart Light - "+this.getAlias()+" will be turned on "+seconds+" seconds later!");
				System.out.println("(Current time: "+this.getProgramTime().get(Calendar.HOUR_OF_DAY)+":"+
				this.getProgramTime().get(Calendar.MINUTE)+":"+this.getProgramTime().get(Calendar.SECOND)+")");
				this.getProgramTime().add(Calendar.SECOND, seconds);

			}
		}
		else this.controlConnection();
	}
	@Override
	public void cancelTimer() {
		// TODO Auto-generated method stub
		if(this.getConnectionStatus())
			this.setProgramTime(null);
		else this.controlConnection();
	}
	@Override
	public void runProgram() {
		// TODO Auto-generated method stub
		boolean situation=this.getProgramTime().get(Calendar.HOUR_OF_DAY)==Calendar.getInstance().get(Calendar.HOUR_OF_DAY) &&  
				   this.getProgramTime().get(Calendar.MINUTE)==Calendar.getInstance().get(Calendar.MINUTE) && 
				   this.getProgramTime().get(Calendar.SECOND)==Calendar.getInstance().get(Calendar.SECOND);
		if(this.getConnectionStatus()) {
			if(situation && this.getHasLightTurned()==true){
				System.out.println("RunProgram -> Smart Light - "+this.getAlias()); 
				System.out.println("Smart Light - "+this.getAlias()+" is turned off now (Current time:"+
				this.getProgramTime().get(Calendar.HOUR_OF_DAY)+":"+this.getProgramTime().get(Calendar.MINUTE)+":"+
				this.getProgramTime().get(Calendar.SECOND)+")");
			}
			else if(situation && this.getHasLightTurned()==false) {
				System.out.println("RunProgram -> Smart Light - "+this.getAlias()); 
				System.out.println("Smart Light - "+this.getAlias()+" is turned on now (Current time:"+
				this.getProgramTime().get(Calendar.HOUR_OF_DAY)+":"+this.getProgramTime().get(Calendar.MINUTE)+":"+
				this.getProgramTime().get(Calendar.SECOND)+")");
			}
		}
		else if(!this.getConnectionStatus()) this.controlConnection();

	}
	// This method turns off the smart light depending on connection status
	@Override
	public void onLeave() {
		// TODO Auto-generated method stub
		if(this.getConnectionStatus()==true) {
			this.controlConnection();
			this.setHasLightTurned(false);
			
			System.out.println("On Leave -> Smart Light - "+this.getAlias());
			System.out.println("Smart Light - "+this.getAlias()+" is turned off now (Current time: "+
			this.getProgramTime().get(Calendar.HOUR_OF_DAY)+":"+this.getProgramTime().get(Calendar.MINUTE)+":"+
					this.getProgramTime().get(Calendar.SECOND)+")");
		}
		else this.controlConnection();
	}
	// This method turns on the smart light depending on connection status
	@Override
	public void onCome() {
		// TODO Auto-generated method stub
		if(this.getConnectionStatus()==true) {
			this.setHasLightTurned(true);
			
			System.out.println("On Come -> Smart Light - "+this.getAlias());
			System.out.println("Smart Light - "+this.getAlias()+" is turned on now (Current time: "+
			this.getProgramTime().get(Calendar.HOUR_OF_DAY)+":"+this.getProgramTime().get(Calendar.MINUTE)+":"+
				this.getProgramTime().get(Calendar.SECOND)+")");
		}
		else this.controlConnection();
	}
	@Override
	public boolean shutDownObject() {
		// TODO Auto-generated method stub
		if(this.getConnectionStatus()==true) {
			this.SmartObjectToString();
			if(this.getHasLightTurned()==true) this.setHasLightTurned(false);
			return true;
		}
		return this.controlConnection();
	}
	public boolean checkTimeEquality() {
		return  this.getProgramTime().get(Calendar.HOUR_OF_DAY)==Calendar.getInstance().get(Calendar.HOUR_OF_DAY) &&  
				this.getProgramTime().get(Calendar.MINUTE)==Calendar.getInstance().get(Calendar.MINUTE) && 
				this.getProgramTime().get(Calendar.SECOND)==Calendar.getInstance().get(Calendar.SECOND);
	}
	@Override
	public String toString() {
		return "Test is starting for SmartLight";
	}
}
