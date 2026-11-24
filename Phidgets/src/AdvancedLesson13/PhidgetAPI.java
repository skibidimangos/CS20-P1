package AdvancedLesson13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.phidget22.*;

public class PhidgetAPI {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
    public static void main(String[] args) throws InterruptedException, IOException{ // Compared to lesson 4, this code has a try-catch to prevent any crashes from errors.

        try{

	        TemperatureSensor tSensor = new TemperatureSensor();
	        
	        tSensor.open(1000);
	        
	        System.out.println("Attached : " + tSensor.getAttached());
	        
	        System.out.println("Library Version: " + PhidgetBase.getLibraryVersion());
	        
	        while (true) {
	        	
		    	if (reader.ready()) {
		    		
		    		if (reader.read() == 's') {
		    			
		    			break;
		    			
		    		}
		    		
		    	}
	        	
	        	
	        	Thread.sleep(100);
	        }
	        
	        tSensor.close();
        
        } catch(PhidgetException ex){
        	
            System.out.println("Failure: " + ex);
            
        }
        
    }
}
  