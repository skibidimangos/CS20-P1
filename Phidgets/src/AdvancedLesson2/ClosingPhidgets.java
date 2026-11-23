package AdvancedLesson2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.phidget22.*;

public class ClosingPhidgets {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	public static void main(String[] args) throws Exception{

        //Create
        TemperatureSensor temperatureSensor = new TemperatureSensor();

        //Open
        temperatureSensor.open(1000);

        //Use your Phidgets
        System.out.println("Temperature: " + temperatureSensor.getTemperature() + " °C" );
        
        //Close your Phidgets
        temperatureSensor.close();
        
        try {
        	
        	System.out.println("Temperature: " + temperatureSensor.getTemperature() + " °C" );
        	
        	
        } catch (PhidgetException e) {
        	
        	e.printStackTrace();
        	
        }
        
        temperatureSensor.open();
        
        try {
        	
        	System.out.println("Temperature: " + temperatureSensor.getTemperature() + " °C" );
        	
        	
        } catch (PhidgetException e) {
        	
        	e.printStackTrace();
        	
        }
        
        temperatureSensor.close();
        
    }

}
