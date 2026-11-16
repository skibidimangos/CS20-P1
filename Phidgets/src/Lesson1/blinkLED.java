package Lesson1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.phidget22.*;

public class blinkLED {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	
	public static void Deactivate(DigitalOutput Device) throws Exception {
		Device.setState(false);
		Device.close();
	}
	
	public static void Deactivate(DigitalInput Device) throws Exception {
		Device.close();
	}
	
	public static void main(String[] args) throws Exception {

        //Create 
        DigitalOutput greenLED = new DigitalOutput();

        //Address 
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);

        //Open 
        greenLED.open(1000);


        while(true)	{
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 'y') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
        	
        	greenLED.setState(true);
        	
            Thread.sleep(2000);
            
            for (int i = 0; i <= 3; i++) {
            	greenLED.setState(false);
            	Thread.sleep(50);
            	greenLED.setState(true);
            }
            
        }
        
        Deactivate(greenLED);
        
	        
    }

}
