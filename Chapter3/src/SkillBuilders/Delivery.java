package SkillBuilders;

import java.util.Scanner;

import java.util.HashMap;

import java.util.ArrayList;

public class Delivery {
	
	static void print(String input) {
		System.out.println(input);
	}
	
	public static void main(String[] args)
	{
		//Create HashMap for dimension requirements.
		HashMap<Integer, Integer> DimensionsReq = new HashMap<Integer, Integer>();
		
		 
		//Insert Key Values (Dimension, Limit)
		
		DimensionsReq.put(1, 10); // Length
		
		DimensionsReq.put(2, 10); // Width
		
		DimensionsReq.put(3, 10); // Height
		
		Scanner userinput = new Scanner(System.in); //Ask for user input
		
		print("Enter the dimensions of your package divided by commas. (Length, Width, Height)");
		
		while (true) {
			try {
				
				String input = userinput.next();
				
				String[] InputDimensions = input.split(",");
				
		        if (InputDimensions.length != 3) {
		            print("Please enter exactly 3 dimensions.");
		            continue;
		        }
				
				ArrayList<Integer> Dimensions = new ArrayList<>();
				
				for (String Dimension : InputDimensions) {
					
					int value = Integer.parseInt(Dimension.trim());
					
		            if (value < 1 || value > 10) {
		                print("Each dimension must be between 1 and 10.");
		                Dimensions.clear();
		                break;
		            }
		            
		            Dimensions.add(value);
				}
				
				
				for (int i = 1; i <= 3; i += 1) {
					
					
					
					
				}
				
						
				
				input = userinput.next();
				
			} catch (NumberFormatException e) {
			   print("Invalid input, must be an integer between 1 and 5.");
			}
		}
		
	}
	
}
