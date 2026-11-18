package AdvancedLesson5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

import com.phidget22.*;

public class SmartPhidgetEvents { // Compared to lesson 5, the output is no longer at a constant pace but when the value/state changes.
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
	
	public static void main(String[] args) throws Exception{

        
		//Create
        HumiditySensor hSensor = new HumiditySensor();
        TemperatureSensor tSensor = new TemperatureSensor();
        
        Set<Phidget> Devices = Set.of(hSensor, tSensor);

        //Humidity Event 
        hSensor.addHumidityChangeListener(new HumiditySensorHumidityChangeListener() {
            public void onHumidityChange(HumiditySensorHumidityChangeEvent e) {
                System.out.println("Humidity: " + e.getHumidity() + "%RH");
            }
        });
        
        //Temperature Event 
        tSensor.addTemperatureChangeListener(new TemperatureSensorTemperatureChangeListener() {
            public void onTemperatureChange(TemperatureSensorTemperatureChangeEvent e) {
            	
            	double Temp = e.getTemperature();
            	
            	if (Temp > 21) {
            		
            		System.out.println("Temperature: " + e.getTemperature() + "°C");
                
            	} else {
            		
            		System.out.println("Room is too cold.");
            		
            	}
            }
        });

        //Open
        hSensor.open(1000);
        tSensor.open(1000);

        //Keep program running
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 's') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
            Thread.sleep(150);
        }
        
        DeactivateDevices(Devices);
        
    }

}
