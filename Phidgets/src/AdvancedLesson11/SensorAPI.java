package AdvancedLesson11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.phidget22.*;

public class SensorAPI {
	
	static double PreviousTemp;
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
    public static void main(String[] args) throws InterruptedException, IOException{ // Compared to lesson 4, this code has a try-catch to prevent any crashes from errors.

        try{

	        TemperatureSensor TSensor = new TemperatureSensor();
	        
	        HumiditySensor HSensor = new HumiditySensor();
	     
	        TSensor.open(1000);
	        
	        HSensor.open(1000);
	        
	        PreviousTemp = TSensor.getTemperature();
	
	        System.out.println("Min Humidity: " + HSensor.getMinHumidity());
	        
	        System.out.println("Max Humidity: " + HSensor.getMaxHumidity());
	        
	        System.out.println("Min Temp Interval: " + TSensor.getMinDataInterval());
	        
	        System.out.println("Max Humidity: " + TSensor.getMaxDataInterval());
	        
	        TSensor.addTemperatureChangeListener(new TemperatureSensorTemperatureChangeListener() {
	            public void onTemperatureChange(TemperatureSensorTemperatureChangeEvent e) {
	            	
	            	double CurrentTemp = e.getTemperature();
	            	
	            	double TempChange = Math.abs(PreviousTemp - CurrentTemp);
	            	
	            	if (TempChange > 1) {
	            		
		            	PreviousTemp = CurrentTemp;
	            		
	            		System.out.println("Temperature: " + e.getTemperature() + "°C");
	                
	            	} else {
	            		
	            		System.out.println("Temp Difference too small: " + TempChange + "°C");
	            		
	            	}
	            	

	            }
	        });
	        
	        while (true) {
	        	
		    	if (reader.ready()) {
		    		
		    		if (reader.read() == 's') {
		    			
		    			break;
		    			
		    		}
		    		
		    	}
	        	
	        	
	        	Thread.sleep(100);
	        }
	        
	        TSensor.close();
	        
	        HSensor.close();
        
        } catch(PhidgetException ex){
        	
            System.out.println("Failure: " + ex);
            
        }
        
    }
}
  