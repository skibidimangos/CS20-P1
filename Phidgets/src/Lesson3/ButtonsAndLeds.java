package Lesson3;

import com.phidget22.*;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.io.IOException;

import java.util.Set;

public class ButtonsAndLeds {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	public static void Deactivate(Phidget Device) throws Exception {
		try {
			
			DigitalInput Button = (DigitalInput) Device;
			
			Button.close();
			
			
		} catch (Exception e) {
			
			DigitalOutput LED = (DigitalOutput) Device;
			
			LED.setState(false);
			
			LED.close();
		}
	}
	
	public static boolean Pressed(boolean CurrentState, boolean PreviousState) throws Exception {
		
		if (CurrentState && !PreviousState) {
			
			return true;
			
		}
		
		
		return false;
		
	}
	
    public static void main(String[] args) throws Exception{
        
	    //Create 
    	
        DigitalInput redButton = new DigitalInput();
        DigitalOutput redLED = new DigitalOutput();
        DigitalInput greenButton = new DigitalInput();
        DigitalOutput greenLED = new DigitalOutput();
	
	    //Address 
        redButton.setHubPort(0);
        redButton.setIsHubPortDevice(true);
        redLED.setHubPort(1);
        redLED.setIsHubPortDevice(true);
        greenButton.setHubPort(5);
        greenButton.setIsHubPortDevice(true);
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);
	
	    //Open 
        redButton.open(1000);
        redLED.open(1000);
        greenButton.open(1000);
        greenLED.open(1000);
        
        Set<Phidget> Devices = Set.of(redButton, redLED, greenButton, greenLED);
	
	    //Use your Phidgets 
	    
	    boolean PreviousGreenState = greenButton.getState();
	    
	    boolean PreviousRedState = redButton.getState();
	    
	    int ButtonPresses = 0;
	    
	    while(true)	{
	    	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 'y') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
	    	boolean GreenState = greenButton.getState();
	    	
	    	boolean RedState = redButton.getState();
	    	
	    	if (Pressed(GreenState, PreviousGreenState)) {
	    	
	            ButtonPresses += 1;
	            
	            System.out.println(ButtonPresses);
	            
	    	}
	    	
	    	if (Pressed(RedState, PreviousRedState)) {
		    	
	            ButtonPresses += 1;
	            
	            System.out.println(ButtonPresses);
	            
	    	}
	    	
	    	greenLED.setState(!RedState);
	    	
	    	redLED.setState(!GreenState);
	    	
	    	PreviousGreenState = GreenState;
	    	
	    	PreviousRedState = RedState;
	            
            Thread.sleep(10);
    	}
	    
	    for (Phidget Device: Devices) {
	    	
	    	Deactivate(Device);
	    	
	    }
	    
	    
	    
	}

}
