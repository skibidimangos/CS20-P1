package SkillBuilders;

import java.util.Scanner;

public class Exponentiation {
	
	static double getInput(Scanner Userinput) {
		
		while (true) {
			
			String Input = Userinput.next();
			
			try {
				
				return Double.parseDouble(Input);
				
			} catch (NumberFormatException e) {
				
				System.out.println("Invalid input, must be a number: " + e);
				
			}
			
			
		}
		
	}
	
	static double calculateExponent(double Base, double Exponent) {
		
		return Math.pow(Base, Exponent);

	}
	
	public static void main(String[] args) {
		
		Scanner Userinput = new Scanner(System.in);
		
		System.out.println("Enter the base: ");
		
		double Base = getInput(Userinput);
		
		System.out.println("Enter the exponent: ");
		
		double Exponent = getInput(Userinput);
		
		double Value = calculateExponent(Base, Exponent);
		
		System.out.printf("%.2f%n", Value);
		
	}

}
