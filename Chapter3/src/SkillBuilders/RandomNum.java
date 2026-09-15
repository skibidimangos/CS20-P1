package SkillBuilders;

import java.util.Scanner;

public class RandomNum {
	
	static void print(String input) {
		System.out.println(input);
	}
	
	static int GenerateRandom(int Min, int Max) {
		return (int) ((Max - Min + 1) * Math.random() + Min);
	}
	
	public static void main(String[] args)
	{
		
		Scanner userinput = new Scanner(System.in); //Ask for user input
		
		int Minimum;
		
		int Maximum;
		
		while (true) {
			try {
				
				print("Enter the inclusive minimum value.");
				
				Minimum = Integer.parseInt(userinput.next());
				
				print("Enter the inclusive maximum value.");
				
				Maximum = Integer.parseInt(userinput.next());
				
				print("Enter ('g') to generate a value. If you want to alter the range, enter ('n')");
				
				String Command = userinput.next();
				
				while (true) {
				
					if (Command.toLowerCase().equals("g")) {
						
						print("You got " + GenerateRandom(Minimum, Maximum) + " out of the range: " + Minimum + " - " + Maximum + ".");
						
						Command = userinput.next();
						
					} else if (Command.toLowerCase().equals("n")) {
						break;
					} else {
						
						print("Invalid input, enter ('g') or ('n').");
						
						Command = userinput.next();
					}
					
				}

				
			} catch (NumberFormatException e) {
				
				print("Invalid input, must be an integer between 1 and 5.");
			}
		}
		
	}
	
}
