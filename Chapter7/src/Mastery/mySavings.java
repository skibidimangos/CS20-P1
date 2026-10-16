package Mastery;

import java.lang.reflect.Field;

import java.util.Scanner;

public class mySavings {
	
	public class PiggyBank {
		
		@SuppressWarnings("unused")
		private int Pennies = 0;
		
		@SuppressWarnings("unused")
		private int Nickels = 0;
		
		@SuppressWarnings("unused")
		private int Dimes = 0;
		
		@SuppressWarnings("unused")
		private int Quarters = 0;
		
		public PiggyBank(
				int Pennies, 
				int Nickels, 
				int Dimes, 
				int Quarters) {
			
			this.Pennies = Pennies;
			
			this.Nickels = Nickels;
			
			this.Dimes = Dimes;
			
			this.Quarters = Quarters;
			
		}
		
		// Instance methods
		
		// Access
		
		public int getCoins(String Type) {
			
		    try {
		    	
		        Field Coin = PiggyBank.class.getDeclaredField(Type);

		        return Coin.getInt(this);
		        

		    } catch (NoSuchFieldException | IllegalAccessException e) {
		    	
		        return -1;
		        
		    }
			
		}
		
		public double getValue() {
			
			Field[] Coins = PiggyBank.class.getDeclaredFields();
			
			double Value = 0.0;
			
			for (Field Coin: Coins) {
				
				try {
					
					Value += Coin.getInt(this);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					
					return -1.0;
					
				}
				
			}
			
			return Value;
			
		}
		
		// Modify
		
		public void addCoins(String Type, int Amount) {
			
		    try {
		    	
		        Field Coin = PiggyBank.class.getDeclaredField(Type);
		        
		        int Coins = Coin.getInt(this);
		        
		        Coin.setInt(this, Amount + Coins);
		        
		        print(Amount + " " + Type + " have been added to the piggy bank. There are now: " + Coins + " " + Type);
		       

		    } catch (NoSuchFieldException | IllegalAccessException e) {
		    	
		        print("Attribute not found.");
		        
		    }

			
		}
		
		public void takeCoins(String Type, int Amount) {
			
		    try {
		    	
		        Field Coin = PiggyBank.class.getDeclaredField(Type);
		        
		        int Coins = Coin.getInt(this);
		        
		        if (Amount > Coins) {
		        	
		        	print("Can't take more coins than amount stored. (" + Amount + " > " + Coins + ")");
		        	
		        	return;
		        	
		        }
		        
		        Coin.setInt(this, Coins - Amount);
		        
		        print(Amount + " " + Type + " have been removed from the piggy bank. There are now: " + Coin.getInt(this) + " " + Type);
		       

		    } catch (NoSuchFieldException | IllegalAccessException e) {
		    	
		        print("Attribute not found.");
		        
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
		print("0. Quit Program");
		
	}
	
	public static int getOption(Scanner Userinput) {
		
		while (true) {
			
			String Input = Userinput.next();
			
			try {
				
				return Integer.parseInt(Input);
				
			} catch (NumberFormatException e) {
				
				print("Input must be an integer.");
				
			}
			
		}
		
		
	}
	
	public static void main(String Args[]) {
		
		Scanner Userinput = new Scanner(System.in);
		
		while (true) {
		
			printOptions();
		
			getOption(Userinput);
		
		
		}
		
	}
	
}
