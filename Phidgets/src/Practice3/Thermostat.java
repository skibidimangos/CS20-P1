package Practice3;

import com.phidget22.*;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.util.Set;

import java.util.concurrent.TimeUnit;

public class Thermostat {
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	
	public static class Button {
		
		private boolean PreviousState = false;
		
		private DigitalInput Device;
		
		public Button(DigitalInput Device) throws PhidgetException {
			
			this.Device = Device;
			
			this.PreviousState = Device.getState();
			
		}
		
		public boolean getState() throws PhidgetException {
			
			return this.Device.getState();
			
		}
		
		public boolean Pressed(boolean CurrentState) {
			
			if (CurrentState == true && this.PreviousState == false) {
				
				return true;
				
			}
			
			return false;
			
		}
		
		public void updatePrevious(boolean CurrentState) {
			
			this.PreviousState = CurrentState;
			
		}
		
	}
	
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
	
	public static class TempDisplay extends Thread {
		
		private TemperatureSensor Sensor;
		
		private int SetTemp = 21;
		
		public TempDisplay(TemperatureSensor Sensor) {
			
			this.Sensor = Sensor;
			
		}
		
		public void updateTemp(int SetTemp) {
			
			this.SetTemp = SetTemp;
			
		}
		
		public void run() {
			
			while (true) {
				
				double CurrentTemp = 0;
				
				try {
					
					CurrentTemp = Sensor.getTemperature();
					
				} catch (PhidgetException e) {
					
					e.printStackTrace();
					
				}
				
				System.out.println("Set Temperature: " + this.SetTemp + "°C");
				
				System.out.println("Current Temperature: " + CurrentTemp + "°C");
				
				try {
					
					TimeUnit.SECONDS.sleep(10);
					
				} catch (InterruptedException e) {
		            
		            break;
					
				}
				
			}
			
		}
		
	}
	
    public static void main(String[] args) throws Exception{

        //Create 
        TemperatureSensor temperatureSensor = new TemperatureSensor();
        
        DigitalInput redButton = new DigitalInput();
        DigitalOutput redLED = new DigitalOutput();
        DigitalInput greenButton = new DigitalInput();
        DigitalOutput greenLED = new DigitalOutput();
        
        redButton.setHubPort(0);
        redButton.setIsHubPortDevice(true);
        redLED.setHubPort(1);
        redLED.setIsHubPortDevice(true);
        greenButton.setHubPort(5);
        greenButton.setIsHubPortDevice(true);
        greenLED.setHubPort(4);
        greenLED.setIsHubPortDevice(true);

        //Open 
        temperatureSensor.open(1000);
        
        redButton.open(1000);
        redLED.open(1000);
        greenButton.open(1000);
        greenLED.open(1000);
        
        Set<Phidget> Devices = Set.of(
			temperatureSensor
			,redButton, greenButton, 
			redLED, greenLED
		);
        
        
        int SetTemp = 21;
        
        Button GreenButton = new Button(greenButton);
        
        Button RedButton = new Button(redButton);
        
        TempDisplay thread2 = new TempDisplay(temperatureSensor);
        
        thread2.start();

        //Use your Phidgets 
        
        while (true) {
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 's') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
	    	boolean GreenState = GreenButton.getState();
	    	
	    	boolean RedState = RedButton.getState();
	    	
	    	if (GreenButton.Pressed(GreenState)) {
	    		
	    		SetTemp += 1;
	    		
	    	}
	    	
	    	if (RedButton.Pressed(RedState)) {
	    		
	    		SetTemp -= 1;
	    		
	    	}
	    	
	    	GreenButton.updatePrevious(GreenState);
	    	
	    	RedButton.updatePrevious(RedState);
	    	
	    	
	    	thread2.updateTemp(SetTemp);
	    	
	    	double Temp = temperatureSensor.getTemperature();
	    	
	    	double TempDifference = SetTemp - Temp;
	    	
        	if (TempDifference >= -2 && TempDifference <= 2) {
        		
        		greenLED.setState(true);
        		
           		redLED.setState(false);
        		
        	} else {
        		
        		greenLED.setState(false);
        		
        		redLED.setState(true);
        		
        	}
            
            
            Thread.sleep(10);
            
        }
        
        thread2.interrupt();
        
        DeactivateDevices(Devices);
	    
    }

}
