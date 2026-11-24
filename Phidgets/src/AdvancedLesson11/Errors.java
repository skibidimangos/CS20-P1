package AdvancedLesson11;

import com.phidget22.*;

public class Errors {
	
    public static void main(String[] args){ // Compared to lesson 4, this code has a try-catch to prevent any crashes from errors.

        try{

	        TemperatureSensor temperatureSensor = new TemperatureSensor();
	        
	        temperatureSensor.open(1000);
	
	        System.out.println("Temperature: " + temperatureSensor.getTemperature() + " °C" );
	        
	        temperatureSensor.close();
        
        } catch(PhidgetException ex){
        	
            System.out.println("Failure: " + ex);
            
        }
    }
}
  