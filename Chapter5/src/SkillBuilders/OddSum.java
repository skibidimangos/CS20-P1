package SkillBuilders;

import java.util.Scanner;

public class OddSum {
	
	static int GetInput() {
		
		Scanner Userinput = new Scanner(System.in);
		
		System.out.println("Enter an integer");
		
		while (true) {
			
			String input = Userinput.next();
			
			try {
				
				int Value = Integer.parseInt(input);
				
				Userinput.close();
				
				return Value;
				
				
			} catch (NumberFormatException e) {
				
				
			}
			
		}
		
	}

	public static void main(String[] args) {
		
		int Length = GetInput();
		
		int Sum = 0;
		
		for (int i = 1; i <= Length ; i++) {
			
			if ((i % 2) != 0) {
			
				System.out.println(i);
				
				Sum += i;
				
				System.out.println("Sum:" + Sum);
				
				continue;
			}
		}
	}
}
