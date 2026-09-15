package SkillBuilders;

import java.util.Scanner;

import java.util.HashMap;

public class HurricaneClassifier {
	
	static void print(String input) {
		System.out.println(input);
	}
	
	public static void main(String[] args)
	{
		//Create HashMap for category attributes, ask user for value correlating to the key.
		HashMap<Integer, String> HurricaneData = new HashMap<Integer, String>();
		 
		//Insert Key Values (Category Number, Speed)
		
		HurricaneData.put(1, "74-95 mph, 64-82kt, or 119-153km/h");
		HurricaneData.put(2, "96-110 mph, 83-95 kt, or 154-177km/h");
		HurricaneData.put(3, "111-130 mph, 96-113 kt, or 178-209km/h");
		HurricaneData.put(4, "131-155 mph, 114-135 kt, or 210-249km/h");
		HurricaneData.put(5, "Greater than 155mph, 135 kt, or 249 km/hr");
		
		print("Enter a category number for hurricanes.");
		
		Scanner userinput = new Scanner(System.in); //Ask for user input
		
		while (true) {
			try {
				
				int CategoryNumber = userinput.nextInt();
				
				if (1 <= CategoryNumber && CategoryNumber <= 5) { // Checks if the input is between 1 and 5
					
					print("The wind speed of a category " + CategoryNumber + " Hurricane is:\n" + HurricaneData.get(CategoryNumber));

				} else {
					
					print("Invalid input, must be an integer between 1 and 5.");
				}
				
			} catch (NumberFormatException e) {
			    System.out.println("Invalid input, must be an integer between 1 and 5.");
			}
		}
		
	}
	
}
