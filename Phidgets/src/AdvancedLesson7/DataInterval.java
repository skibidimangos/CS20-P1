

package AdvancedLesson7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

import com.phidget22.*;

public class DataInterval { // Compared to lesson 5, the output is no longer at a constant pace but when the value/state changes.
	  								// This also means that the output will never repeat the same value over and over again like before.
	
	public static void DeactivateDevices(Set<Phidget> Devices) throws Exception {
		
		for (Phidget Device: Devices) {
			
	        if (Device instanceof DigitalInput) {
	        	
	            Device.close();
	        }

	        else if (Device instanceof DigitalOutput) {
	        	
	            DigitalOutput LED = (DigitalOutput) Device;
	            
	            LED.setState(false);
	            
	            LED.close();
	        }
		}
	}
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	
	public static void main(String[] args) throws Exception {

        //Create
        TemperatureSensor temperatureSensor = new TemperatureSensor();

        //Temperature Event 
        temperatureSensor.addTemperatureChangeListener(new TemperatureSensorTemperatureChangeListener() {
            public void onTemperatureChange(TemperatureSensorTemperatureChangeEvent e) {
                System.out.println("Temperature: " + e.getTemperature());
            }
        });

        //Open
        temperatureSensor.open(5000);

        //Set Data Interval 
        temperatureSensor.setDataInterval(1000); // 500ms -> Shorter, 60000ms -> Longer.
        
        //LEDs and Buttons do not have a data interval since they do not have a constant sensor that measures something.

        //Keep program running
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 's') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
            Thread.sleep(150);
        }
        
        temperatureSensor.close();
    }

}
