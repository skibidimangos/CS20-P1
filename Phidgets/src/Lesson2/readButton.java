package Lesson2;

import com.phidget22.*;

public class readButton {
	
    public static void main(String[] args) throws Exception{
        
	    //Create 
	    DigitalInput redButton = new DigitalInput();
	
	    //Address 
	    redButton.setHubPort(5);
	    redButton.setIsHubPortDevice(true);
	
	    //Open 
	    redButton.open(1000);
	
	    //Use your Phidgets 
	    
	    boolean PreviousState = redButton.getState();
	    
	    while(true)	{
	    	
	    	boolean ButtonState = redButton.getState();
	    	
	    	if (PreviousState != ButtonState) {
	    	
	            System.out.println("Button State: " + redButton.getState());
	            
	            PreviousState = ButtonState;
	            
	    	}
	            
            Thread.sleep(150);
    	}
	    
	    
	    
	}

}
