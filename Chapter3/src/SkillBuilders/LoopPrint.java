package SkillBuilders;

import java.util.Scanner;

public class LoopPrint {

	public static void main(String[] args)
	{
		
		Scanner userinput = new Scanner(System.in);
		
		int Loop;
		
		System.out.println("Enter quantity of loops.");
		
		while (true) {
			
			try {
				Loop = Integer.parseInt(userinput.next());
				
				break;
		        
			} catch (NumberFormatException e) {
			    System.out.println("Invalid input, must be an integer");
			
			}
		}
		
		userinput.close();
		
		for (int i = 1; i <= Loop; i++) {
			
			System.out.println(i);
			
		}
	}
	
}
