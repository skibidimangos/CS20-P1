package AdvancedLesson9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.phidget22.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class MultipleVINTHubs { 
	
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	public static Random random = new Random();
	
	public static AtomicBoolean Triggered = new AtomicBoolean(false);
	
	public static AtomicBoolean GameEnd = new AtomicBoolean(true);
	
	public static boolean DetectInput(char ExpectedInput) throws IOException {
		
    	if (reader.ready()) {
    		
    		if (reader.read() == ExpectedInput) {
    			
    			return true;
    			
    		}
    		
    	}
    	
    	return false;
		
	}
	
	public static class Player {
		
		private String Name;
		
		private int Points = 0;
		
		private ArrayList<DigitalOutput> LEDs;
		
		private ArrayList<DigitalInput> Buttons;

		private DigitalInputStateChangeListener CorrectListener =
				
			new DigitalInputStateChangeListener() {

	            public void onStateChange(DigitalInputStateChangeEvent e) {
	                
	            	boolean State = e.getState();
	            	
	            	if (State && !Triggered.get()) {
	            		
	            		System.out.println(Name + "Pressed correct button!");
	            		
	            		Points += 1;
	            		
	            		Triggered.set(true);
	            		
	            		GameEnd.set(true);
	            		
	            		GameManager.displayScores();
	            		
	            	}
	            	
	            }
        };
        
		private DigitalInputStateChangeListener IncorrectListener =
				
			new DigitalInputStateChangeListener() {
			
	            public void onStateChange(DigitalInputStateChangeEvent e) {
	                
	            	boolean State = e.getState();
	            	
	            	if (State && !Triggered.get()) {
	            		
	            		System.out.println(Name + " Pressed wrong button! -1 Point.");
	            		
	            		Points -= 1;
	            		
	            		Triggered.set(true);
	            		
	            		GameEnd.set(true);
	            		
	            		GameManager.displayScores();
	            		
	            	}
	            	
	            	
	            }
        };
		
		public Player(String Name, ArrayList<DigitalOutput> LEDs, ArrayList<DigitalInput> Buttons) {
			
			this.Name = Name;
			
			this.LEDs = LEDs;
			
			this.Buttons = Buttons;
			
		}
		
		public static void awaitTrigger() throws InterruptedException {

		    while (!Triggered.get()) {

		        Thread.sleep(50);

		    }

		}
		
		public int fetchPoints() {
			
			return this.Points;
			
		}
		
		public String fetchName() {
			
			return this.Name;
			
		}
		
		public void playRound() throws InterruptedException {
			
			GameEnd.set(false);
			
			int CorrectColor = random.nextInt(0, 1 + 1); // 1 = Red, 2 = Green.
			
			int IncorrectColor = (CorrectColor == 1) ? 0 : 1;
			
			DigitalOutput CorrectLED = (DigitalOutput) LEDs.get(CorrectColor);
			
			DigitalInput CorrectButton = (DigitalInput) Buttons.get(CorrectColor);
			
			DigitalInput IncorrectButton = (DigitalInput) Buttons.get(IncorrectColor);
			
			try {
				
				CorrectLED.setState(true);
				
			} catch (PhidgetException e) {

				e.printStackTrace();
			}
			
			CorrectButton.addStateChangeListener(CorrectListener);
			
			IncorrectButton.addStateChangeListener(IncorrectListener);
			
			awaitTrigger();
			
			CorrectButton.removeStateChangeListener(CorrectListener);
			
			IncorrectButton.removeStateChangeListener(IncorrectListener);
			
			try {
				
				CorrectLED.setState(false);
				
			} catch (PhidgetException e) {

				e.printStackTrace();
			}
			
			Triggered.set(false);
			
		}
		
	}
	
	public static class GameManager extends Thread {
		
		private static Player Player1;
		
		private static Player Player2;
		
		public GameManager(Player Player1, Player Player2) {
			
			GameManager.Player1 = Player1;
			
			GameManager.Player2 = Player2;
			
		}
		
		public static void displayScores() {
			
			System.out.println(Player1.fetchName() + " : " + Player1.fetchPoints());
			
			System.out.println(Player2.fetchName() + " : " + Player2.fetchPoints());
			
		}
		
		public void run() {
			
			while (true) {
			
				if (GameEnd.get()) {
			
					try {
							
						if (DetectInput('b')) {
							
							for (int i = 3; i > 0; i--) {
								
								System.out.println(i);
								
								Thread.sleep(1000);
								
							}
							
							new Thread(() -> Player1.playRound()).start();
							
							new Thread(() -> Player2.playRound()).start();
								
						}
					
					} catch (InterruptedException | IOException e) {
						
						e.printStackTrace();
						
					}
				
				}
			
		        try {

		            Thread.sleep(10);

		        } catch (InterruptedException e) {

		            Thread.currentThread().interrupt();
		            break;

		        }
				
			}
			
			
		}
		
		
	}
	
	public static class Hub {
		
		public static List<Integer> LEDPorts = Arrays.asList(1, 4); // Red LED first, Green LED after.
		
		public static List<Integer> ButtonPorts = Arrays.asList(0, 5); // Red Button first, Green Button after.
		
		private ArrayList<DigitalOutput> LEDs = new ArrayList<DigitalOutput>();
		
		private ArrayList<DigitalInput> Buttons = new ArrayList<DigitalInput>();
		
		private int SerialID;
		
		public Hub(

				int SerialID
		) {
			
			this.SerialID = SerialID;
			
		}
		
		public void InitializeDevices() throws PhidgetException {
			
	        for (int i = 0; i < 2; i++) {
	        	
	        	DigitalOutput LED = new DigitalOutput();
	        	
	        	int Port = Hub.LEDPorts.get(i);
	        	
				LED.setDeviceSerialNumber(this.SerialID);
				
				LED.setIsHubPortDevice(true);
	        	
	        	LED.setHubPort(Port);
	        	
	        	LED.open(1000);
	        	
	        	this.LEDs.add(LED);
	        	
	        }
	        
	        for (int i = 0; i < 2; i++) {
	        	
	        	DigitalInput Button = new DigitalInput();
	        	
	        	int Port = Hub.ButtonPorts.get(i);
	        	
				Button.setDeviceSerialNumber(this.SerialID);
				
				Button.setIsHubPortDevice(true);
	        	
				Button.setHubPort(Port);
	        	
				Button.open(1000);
				
	        	this.Buttons.add(Button);
	        	
	        }
			
		}
		
		public ArrayList<DigitalOutput> fetchLEDs() {
			
			return this.LEDs;
			
		}
		
		public ArrayList<DigitalInput> fetchButtons() {
			
			return this.Buttons;
			
		}
		
	}
	
	public static void DeactivateDevices(ArrayList<DigitalOutput> OutputDevices, ArrayList<DigitalInput> InputDevices) throws Exception {
		
		for (DigitalOutput LED : OutputDevices) {
            
            LED.setState(false);
            
            LED.close();
			
		}
		
		for (DigitalInput Button : InputDevices) {
           
			Button.close();
			
		}
		
	}

	public static void main(String[] args) throws Exception {
		
		int Hub1Serial = 684336;
		
		int Hub2Serial = 684160;
		
		Hub Hub1 = new Hub(Hub1Serial);
		
		Hub Hub2 = new Hub(Hub2Serial);
		
		Hub1.InitializeDevices();
		
		Hub2.InitializeDevices();
		
		ArrayList<DigitalOutput> Hub1LEDS = Hub1.fetchLEDs(), Hub2LEDS = Hub2.fetchLEDs();
		
		ArrayList<DigitalInput> Hub1Buttons = Hub1.fetchButtons(), Hub2Buttons = Hub2.fetchButtons();
		
		ArrayList<DigitalOutput> OutputDevices = new ArrayList<DigitalOutput>();
		
		OutputDevices.addAll(Hub1LEDS);
		
		OutputDevices.addAll(Hub2LEDS);
		
		ArrayList<DigitalInput> InputDevices = new ArrayList<DigitalInput>();

		InputDevices.addAll(Hub1Buttons);
		
		InputDevices.addAll(Hub2Buttons);
        
        Player Player1 = new Player("Player1", Hub1LEDS, Hub1Buttons);
        
        Player Player2 = new Player("Player2", Hub2LEDS, Hub2Buttons);
        
        GameManager manager = new GameManager(Player1, Player2);
        
        manager.start();
        
        System.out.println("Enter 'b' to start game.");
        
        //Use your Phidgets
        while(true){
        	
        	if (DetectInput('s')) {
        		
        		break;
        		
        	}
	    	
            Thread.sleep(10);
        }
        
        DeactivateDevices(OutputDevices, InputDevices);
        
        manager.interrupt();
        
    }

}