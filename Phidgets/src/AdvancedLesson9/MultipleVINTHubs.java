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
import java.util.concurrent.atomic.AtomicInteger;

public class MultipleVINTHubs {  // Reaction Time game
	
	
	static BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in)
			);
	
	public static Random random = new Random();
	
	public static AtomicBoolean Triggered = new AtomicBoolean(false);
	
	public static char DetectInput() throws IOException {
		
    	if (reader.ready()) {
    		
    		return (char) reader.read();
    		
    	}
    	
    	return (char) reader.read();
		
	}
	
	public static class Player {
		
		private AtomicBoolean PlayerFinished = new AtomicBoolean(true);
		
		private String Name;
		
		private AtomicInteger Points = new AtomicInteger(0);
		
		private ArrayList<DigitalOutput> LEDs;
		
		private ArrayList<DigitalInput> Buttons;

		private DigitalInputStateChangeListener CorrectListener =
				
			new DigitalInputStateChangeListener() {

	            public void onStateChange(DigitalInputStateChangeEvent e) {
	                
	            	boolean State = e.getState();
	            	
	            	if (State && Triggered.compareAndSet(false, true)) {
	            		
	            		System.out.println(Name + " Pressed correct button! +1 Point.");
	            		
	            		Points.incrementAndGet();
	            		
	            	}
	            	
	            }
        };
        
		private DigitalInputStateChangeListener IncorrectListener =
				
			new DigitalInputStateChangeListener() {
			
	            public void onStateChange(DigitalInputStateChangeEvent e) {
	                
	            	boolean State = e.getState();
	            	
	            	if (State && Triggered.compareAndSet(false, true)) {
	            		
	            		System.out.println(Name + " Pressed wrong button! -1 Point.");
	            		
	            		Points.decrementAndGet();
	            		
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
			
			return this.Points.get();
			
		}
		
		public String fetchName() {
			
			return this.Name;
			
		}
		
		public boolean isFinished() {
			
			return this.PlayerFinished.get();
			
		}
		
		public void changeStatus(boolean newStatus) {
			
			this.PlayerFinished.set(newStatus);
			
		}
		
		public void playRound() throws InterruptedException, PhidgetException {
			
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
			
			try {


			    awaitTrigger();

			} finally {

			    CorrectButton.removeStateChangeListener(CorrectListener);

			    IncorrectButton.removeStateChangeListener(IncorrectListener);

			    CorrectLED.setState(false);
			    
			    this.changeStatus(true);

			
			}
			
		}
		
	}
	
	public static class GameManager {
		
		private static Player Player1;
		
		private static Player Player2;
		
		public static void startThread(Player player) {
			
			new Thread(() -> {
				
				try {
					
					player.playRound();
					
				} catch (InterruptedException | PhidgetException e) {

					e.printStackTrace();
					
				}
			}).start();
			
		}
		
		public static void startMatch() throws IOException, InterruptedException {
			
			if (!Player1.isFinished() || !Player2.isFinished()) {
				
				System.out.println("Can't start, previous match hasn't ended.");
				
				return;
				
			}
				
			Player1.changeStatus(false);
			
			Player2.changeStatus(false);
			
			Triggered.set(false);
			
			for (int i = 3; i > 0; i--) {
				
				System.out.println(i);
				
				Thread.sleep(1000);
				
			}
			
			startThread(Player1);
			startThread(Player2);

		}
		
		public static void checkScore() {
				
				System.out.println(Player1.fetchName() + " : " + Player1.fetchPoints());
				
				System.out.println(Player2.fetchName() + " : " + Player2.fetchPoints());
			
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
        
        GameManager.Player1 = Player1;
        
        GameManager.Player2 = Player2;
        
        boolean Running = true;
        
        System.out.println("Enter 'b' to start game.");
        
        System.out.println("Enter 'c' to see score.");
        
        System.out.println("Enter 's' to end the program.");
        
        while(Running){
        	
        	char Input = DetectInput();
        	
        	switch (Input) {
        	
        		case 's':
        		
        			Running = false;
        			
        			break;
        			
        		case 'b':
        			
        			GameManager.startMatch();
        			
        			break;
        			
        		case 'c':
        			
        			GameManager.checkScore();
        			
        			break;
        	
        	}
	    	
            Thread.sleep(10);
        }
        
        DeactivateDevices(OutputDevices, InputDevices);
        
    }

}