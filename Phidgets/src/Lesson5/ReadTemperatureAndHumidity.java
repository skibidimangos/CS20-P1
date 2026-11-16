package Lesson5;

import com.phidget22.*;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.util.Set;

public class ReadTemperatureAndHumidity {
	
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
        HumiditySensor humiditySensor = new HumiditySensor();
        TemperatureSensor temperatureSensor = new TemperatureSensor();

        //Open 
        humiditySensor.open(1000);
        temperatureSensor.open(1000);
        
        Set<Phidget> Devices = Set.of(humiditySensor, temperatureSensor);

        //Use your Phidgets 
        
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 'y') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
	    	double Temp = temperatureSensor.getTemperature();
	    	
	    	double Humidity = humiditySensor.getHumidity();
	    	
	    	if (Temp > 21) {
	    		
	            System.out.println(Temp + " °C" );
	    		
	    	} else {
	    		
	            System.out.println("Room is too cold.");
	            
	    	}
	    	
	    	if (Humidity > 30) {
	    		
	            System.out.println(Humidity + " %RH");
	    		
	    	} else {
	    		
	            System.out.println("Humidity is low.");
	            
	    	}
        	

            

            
            
            Thread.sleep(150);
            
        }
        
        DeactivateDevices(Devices);
	    
    }

}
