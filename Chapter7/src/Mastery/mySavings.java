package Mastery;

import java.lang.reflect.Field;

import java.util.Scanner;

import java.util.HashMap;

public class mySavings {
	
	public static Scanner userInput = new Scanner(System.in);
	
	public static class PiggyBank {
		
		@SuppressWarnings("unused")
		private int pennies = 0;
		
		@SuppressWarnings("unused")
		private int nickels = 0;
		
		@SuppressWarnings("unused")
		private int dimes = 0;
		
		@SuppressWarnings("unused")
		private int quarters = 0;
		
		private static HashMap<String, Double> CoinValues = new HashMap<String,Double>();
		
		// Constructors
		
		public PiggyBank() {
			
			PiggyBank.initiateCoinValues();

		}
		
		public PiggyBank(
				int Pennies, 
				int Nickels, 
				int Dimes, 
				int Quarters) {
			
			this.pennies = Pennies;
			
			this.nickels = Nickels;
			
			this.dimes = Dimes;
			
			this.quarters = Quarters;
			
			PiggyBank.initiateCoinValues();
			
		}
		
		// Instance methods
		
		// Access
		
		public double getValue() {
			
			Field[] Coins = PiggyBank.class.getDeclaredFields();
			
			double Value = 0.0;
			
			for (Field Coin: Coins) {
				
				String CoinName = Coin.getName();
				
				if (PiggyBank.CoinValues.containsKey(CoinName) != true) {
					
					continue;
					
				}
				
				try {
					
					Value += Coin.getInt(this) *  PiggyBank.CoinValues.get(CoinName);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					
					print("ERROR: Can't access coin" + e);
					
				}
				
			}
			
			return Value;
			
		}
		
		public void checkStorage() {
			
			Field[] Coins = PiggyBank.class.getDeclaredFields();
			
			for (Field Coin: Coins) {
				
				String CoinName = Coin.getName();
				
				if (PiggyBank.CoinValues.containsKey(CoinName) != true) {
					
					continue;
					
				}
				
				try {
					
					print(CoinName + ":" + Coin.getInt(this));
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					
					print("ERROR: Can't access coin" + e);
					
				}
				
			}
			
		}
		
		// Modify
		
		public static void initiateCoinValues() {
			CoinValues.put("pennies", 0.01);
			
			CoinValues.put("nickels", 0.05);
			
			CoinValues.put("dimes", 0.10);
			
			CoinValues.put("quarters", 0.25);
		}
		
		public void addCoins() {
			
			while (true) {
				
				print("Enter the coin you want to add. (Pennies, Nickels, Dimes, Quarters)");
				
				String input = mySavings.userInput.next();
				
				try {
			
			        Field Coin = PiggyBank.class.getDeclaredField(input.toLowerCase());
			        
			        int CoinsOwned = Coin.getInt(this);
			        
					print("How many coins would you like to enter?");
					
					input = mySavings.userInput.next();
					
					try {
						
						int Amount = Integer.parseInt(input);
						
						Coin.setInt(this, CoinsOwned + Amount);
						
						print(Amount + " " + Coin.getName() + " have been added to the piggy bank. There are now: " + Coin.getInt(this) + " " + Coin.getName());
						
						
					} catch (NumberFormatException e) {
						
						print("Input must be an integer, try again." + e);
						
						continue;
						
					}
					
					
					
					
				} catch (NoSuchFieldException | IllegalAccessException e) {
			    	
			        print("No such coin exists, try again." + e);
			        
			        continue;
			        
			    } 
				
				print("Do you want to continue adding coins? (Y/N)");
				
				input = mySavings.userInput.next();
				
				if (input.equalsIgnoreCase("N")) {
					
					return;
					
				}
				
				
				
			}

			
		}
		
		public void takeCoins() {
			
			while (true) {
				
				print("Enter the coin you want to take. (Pennies, Nickels, Dimes, Quarters)");
				
				String input = mySavings.userInput.next();
				
				try {
			
			        Field Coin = PiggyBank.class.getDeclaredField(input.toLowerCase());
			        
			        int CoinsOwned = Coin.getInt(this);
			        
			        if (CoinsOwned == 0) {
			        	
			        	print("You don't have any " + Coin.getName() + ", please try again.");
			        	
			        	continue;

			        }
			        
					print("You have: " + CoinsOwned + " " + Coin.getName() + ", how many do you want tot ake?");
					
					input = mySavings.userInput.next();
					
					try {
						
						int Amount = Integer.parseInt(input);
						
						while (Amount > CoinsOwned) {
							
							print("You can't have more coins than you own, try again.");
						
							input = mySavings.userInput.next();
							
							try {
								
								Integer.parseInt(input);
								
							} catch (NumberFormatException e) {
								
								print("Input must be an integer, try again." + e);
								
							}
							
						}
						
						Coin.setInt(this, CoinsOwned - Amount);
						
						print(Amount + " " + Coin.getName() + " have been removed from the piggy bank. There are now: " + Coin.getInt(this) + " " + Coin.getName());
						
						
					} catch (NumberFormatException e) {
						
						print("Input must be an integer, try again." + e);
						
						continue;
						
					}
					
					
					
					
				} catch (NoSuchFieldException | IllegalAccessException e) {
			    	
			        print("No such coin exists, try again." + e);
			        
			        continue;
			        
			    } 
				
				print("Do you want to continue adding coins? (Y/N)");
				
				input = mySavings.userInput.next();
				
				if (input.equalsIgnoreCase("N")) {
					
					return;
					
				}
				
				
				
			}

			
		}
	}

	public static void print(String printStatement) {
		
		System.out.println(printStatement);
		
	}
	
	public static void printOptions() {
		
		print("1. Show Total");
		print("2. Add Coins");
		print("3. Take Coins");
		print("4. Check Piggy Bank");
		print("5. See Options Again");
		print("0. Quit Program");
		
	}
	
	public static int getOption() {
		
		while (true) {
			
			String Input = userInput.next();
			
			try {
				
				return Integer.parseInt(Input);
				
			} catch (NumberFormatException e) {
				
				print("Input must be an integer.");
				
			}
			
		}
		
		
	}
	
	public static void callOption(int Option, PiggyBank myPiggyBank) {
		
		switch (Option) {
		
		case 1:
			
			print("$" + Double.toString(myPiggyBank.getValue()));
			
			return;
			
		case 2:
			
			myPiggyBank.addCoins();
			
			return;
			
		case 3:
			
			myPiggyBank.takeCoins();
			
			return;
			
		case 4:
			
			myPiggyBank.checkStorage();
			
			return;
			
		case 5:
			
			printOptions();
			
			return;
		
		case 0:
			
			userInput.close();
			
			System.exit(0);
		
		}
		
	}
	
	public static void main(String[] args) {
		
		PiggyBank myPiggyBank = new PiggyBank();
		
		printOptions();
		
		while (true) {
			
			callOption(getOption(), myPiggyBank);
		
		}
		
	}
	
}
	

