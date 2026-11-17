package AdvancedLesson3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.phidget22.*;

public class ButtonEvents {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	public static void main(String[] args) throws Exception{

        
        //Create
        DigitalInput redButton = new DigitalInput();
        
        DigitalInput greenButton = new DigitalInput();

        //Address
        redButton.setIsHubPortDevice(true);
        redButton.setHubPort(0);
        
        greenButton.setIsHubPortDevice(true);
        greenButton.setHubPort(5);

        //Event 
        redButton.addStateChangeListener(new DigitalInputStateChangeListener() {
            public void onStateChange(DigitalInputStateChangeEvent e) {
            	
            	boolean Pressed = e.getState() ? true : false;
            	
            	if (Pressed) {
            		
            		System.out.println("Red Button Pressed");
            		
            	} else {
            		
            		System.out.println("Red Button Not Pressed");
            		
            	}
            }
        });
        
        greenButton.addStateChangeListener(new DigitalInputStateChangeListener() {
            public void onStateChange(DigitalInputStateChangeEvent e) {
            	
            	boolean Pressed = e.getState() ? true : false;
            	
            	if (Pressed) {
            		
            		System.out.println("Green Button Pressed");
            		
            	} else {
            		
            		System.out.println("Green Button Not Pressed");
            		
            	}
            }
        });
        


        //Open
        redButton.open(1000);
        
        greenButton.open(1000);

        //Keep program running
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 's') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
        	
            Thread.sleep(150); // Output frequency does not change based on loop iteration rate.  
            
        }
        
        redButton.close();
        
        greenButton.close();
        
    }

}
