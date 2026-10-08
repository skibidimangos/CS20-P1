package Mastery;

import java.util.Scanner;

public class SimpleInterest_Ex15 {
	
	static double CalculateAmount(double Amount, double Years, double Interest, boolean forReturns) {
		
		if (forReturns) { // Get Returns
			
			return Amount * (1 + (Years * Interest));
			
		}
		
		return Amount / (1 + (Years * Interest));
		
	}
	
	static double GetDouble(Scanner Userinput) {
		
	    while (true) {
	    	
	    	String input = Userinput.next();
	    	
	    	try {
	    		
	    		double Value = Double.parseDouble(input);
	    		
	    		
	    		return Value;
	    		
	    		
	    	} catch (NumberFormatException e) {
				
				System.out.println("Input has to be a number, try again.");
				
			}
	    }
	}
	
	static boolean GetBoolean(Scanner Userinput) {
		
	    while (true) {
	    	
	    	String input = Userinput.next();
	    	
	    	try {
	    		
	    		if (input.equalsIgnoreCase("returns") || input.equalsIgnoreCase("principal")) {
	    			
		    		boolean Value = (input.equalsIgnoreCase("Returns"));
		    		
		    		return Value;
	    			
	    		} else {
	    			
	    			System.out.println("Invalid input, must be either (returns) or (principal).");
	    			
	    		}
	    			
	    	} catch (Exception e) {
	    	      System.out.println("Something went wrong.");
	        }
	    }
	}
	
	public static void main(String[] args) {
		
		Scanner Userinput = new Scanner(System.in);
		

		System.out.println("Are you calculating the principal or the returns? (principal/returns)");
		
		boolean forReturns = GetBoolean(Userinput);
		
		if (forReturns) {
			
			System.out.println("Enter the Principal: ");
			
			
		} else {
			
			System.out.println("Enter the Desired Returns: ");
			
		}
		
		double Amount = GetDouble(Userinput);
		
		System.out.println("Enter Time (in years): ");
		
		double Years = GetDouble(Userinput);
		
		System.out.println("Enter Interest Rate: ");
		
		double Interest = GetDouble(Userinput);
		
		if (forReturns) {
			
			System.out.printf("The returns you will get are: $%.2f%n", CalculateAmount(Amount, Years, Interest, forReturns));
			
			
		} else {
			
			System.out.printf("The principal you need to get those returns are: $%.2f%n", CalculateAmount(Amount, Years, Interest, forReturns));
			
		}
		
		Userinput.close();
		
	}
}
