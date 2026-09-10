package SkillBuilders;

import java.util.Scanner;

public class Rectangle {
	
	static void RequestPrint(String Text) {
		
		System.out.println(Text);
		System.out.println();
		
	}
	
	public static void main(String[] args)
	{
		

				
		//Assignment format: Type, Identifier, Optional[Value]
		
		int len;
		
		
		int width;
		
		// Create Scanner Object for User Input
		
		Scanner userinput = new Scanner(System.in);
		
		// Requesting Input
		
		RequestPrint("Enter a Width:");
		
		width = userinput.nextInt();
		
		RequestPrint("Enter a Length");
		
		len = userinput.nextInt();
		
		// Format String %[argument_index$][flags][width][.precision]conversion
		
		String Format = "A rectangle with a width of %d and a length of %d has an area of %d".formatted(width, len, width * len);
		
		System.out.println(Format);
		
		userinput.close();
		
		
		
	}
	
}
