package Mastery;

import java.util.Scanner;

import java.util.ArrayList;

public class Order_Ex11 {
	
	static class Item {
		String name;
		double price;
		int quantity;
		
		Item(String name, double price, int quantity) {
			
			this.name = name;
			this.price = price;
			this.quantity = quantity;
		}
		
		public void ChangeQuantity (int NewValue) {
			
			this.quantity = NewValue;
		}
	}
	
	static ArrayList<Item> CreateList() {
		
		ArrayList<Item> ItemsList = new ArrayList<Item>();
		
	    ItemsList.add(new Item("Burger", 1.69, 0));
	    ItemsList.add(new Item("Fries", 1.09, 0));
	    ItemsList.add(new Item("Soda", 0.99, 0));
		
		return ItemsList;
	}
	
	static void DisplayMenu(ArrayList<Item> Items) {
		
		for (Item item: Items) {
			
			String Name = item.name;
			
			double Price = item.price;
			
			System.out.println(Name + " : $" + Price);
			
			
		}
	}
	
	static double FetchTendered(Scanner Userinput) {
		
		while (true) {
			
			String input = Userinput.next();
			
			try {

				return Double.parseDouble(input);
				
			} catch (NumberFormatException e) {
				
				System.out.println("Input has to be a number, try again.");
				
			}
		}
	}
	
	public static void main(String[] args) {
		
		float Tax = (float) (6.5 / 100);
		
		double Tendered = 0.0;
		
		ArrayList<Item> Items = CreateList();
		
		Scanner Userinput = new Scanner(System.in);
		
		System.out.println("Enter an Item: \n Enter ('m') to see the menu again. \n Enter ('t') for tender amount. \n Enter ('f') to calculate.");
		
		DisplayMenu(Items);
		
		while (true) {
			
			Item selectedItem = null;
			
			String input = Userinput.next();

			for (Item item : Items) {
			    if (item.name.equalsIgnoreCase(input)) {
			        selectedItem = item;
			        break;
			    }
			}

			if (selectedItem == null) {
				if (input.equalsIgnoreCase("m")) {
					
					DisplayMenu(Items);
					
					continue;
					
				}
				
				if (input.equalsIgnoreCase("t")) {
					
					System.out.println("Enter Amount Tendered");
					
					Tendered = FetchTendered(Userinput);
					
					continue;
					
				}
				
				if (input.equalsIgnoreCase("f")) {
					
					break;
					
				}
				System.out.println("Invalid Input, try again.");
				
				continue;
			}
			

				
			System.out.println("Enter quantity of " + input + "(s).");
			
			String input2 = Userinput.next();
			
			try {
				
				int Quantity = Integer.parseInt(input2);
				
				if (Quantity < 0) {
					
					System.out.println("The input cannot be negative, try again.");
					continue;
				}
				
				selectedItem.ChangeQuantity(Quantity);
				
				System.out.println("There is now: " + Quantity + " " + selectedItem.name + "(s) in the system.");
				
				
			} catch (NumberFormatException e) {
				
				System.out.println("Input has to be an integer, try again.");
				
			}

		}
		
		Userinput.close();
		
		Double PreTotal = 0.0;
		
		for (Item item: Items) {
			
			if (item.quantity == 0) {
				continue;
			}
			
			String Name = item.name;
			
			double Price = item.price;
			
			int Quantity = item.quantity;
			
			double Value = Price * Quantity;
			
			System.out.printf(Quantity + " " + Name + "(s) : $%.2f%n", Value);  // Decimal formatting required for rounding errors
			
			PreTotal += Value;

		}
		
		Double FinalTotal = PreTotal + (PreTotal * Tax);
		
		System.out.printf("Total before tax: $%.2f%n", PreTotal);
		
		System.out.printf("Final Total: $%.2f%n", FinalTotal);
		

		
		if (Tendered > 0) {
			
			System.out.println("Amount Tendered: $" + Tendered);
			
			double Change = Tendered - FinalTotal;
			
			if (Change < 0) {
				System.out.printf("Remainder Owed: $%.2f%n", (Change * - 1));
			} else {
				
				System.out.printf("Change: $%.2f%n", Change);;
			
			}
			
		}
		
	}

}
