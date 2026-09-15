package SkillBuilders;

import java.util.ArrayList;
import java.util.Scanner;

import java.text.NumberFormat;

public class GradeAvg {

	public static void main(String[] args)
	{
		
		Scanner userinput = new Scanner(System.in);
		
		ArrayList<Integer> Gradelist = new ArrayList<>(); // Arraylists are required to add elements
		
		System.out.println("Enter your grades ('proceed' to complete the program)");
		
		while (true) {
			
			String input = userinput.next();
			
			try {
		        Gradelist.add(Integer.parseInt(input));
		        
			} catch (NumberFormatException e) {
				if (input.toLowerCase().equals("proceed")) {
					
					break;
				}
			    System.out.println("Invalid input");
			} catch (ClassCastException e) {
			    System.out.println("Invalid input, only integers allowed.");
			}
			
		}
		
		userinput.close();
		
		int sum = 0;

		for (int Grade : Gradelist) {
		    sum += Grade;
		    
		}
		
		NumberFormat PercentFormat = NumberFormat.getPercentInstance();
		
		double GradeAverage = (double) sum / Gradelist.size(); // have to specify division with a double value
		
		System.out.println("The Average of the entered grades is: " + PercentFormat.format(GradeAverage / 100));
		
	}
	
}
