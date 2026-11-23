package Practice2;

import com.phidget22.*;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.util.Set;

public class HotOrCold {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
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
	
    public static void main(String[] args) throws Exception{

        //Create 
        TemperatureSensor temperatureSensor = new TemperatureSensor();
        
        DigitalOutput redLED = new DigitalOutput();
        
        DigitalOutput greenLED = new DigitalOutput();

        //Open 
        
        redLED.setHubPort(1);
        redLED.setIsHubPortDevice(true);
        
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);
        
        redLED.open(1000);
        greenLED.open(1000);
        temperatureSensor.open(1000);
        
        Set<Phidget> Devices = Set.of(redLED, greenLED, temperatureSensor);

        //Use your Phidgets 
        
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 'y') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
	    	double Temp = temperatureSensor.getTemperature();
	    	
	    	System.out.println(Temp + " °C" );
	    	
	    	if (Temp >= 20 && Temp <= 24) {
	    		
	            greenLED.setState(true);
	            
	            redLED.setState(false);
	    		
	    	} else {
	    		
	            greenLED.setState(false);
	            
	            redLED.setState(true);
	            
	    	}

            Thread.sleep(150);
            
        }
        
        DeactivateDevices(Devices);
	    
    }

}
