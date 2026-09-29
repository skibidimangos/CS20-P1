package SkillBuilders;

import java.util.Scanner;

import java.util.ArrayList;

public class Delivery {
	
	static void print(String input) {
		System.out.println(input);
	}
	
	static class Dimension {
		String name;
		int limit;
		
		Dimension(String name, int limit) {
			this.name = name;
			this.limit = limit;
		}
	}
	
	public static void main(String[] args)
	{
		// Create list of dimensions
		
		ArrayList<Dimension> dimensions = new ArrayList<>();
		
		dimensions.add(new Dimension("Length", 10));
		
		dimensions.add(new Dimension("Width", 10));
		
		dimensions.add(new Dimension("Height", 10));
		
		boolean Valid = true;
		
		Scanner userinput = new Scanner(System.in); //Ask for user input
		
		print("Enter the dimensions of your package divided by commas. (Length, Width, Height)");
		
		while (true) {
			try {
				
				String input = userinput.nextLine();
				
				String[] InputDimensions = input.split(",");
				
		        if (InputDimensions.length != 3) {
		            print("Please enter exactly 3 dimensions.");
		            continue;
		        }
				
				
				for (int i = 0; i < 3; i++) {
					
					int value = Integer.parseInt(InputDimensions[i].trim());
					
					Dimension CurrentDimension = dimensions.get(i);
					
					int DimensionLimit = CurrentDimension.limit;
					
					String DimensionName = CurrentDimension.name;
					
		            if (value > 0 && value <= DimensionLimit) {
		                print(DimensionName + " of the package is a valid size.");
		                
		            } else {
		            	print(DimensionName + " Cannot be larger than " + DimensionLimit + " Meters.");
		            	Valid = false;
		            }
		           
				}
				
				if (Valid) {
					print("Your package is qualified to be shipped using our services!");
				} else {
					print("Your package is not qualified to be shipped, please adjust the size or choose another service.");
				}
				
				
				
			} catch (NumberFormatException e) {
			   print("Invalid input, only integers are allowed.");
			}
		}
		
	}
	
}
