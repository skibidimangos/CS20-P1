package Mastery;

import java.lang.reflect.Array;

import java.lang.reflect.Field;
import java.util.ArrayList;

import java.util.Arrays;

import java.util.Random;

import java.util.Scanner;

@SuppressWarnings("unused")
public class Bowling {
	
	public static Random RandomClass = new Random();
	
	public static Scanner UserInput = new Scanner(System.in);
	
	static class Bowler {
		
		private static int StrikeReward = 20; // Points given for strike
		
		private static int SpareReward = 15; // Points given for spare
		
		private String Name;
		
		private int PinsKnocked;
		
		private int TotalPinsKnocked;
		
		private int Score;
		
		private int Throw;
		
		private int Strikes;
		
		private int Spares;
		
		private boolean Strike;
		
		private boolean Spare;
		
		// Constructor
		
		public Bowler(String Name) {
			
			this.Name = Name;
			
			this.Throw = 1;
			
		}
		
		// Access Methods
		
		public void printName() {
			
			print("Bowler: " + Name);
		}
		
		public void printScore() {
			
			print("Score: " + Integer.toString(Score));
			
		}
		
		public void printPinsKnocked() {
			
			print("Pins Knocked:" + Integer.toString(PinsKnocked));
			
		}
		
		public void printCurrentThrow() {
			
			String Report = "Status: ";
			
			switch (Throw) {
			
				case 0:
					
					Report += "Skipped (Strike)";
					
					break;
					
				case 1:
					
					Report += Strike ? "Strike!" : "Throw 1";
					
					break;
					
				case 2:
					
					Report += Spare ? "Spare!" : "Throw 2";
					
					break;
			
			}
			
			print(Report);
			
		}
		
		public void reportEndResult() throws IllegalArgumentException, IllegalAccessException {
			
			ArrayList<String> NotDisplayable = new ArrayList<String>();
			
			NotDisplayable.addAll(Arrays.asList("StrikeReward", "SpareReward", "PinsKnocked", "Strike", "Spare"));
		
			
			Field[] Stats = Bowler.class.getDeclaredFields();
			
			for (Field Stat: Stats) {
				
				String StatName = Stat.getName();
			
				if (NotDisplayable.contains(StatName)) {
					
					continue;
					
				}
				
				Object StatType = Stat.getType();
				
				if (StatType == int.class) { // Is an integer
					
						
					print(StatName + " : " + Stat.get(this));
					
				}

				
				if (StatType == String.class) { // Is a string
						
						print(StatName + " : " + String.valueOf(Stat.get(this)));

					
					
				}
					
				
			}
			
		}
		
		// Modify Methods
		
		public void Bowl() {
			
			PinsKnocked += (RandomClass.nextInt(0, (10 - PinsKnocked) + 1));
			
			checkResult();
			
		}
		
		public void checkResult() {
			
			switch (Throw) {
			
				case 1:
				
					if (PinsKnocked == 10) {
						
						Strike = true;
						
					}
				
					break;
				
				case 2:
					
					if (PinsKnocked == 10) {
						
						Spare = true;
						
					}
					
			}
			
		}
		
		public void updateStats() {
			
			if (Strike || Spare) {
				
				
				Throw = Strike ? 0 : 1;
				
				Score += Strike ? Bowler.StrikeReward : Bowler.SpareReward;
				
				Strikes += Strike ? 1 : 0;
				
				Spares += Spare ? 1 : 0;
				
				Strike = false;
				
				Spare = false;
				
				TotalPinsKnocked += PinsKnocked;
				
				PinsKnocked = 0;
				
			} else switch (Throw) {
			
				case 1:
					
					Throw = 2;
					
					break;
					
				case 2:
					
					Throw = 1;
					
					Score += PinsKnocked;
					
					TotalPinsKnocked += PinsKnocked;
					
					PinsKnocked = 0;
					
					break;
			
			}
			
			
			
		}
	}
		
	
	public static void print(String printstatement) {
		
		System.out.println(printstatement);
		
	}
	
	public static void print(boolean border) {
		
		print("----------------------------------------");
		
	}
	
	public static String getOption() {
		
		ArrayList<String> Options = new ArrayList<String>();
		
		Options.addAll(Arrays.asList("Throw", "Bowler", "Frame", "Game"));
		
		print("Proceed until next:");
		
		for (int i = 1; i <= Options.size(); i++) {
			
			String Option = Options.get(i - 1);
			
			print(i + ". " + Option);
			
		}
		
		
		while (true) {
			
			String Input = Bowling.UserInput.next();
			
			try {
				
				int OptionIndex = Integer.parseInt(Input);
				
				if (OptionIndex > 0 && OptionIndex <= Options.size()) {
					
					return Options.get(OptionIndex - 1);
					
				}
				
				print("Invalid input, must be an input from 1 - " + Options.size());
				
		
			} catch (Exception e) {
				
				print("Unknown error has occured: " + e);
				
			}
		}
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		int Frames = 10;
		
		int Players = 4;
		
		ArrayList<Bowler> Bowlers = new ArrayList<Bowler>();
		
		
		for (int i = 1; i <= Players; i++) {
			
			Bowler newBowler = new Bowler(Integer.toString(i));
			
			Bowlers.add(newBowler);
			
		}
		
		String Option = getOption();
		
		for (int a = 1; a <= Frames; a++) {
			
			for (Bowler bowler: Bowlers) {
				
				for (int b = 1; b <= 2; b++) {
					
					print(true);
					
					bowler.printName();
				
					bowler.Bowl();
					
					bowler.printCurrentThrow();
					
					bowler.printPinsKnocked();
					
					bowler.updateStats();
					
					bowler.printScore();
			
					
					print(true);
					 
					if (Option.equalsIgnoreCase("Throw")) {
						
						Option = getOption();
						
					}
					
					
				

				}
				
				
				if (Option.equalsIgnoreCase("Bowler")) {
						
						Option = getOption();
						
				}
				
				
			}
			
			if (Option.equalsIgnoreCase("Frame")) {
				
				Option = getOption();
				
			}
			
			
		}
		
		for (Bowler bowler: Bowlers) {
			
			print(true);
			
			bowler.reportEndResult();
			
		}
		
		print(true);

	}

}
