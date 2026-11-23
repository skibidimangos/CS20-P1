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
        
        double Brightness = 0;
        
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 's') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
        	
	        for (;Brightness < 1;) {
	        	
	        	Brightness = Math.min(Brightness + 0.01, 1.0);
	        	
	            redLED.setDutyCycle(Brightness);
	        	
	        	Thread.sleep(10);
	        	
	        }
	        
	        Thread.sleep(1000);
	        
	        for (;Brightness > 0;) {
	        	
	        	Brightness = Math.max(Brightness - 0.01, 0);
	        	
	        	redLED.setDutyCycle(Brightness);
	        	
	        	Thread.sleep(10);
	        	
	        }
	        
	        Thread.sleep(1000);
	        
        }
        

        
        redLED.close();
        
    }

}
