package AdvancedLesson6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

import com.phidget22.*;

public class AttachDetachEvents { // Compared to lesson 5, the output is no longer at a constant pace but when the value/state changes.
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
        TemperatureSensor tSensor = new TemperatureSensor();
        
        DigitalInput greenButton = new DigitalInput();
        
        DigitalOutput greenLED = new DigitalOutput();
        
        greenButton.setHubPort(5);
        greenButton.setIsHubPortDevice(true);
        
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);

        //Data Event 
        tSensor.addTemperatureChangeListener(new TemperatureSensorTemperatureChangeListener() {
            public void onTemperatureChange(TemperatureSensorTemperatureChangeEvent e) {
                //Print temperature
                System.out.println("Temperature: " + e.getTemperature() + "°C");
            }
        });

        //Attach Event 
        tSensor.addAttachListener(new AttachListener() {
            public void onAttach(AttachEvent e) {
                System.out.println("Temperature Sensor Attached.");
            }
        });
        
        greenLED.addAttachListener(new AttachListener() {
            public void onAttach(AttachEvent e) {
                System.out.println("GreenLED attached.");
            }
        });
        
        greenButton.addAttachListener(new AttachListener() {
            public void onAttach(AttachEvent e) {
                System.out.println("GreenButton attached.");
            }
        });

        //Detach Event 
        tSensor.addDetachListener(new DetachListener() {
            public void onDetach(DetachEvent e) {
                System.out.println("Temperature Sensor Detached.");
            }
        });
        
        greenLED.addDetachListener(new DetachListener() {
            public void onDetach(DetachEvent e) {
                System.out.println("GreenLED Detached.");
            }
        });
        
        greenButton.addDetachListener(new DetachListener() {
            public void onDetach(DetachEvent e) {
                System.out.println("GreenButton Detached.");
            }
        });



        //Open
        greenButton.open(1000);
        greenLED.open(1000);
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
        
        tSensor.close();
        
    }

}
