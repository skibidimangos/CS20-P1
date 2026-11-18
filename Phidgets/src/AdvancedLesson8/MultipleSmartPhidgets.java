package AdvancedLesson8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

import com.phidget22.*;

public class MultipleSmartPhidgets { // Compared to lesson 5, the output is no longer at a constant pace but when the value/state changes.
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
        TemperatureSensor tSensor1 = new TemperatureSensor();
        TemperatureSensor tSensor2 = new TemperatureSensor();
        
        DigitalInput redButton = new DigitalInput();
        DigitalInput greenButton = new DigitalInput();
        
        Set<Phidget> Devices = Set.of(tSensor1, tSensor2, redButton, greenButton);
        
        greenButton.addStateChangeListener(new DigitalInputStateChangeListener() {
            public void onStateChange(DigitalInputStateChangeEvent e) {
            	
            	boolean State = e.getState();
            	
            	if (State) {
	            	
	            	try {
	            		
						System.out.println("Temperature: " + tSensor1.getTemperature() + "°C");
						
					} catch (PhidgetException e1) {

						e1.printStackTrace();
						
					}
            	
            	}
            }
        });
        
        redButton.addStateChangeListener(new DigitalInputStateChangeListener() {
            public void onStateChange(DigitalInputStateChangeEvent e) {
            	
            	boolean State = e.getState();
            	
            	if (State) {
	            	
	            	try {
	            		
						System.out.println("Temperature: " + tSensor2.getTemperature() + "°C");
						
					} catch (PhidgetException e1) {

						e1.printStackTrace();
						
					}
            	
            	}
            }
        });
        
         //Address 
        
        redButton.setHubPort(0);
        redButton.setIsHubPortDevice(true);
        greenButton.setHubPort(5);
        greenButton.setIsHubPortDevice(true);
        
        tSensor1.setHubPort(3);
        tSensor2.setHubPort(2);

        //Open 
        
        redButton.open(1000);
        
        greenButton.open(1000);
        
        tSensor1.open(1000);
        tSensor2.open(1000);

        //Use your Phidgets 
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