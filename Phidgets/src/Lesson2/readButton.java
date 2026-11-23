package Lesson2;

import com.phidget22.*;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class readButton {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	public static void Deactivate(DigitalOutput Device) throws Exception {
		Device.setState(false);
		Device.close();
	}
	
	public static void Deactivate(DigitalInput Device) throws Exception {
		Device.close();
	}
	
    public static void main(String[] args) throws Exception{
        
	    //Create 
	    DigitalInput greenButton = new DigitalInput();
	
	    //Address 
	    greenButton.setHubPort(5);
	    greenButton.setIsHubPortDevice(true);
	
	    //Open 
	    greenButton.open(1000);
	
	    //Use your Phidgets 
	    
	    boolean PreviousState = greenButton.getState();
	    
	    while(true)	{
	    	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 'y') {
	    			
	    			break;
	    			
	    		}
	    		
	    		
	    	}
	    	
	    	boolean ButtonState = greenButton.getState();
	    	
	    	if (PreviousState != ButtonState) {
	    	
	            System.out.println("Button State: " + greenButton.getState());
	            
	            PreviousState = ButtonState;
	            
	    	}
	            
            Thread.sleep(150);
    	}
	    
	    Deactivate(greenButton);
	    
	    
	    
	}

}
