package Practice1;

import com.phidget22.*;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.io.IOException;

import java.util.Set;

public class TugOfWar {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	public static void Deactivate(Set<Phidget> Devices) throws Exception {
		
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
        
        Set<Phidget> InputDevices = Set.of(redButton, greenButton);
	
	    //Use your Phidgets 
	    
	    boolean PreviousGreenState = greenButton.getState();
	    
	    boolean PreviousRedState = redButton.getState();
	    
	    int RedPresses = 0, GreenPresses = 0;
	    
	    boolean RedWinner = false, GreenWinner = false;
	    
	    while(true)	{
	    	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 'y') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
	    	RedWinner = (RedPresses == 10) ? true : false;
	    	
	    	GreenWinner = (GreenPresses == 10) ? true : false;
	    	
	    	if (RedWinner) {
	    		
	    		Deactivate(InputDevices);
	    		
	    		for (int i = 0; i < 4; i++) {
	    			
	    	        redLED.setState(true);
	    	        
	    	        Thread.sleep(500);

	    	        redLED.setState(false);
	    	        
	    	        Thread.sleep(500);
	    			
	    		}
	    		
	    		break;
	    		
	    	}
	    	
	    	if (GreenWinner) {
	    		
	    		Deactivate(InputDevices);
	    		
	    		for (int i = 0; i < 4; i++) {
	    			
	    	        greenLED.setState(true);
	    	        
	    	        Thread.sleep(500);

	    	        greenLED.setState(false);
	    	        
	    	        Thread.sleep(500);
	    			
	    		}
	    		
	    		break;
	    		
	    	}
	    	
	    	boolean GreenState = greenButton.getState();
	    	
	    	boolean RedState = redButton.getState();
	    	
	    	if (Pressed(GreenState, PreviousGreenState)) {
	    	
	    		GreenPresses += 1;
	            
	            System.out.println("Green: " + GreenPresses);
	            
	    	}
	    	
	    	if (Pressed(RedState, PreviousRedState)) {
		    	
	    		RedPresses += 1;
	            
	            System.out.println("Red: " + RedPresses);
	            
	    	}
	    	
	    	greenLED.setState(GreenState);
	    	
	    	redLED.setState(RedState);
	    	
	    	PreviousGreenState = GreenState;
	    	
	    	PreviousRedState = RedState;
	            
            Thread.sleep(10);
    	}
	    
	    Deactivate(Devices);
	    
	    
	}

}
