package AdvancedLesson9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.phidget22.*;

public class MultiplePhidgets { // Compared to lesson 5, the output is no longer at a constant pace but when the value/state changes.
	  								// This also means that the output will never repeat the same value over and over again like before.
	
	public static void DeactivateDevices(ArrayList<DigitalOutput> Devices) throws Exception {
		
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
		
		System.out.println("Start");

		//Define
        ArrayList <DigitalOutput> LEDs = new ArrayList<DigitalOutput>();

        //Create, Address and Open
        for(int i = 0; i < 6; i++){
        	LEDs.add(new DigitalOutput());
            LEDs.get(i).setHubPort(i);
            LEDs.get(i).setIsHubPortDevice(true);
            LEDs.get(i).open(1000);
        }
        
        // Adds new LED object to list, sets the port of the LED, opens the LED.
        

        //Use your Phidgets
        while(true){
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 's') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
            for(int i = 0; i < LEDs.size(); i++){
                LEDs.get(i).setState(true);
                Thread.sleep(100);
            }  
            
            Thread.sleep(100);
            
            for(int i = LEDs.size() - 1; i >= 0; i--){
                LEDs.get(i).setState(false);
                Thread.sleep(100);
            }
            
            Thread.sleep(100);
        }
        
        DeactivateDevices(LEDs);
        
    }

}