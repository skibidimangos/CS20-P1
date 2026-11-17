package AdvancedLesson4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

import com.phidget22.*;

public class ButtonLEDEvents {
	
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
	
	static boolean GreenLEDActivated = false;
	
	static boolean RedLEDActivated = false;
	
	static int RedPresses = 0, GreenPresses = 0;
	
	public static void main(String[] args) throws Exception{

        
        //Create
        DigitalInput redButton = new DigitalInput();
        DigitalInput greenButton = new DigitalInput();
        DigitalOutput redLED = new DigitalOutput();
        DigitalOutput greenLED = new DigitalOutput();

        //Address
        redButton.setHubPort(0);
        redButton.setIsHubPortDevice(true);
        greenButton.setHubPort(5);
        greenButton.setIsHubPortDevice(true);
        redLED.setHubPort(1);
        redLED.setIsHubPortDevice(true);
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);
        
        Set<Phidget> Devices = Set.of(
			redButton, greenButton,
			redLED, greenLED
		);
        
        Set<Phidget> InputDevices = Set.of(
			redButton, greenButton
		);
        
        

        //Event 
        redButton.addStateChangeListener(new DigitalInputStateChangeListener() {
            public void onStateChange(DigitalInputStateChangeEvent e) {
            	
            	boolean State = e.getState();
            	
            	RedLEDActivated = State;
            	
            	if (State) {
            		
            	
	            	RedPresses += 1;
	            	
	            	System.out.println("Red: " + RedPresses);
            	
            	}
            	
            }
        });
        
        greenButton.addStateChangeListener(new DigitalInputStateChangeListener() {
            public void onStateChange(DigitalInputStateChangeEvent e) {
            	
            	boolean State = e.getState();
            	
            	GreenLEDActivated = State;
            	
            	if (State) {
            	
	            	GreenPresses += 1;
	            	
	            	System.out.println("Green: " + GreenPresses);
            	
            	}
            }
        });
        


        //Open
        redButton.open(1000);
        
        greenButton.open(1000);
        
        redLED.open(1000);
        
        greenLED.open(1000);
	    
	    boolean RedWinner = false, GreenWinner = false;

        //Keep program running
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 's') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
	    	RedWinner = (RedPresses == 10) ? true : false;
	    	
	    	GreenWinner = (GreenPresses == 10) ? true : false;
	    	
	    	if (RedWinner) {
	    		
	    		DeactivateDevices(InputDevices);
	    		
	    		for (int i = 0; i < 4; i++) {
	    			
	    	        redLED.setState(true);
	    	        
	    	        Thread.sleep(500);

	    	        redLED.setState(false);
	    	        
	    	        Thread.sleep(500);
	    			
	    		}
	    		
	    		break;
	    		
	    	}
	    	
	    	if (GreenWinner) {
	    		
	    		DeactivateDevices(InputDevices);
	    		
	    		for (int i = 0; i < 4; i++) {
	    			
	    	        greenLED.setState(true);
	    	        
	    	        Thread.sleep(500);

	    	        greenLED.setState(false);
	    	        
	    	        Thread.sleep(500);
	    			
	    		}
	    		
	    		break;
	    		
	    	}
	    	
	    	redLED.setState(RedLEDActivated);
	    	
	    	greenLED.setState(GreenLEDActivated);
        	
            Thread.sleep(150); // Output frequency does not change based on loop iteration rate.  
            
        }
        
        DeactivateDevices(Devices);
        
    }

}
