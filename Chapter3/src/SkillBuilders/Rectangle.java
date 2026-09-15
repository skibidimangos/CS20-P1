package SkillBuilders;

import java.util.Scanner;

public class Rectangle {
	
	public static void main(String[] args)
	{
	
		//Assignment format: Type, Identifier, Optional[Value]
		
		int len;
		
		
		int width;
		
		// Create Scanner Object for User Input
		
		Scanner userinput = new Scanner(System.in);
		
		// Requesting Input
		
		System.out.println("Enter a Width:");
		
		width = userinput.nextInt();
		
		System.out.println("Enter a Length");
		
		len = userinput.nextInt();
		
		// Format String %[argument_index$][flags][width][.precision]conversion
		
		String Format = "A rectangle with a width of %d and a length of %d has a circumference of %d".formatted(width, len, (2 * width) + (2 * len));
		
		System.out.println(Format);
		
		userinput.close();
		
		
		
	}
	
}
