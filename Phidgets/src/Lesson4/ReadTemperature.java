package Lesson4;

import com.phidget22.*;

import java.io.BufferedReader;

import java.io.InputStreamReader;

public class ReadTemperature {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
    public static void main(String[] args) throws Exception{

        //Create 
        TemperatureSensor temperatureSensor = new TemperatureSensor();

        //Open 
        temperatureSensor.open(1000);

        //Use your Phidgets 
        
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 'y') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
	    	double Temp = temperatureSensor.getTemperature();
	    	
	    	double TempFahrenheit = (Temp * 1.8) + 32;
        	
            System.out.println(TempFahrenheit + " °F" );
            Thread.sleep(150);
            
        }
        
        temperatureSensor.close();
	    
    }

}
