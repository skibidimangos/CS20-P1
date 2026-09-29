package Mastery;

import java.util.HashMap;

import java.util.Scanner;

import java.util.ArrayList;

public class Order_Ex11 {
	
	static void InsertValues(HashMap<String, Double> hashmap) {
		
		hashmap.put("Burgers", 1.69);
		
		hashmap.put("Fries", 1.09);
		
		hashmap.put("Soda", 0.99);
		
	}
	
	static void DisplayMenu(HashMap<String, Double> hashmap) {
		
		for (HashMap.Entry<String, Double> Entry: hashmap.entrySet()) {
			
		    String Item = Entry.getKey();
		    Double Value = Entry.getValue();
			
			System.out.println(Item + " : $" + Value);
			
		}
		
	}
	
	public static void main(String[] args) {
		
		float Tax = (float) (6.5 / 100);
		
		HashMap<String, Double> Items = new HashMap<String, Double>(); // Needs wrapper classes, not primitive types
		
		ArrayList<Double> Prices = new ArrayList<Double>();
		
		InsertValues(Items);
		
		Scanner Userinput = new Scanner(System.in);
		
		System.out.println("Enter an Item: \n Enter ('m') to see the menu again. \n Enter ('f') to calculate.");
		
		DisplayMenu(Items);
		
		while (true) {
			
			String input = Userinput.next();
			
			if (Items.containsKey(input)) {
				
				System.out.println("Enter quantity of " + input + "s.");
				
				String input2 = Userinput.next();
				
				try {
					
					int Quantity = Integer.parseInt(input2);
					
					double Price = Items.get(input);
					
					Prices.add(Price * Quantity);
					
					
				} catch (NumberFormatException e) {
					
					System.out.println("Input has to be an integer, try again.");
					
				}
				
				
			} else {
				
				if (input.toLowerCase().equals("m")) {
					
					DisplayMenu(Items);
					
					continue;
					
				}
				
				if (input.toLowerCase().equals("e")) {
					
					break;
					
				}
				
				System.out.println("Invalid Input, try again.");
			}
			
		}
	}

}
