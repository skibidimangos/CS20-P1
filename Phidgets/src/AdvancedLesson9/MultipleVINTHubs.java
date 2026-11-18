package AdvancedLesson9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import com.phidget22.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Random;

public class MultipleVINTHubs { 
	
	public static Random random = new Random();
	
	public static class Player {
		
		private int Points = 0;
		
		private ArrayList<Phidget> LEDs;
		
		private ArrayList<Phidget> Buttons;
		
		private CountDownLatch Trigger = new CountDownLatch(1);
		
		private DigitalInputStateChangeListener CorrectListener =
				
			new DigitalInputStateChangeListener() {

	            public void onStateChange(DigitalInputStateChangeEvent e) {
	                
	            	boolean State = e.getState();
	            	
	            	if (State) {
	            		
	            		System.out.println("Pressed correct button!");
	            		
	            		Points += 1;
	            		
	            		Trigger.countDown();
	            		
	            	}
	            	
	            }
        };
        
		private DigitalInputStateChangeListener IncorrectListener =
				
			new DigitalInputStateChangeListener() {
			
	            public void onStateChange(DigitalInputStateChangeEvent e) {
	                
	            	boolean State = e.getState();
	            	
	            	if (State) {
	            		
	            		System.out.println("Pressed wrong button! -1 Point.");
	            		
	            		Points -= 1;
	            		
	            		Trigger.countDown();
	            		
	            		
	            		
	            	}
	            	
	            	
	            }
        };
		
		public Player(ArrayList<Phidget> LEDs, ArrayList<Phidget> Buttons) {
			
			this.LEDs = LEDs;
			
			this.Buttons = Buttons;
			
		}
		
		public void SelectButton() throws PhidgetException, InterruptedException {
			
			int CorrectColor = random.nextInt(0, 1 + 1); // 1 = Red, 2 = Green.
			
			int IncorrectColor = (CorrectColor == 1) ? 0 : 1;
			
			DigitalOutput CorrectLED = (DigitalOutput) LEDs.get(CorrectColor);
			
			DigitalInput CorrectButton = (DigitalInput) Buttons.get(CorrectColor);
			
			DigitalInput IncorrectButton = (DigitalInput) Buttons.get(IncorrectColor);
			
			CorrectLED.setState(true);
			
			CorrectButton.addStateChangeListener(CorrectListener);
			
			IncorrectButton.addStateChangeListener(IncorrectListener);
			
			Trigger.await();
			
			CorrectButton.removeStateChangeListener(CorrectListener);
			
			IncorrectButton.removeStateChangeListener(IncorrectListener);
			
			CorrectLED.setState(false);
			
		}
		
	}
	
	public static void DeactivateDevices(ArrayList<ArrayList<Phidget>> DeviceList) throws Exception {
		
		for (ArrayList<Phidget> Devices: DeviceList) {
		
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
		
	}
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);

	public static void main(String[] args) throws Exception {

		ArrayList<Phidget> Hub1LEDs = new ArrayList<Phidget>();
		
		ArrayList<Phidget> Hub1Buttons = new ArrayList<Phidget>();
		
		ArrayList<Phidget> Hub2LEDs = new ArrayList<Phidget>();
		
		ArrayList<Phidget> Hub2Buttons = new ArrayList<Phidget>();
		
		ArrayList<ArrayList<Phidget>> DeviceList = new ArrayList<ArrayList<Phidget>>();
		
		DeviceList.addAll((ArrayList<ArrayList<Phidget>>) Arrays.asList(Hub1LEDs, Hub1Buttons, Hub2LEDs, Hub2Buttons));
		
		int Hub1Serial = 684336;
		
		int Hub2Serial = 684160;
		
		ArrayList<Integer> LEDPorts = (ArrayList<Integer>) Arrays.asList(1, 4); // Red LED first, Green LED after.
		
		ArrayList<Integer> ButtonPorts = (ArrayList<Integer>) Arrays.asList(0, 5); // Red Button first, Green Button after.

        //Create, Address and Open
		
        Hub1LEDs.add((DigitalOutput) Arrays.asList(new DigitalOutput(), new DigitalOutput()));
        
        Hub1Buttons.add((DigitalInput) Arrays.asList(new DigitalInput(), new DigitalInput()));
        
        Hub2LEDs.add((DigitalOutput) Arrays.asList(new DigitalOutput(), new DigitalOutput()));
        
        Hub2Buttons.add((DigitalInput) Arrays.asList(new DigitalInput(), new DigitalInput()));
        
        for (int i = 0; i < 2; i++) {
        	
        	DigitalOutput LED = (DigitalOutput) Hub1LEDs.get(i);
        	
			LED.setDeviceSerialNumber(Hub1Serial);
        	
        	LED.setHubPort(LEDPorts.get(i));
        	
        	LED.open(1000);
        	
        }
        
        for (int i = 0; i < 2; i++) {
        	
        	DigitalOutput LED = (DigitalOutput) Hub2LEDs.get(i);
        	
			LED.setDeviceSerialNumber(Hub2Serial);
        	
        	LED.setHubPort(LEDPorts.get(i));
        	
        	LED.setIsHubPortDevice(true);
        	
        	LED.open(1000);
        	
        }
        
        for (int i = 0; i < 2; i++) {
        	
        	DigitalInput Button = (DigitalInput) Hub1Buttons.get(i);
        	
        	Button.setDeviceSerialNumber(Hub2Serial);
        	
        	Button.setHubPort(ButtonPorts.get(i));
        	
        	Button.open(1000);
        	
        }
        
        for (int i = 0; i < 2; i++) {
        	
        	DigitalInput Button = (DigitalInput) Hub2Buttons.get(i);
        	
        	Button.setDeviceSerialNumber(Hub2Serial);
        	
        	Button.setHubPort(ButtonPorts.get(i));
        	
        	Button.open(1000);
        	
        }
        
        

        //Use your Phidgets
        while(true){
        	
	    	if (reader.ready()) {
	    		
	    		if (reader.read() == 's') {
	    			
	    			break;
	    			
	    		}
	    		
	    	}
	    	
            Thread.sleep(1000);
        }
        
        DeactivateDevices(DeviceList);
        
    }

}