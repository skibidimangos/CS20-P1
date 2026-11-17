package AdvancedLesson1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.phidget22.*;

public class LEDBrightness {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	public static void main(String[] args) throws Exception{

        //Create 
        DigitalOutput redLED = new DigitalOutput();

        //Address 
        redLED.setHubPort(1);
        redLED.setIsHubPortDevice(true);

        //Open 
        redLED.open(1000);

        //Use your Phidgets with Duty Cycle 
        
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 's') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
        	
	        redLED.setDutyCycle(0.5);
	        Thread.sleep(1000);
	        redLED.setDutyCycle(1);
	        Thread.sleep(1000);
	        redLED.setDutyCycle(0);
	        Thread.sleep(1000);
	        
        }
        
        redLED.setState(false);
        
        redLED.close();
        
    }

}
