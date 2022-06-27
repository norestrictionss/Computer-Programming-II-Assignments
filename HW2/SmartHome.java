/* STUDENT NAME: BARIÞ GÝRAY AKMAN
 STUDENT ID: 150121822
 PURPOSE: THIS PROGRAM AIMS TO STORE SMART DEVICES LOCATED IN HOUSE. 
 AFTER STORAGE PROCESS, THE PROGRAM TRIES SAMPLE TEST CASES DEFINED IN 
 CLASSES.(INFORMATIONS ARE 
 PRINTED) THIS SAMPLE CASES INCLUDES TURNING ON/OFF LIGHTS, ACTIVATING 
 THE RECORD STATUS OF THE CAMERA ETC.
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class SmartHome{
	private ArrayList<SmartObject> smartObjectList=new ArrayList<>();
	public SmartHome() {
		
	}
	public boolean addSmartObject(SmartObject smartObject) {
		smartObject.setIP("10.0.0.x");
		String ip=smartObject.getIP();
		String [] newIPArray=ip.split("\\.");
		String newIP="";
		newIPArray[3]=(100+smartObjectList.size())+"";
		for(int i=0;i<newIPArray.length;i++) {
			newIP+=newIPArray[i];
			if(i!=newIPArray.length-1)
				newIP+=".";
		}
		System.out.println("-----------------"
				+ "------------------"
				+ "----------------"
				+ "-----------------------");
		System.out.println("-----------------"
				+ "------------------"
				+ "----------------"
				+ "-----------------------");
		System.out.println("Adding new SmartObject");
		System.out.println("-----------------"
				+ "------------------"
				+ "----------------"
				+ "-----------------------");
		smartObject.connect(newIP);
		System.out.println(smartObject);
		smartObject.testObject();
		return getSmartObjectList().add(smartObject);
	}
	public boolean removeSmartObject(SmartObject smartObject) {
		return getSmartObjectList().remove(smartObject);
	}
	public void controlLocation(boolean onCome) {
		System.out.println("---------------------------------------------------------------------------\n" + 
				"---------------------------------------------------------------------------\n" + 
				"LocationControl: OnCome\n" + 
				"---------------------------------------------------------------------------");
		for(int j=0;j<smartObjectList.size();j++) {
			if(smartObjectList.get(j) instanceof LocationControl) {
				if(onCome)
					((LocationControl)smartObjectList.get(j)).onCome();
				else 
					((LocationControl)smartObjectList.get(j)).onLeave();
			}
		}
	}
	public void controlMotion(boolean hasMotion, boolean isDay) {
		System.out.println("--------------------------------------------------------------------------\n" + 
				"--------------------------------------------------------------------------\n" + 
				"MotionControl: HasMotion, isDay\n" + 
				"--------------------------------------------------------------------------");
		for(int j=0;j<smartObjectList.size();j++) {
			if(smartObjectList.get(j) instanceof MotionControl) {
				
				((MotionControl)smartObjectList.get(j)).controlMotion(hasMotion, isDay);
			}
		}
	}
	public void controlProgrammable() {
		System.out.println("--------------------------------------------------------------------------\n" + 
				"--------------------------------------------------------------------------\n" + 
				"Programmable: runProgram\n" + 
				"--------------------------------------------------------------------------");
		for(int i=0;i<smartObjectList.size();i++) {
			if(smartObjectList.get(i) instanceof Programmable) 
				((Programmable)smartObjectList.get(i)).runProgram();
		}
	}
	public void controlTimer(int seconds) {
		System.out.println("--------------------------------------------------------------------------\n" + 
				"--------------------------------------------------------------------------\n" + 
				"Programmable: Timer = "+seconds+" seconds\n" + 
				"--------------------------------------------------------------------------\n" + 
				"");
		for(int i=0;i<smartObjectList.size();i++) {
			if(smartObjectList.get(i) instanceof Programmable) {
				
				if(seconds>0)
					((Programmable)smartObjectList.get(i)).setTimer(seconds);
				else
					((Programmable)smartObjectList.get(i)).cancelTimer();
			}
		}
	}
	public void controlTimerRandomly() {
		System.out.println("--------------------------------------------------------------------------\n" + 
				"--------------------------------------------------------------------------\n" + 
				"Programmable: Timer = 5 or 10 seconds randomly\n" + 
				"--------------------------------------------------------------------------");
		for(int i=0;i<smartObjectList.size();i++) {
			if(smartObjectList.get(i) instanceof Programmable) {
				Random rand=new Random();
				int rand_num=rand.nextInt(2);
				int [] random_numbers= {5, 10};
				if(random_numbers[rand_num]==0)
					((Programmable)smartObjectList.get(i)).cancelTimer();
				else
					((Programmable)smartObjectList.get(i)).setTimer(random_numbers[rand_num]);
			}
		}
	}
	public void sortCameras() {
		int array_size=0;
		for(int j=0;j<smartObjectList.size();j++) {
			if(smartObjectList.get(j) instanceof SmartCamera)
				array_size++;
		}
		SmartCamera [] smartCameras=new SmartCamera[array_size];
		int array_index=0;
		for(int j=0;j<smartObjectList.size();j++) {
			if(smartObjectList.get(j) instanceof SmartCamera) {
				smartCameras[array_index]=(SmartCamera)(smartObjectList.get(j));
				array_index++;
			}
		}
		Arrays.sort(smartCameras);
		System.out.println("--------------------------------------------------------------------------\n" + 
				"--------------------------------------------------------------------------\n" + 
				"Sort Smart Cameras\n" + 
				"--------------------------------------------------------------------------");
		for(int i=0;i<smartCameras.length;i++)
			smartCameras[i].printRecordInformation();
		
	}
	public ArrayList<SmartObject> getSmartObjectList() {
		return smartObjectList;
	}
	public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
		this.smartObjectList = smartObjectList;
	}

}
