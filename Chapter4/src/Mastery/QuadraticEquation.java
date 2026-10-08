package Mastery;

import java.util.Scanner;

import java.util.HashMap;

public class QuadraticEquation {
	
	static void ImportValues(HashMap<String, Float> Values) {
		
		Values.put("A", Float.NaN);
		
		Values.put("B", Float.NaN);
		
		Values.put("C", Float.NaN);
		
		
		
	}
	
	static void GetInput(String Variable, HashMap<String, Float> Values, Scanner Userinput) {
		
		System.out.println("Enter a number for " + Variable);
		
		while (true) {
			
			String input = Userinput.next();
			
			try {
				
				Float Value = Float.parseFloat(input);
				
				Values.replace(Variable, Value);
				
				return;
				
				
			} catch (NumberFormatException e) {
				
				System.out.println("Invalid input, must be a number.");
			}
			
		}
		
	}
	
	static void CalculateRoots(HashMap<String, Float> Values) {
		
		float A = Values.get("A");
		
		float B = Values.get("B");
		
		float C = Values.get("C");
		
		float Discriminant = (B * B) - (4 * A * C);
		
		float MinusRoot = (float) (-B - Math.sqrt(Discriminant)) / (2 * A);
		
		float PlusRoot = (float) (-B + Math.sqrt(Discriminant)) / (2 * A);
		
		if (Discriminant < 0) {
			
			System.out.println("There are no real roots. (Discriminant < 0");
			
			return;
			
		} else if (Discriminant == 0) {
			
			System.out.printf("X = %.2f%n", MinusRoot);
			
			return;
		}
		

		
		System.out.printf("X = %.2f, %.2f", MinusRoot, PlusRoot);
		
	}
	
	public static void main(String[] args) {
		
		Scanner Userinput = new Scanner(System.in);
		
		HashMap<String, Float> Values = new HashMap<String,Float>();
		
		ImportValues(Values);
		
		Values.forEach((key, value) -> {
		    GetInput(key, Values, Userinput);
		});
		
		Userinput.close();
		
		CalculateRoots(Values);
		
	}

}
