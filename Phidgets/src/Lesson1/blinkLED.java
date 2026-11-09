package Lesson1;

import com.phidget22.*;

public class blinkLED {
	
	 public static void main(String[] args) throws Exception{

        //Create 
        DigitalOutput greenLED = new DigitalOutput();

        //Address 
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);

        //Open 
        greenLED.open(1000);


        while(true)	{
        	
        	greenLED.setState(true);
        	
            Thread.sleep(2000);
            
            for (int i = 0; i <= 3; i++) {
            	greenLED.setState(false);
            	Thread.sleep(50);
            	greenLED.setState(true);
            }
            
        }
	        
    }

}
