package SkillBuilders;

import java.util.Scanner;

public class NumbersSum {
	
	static int GetInput() {
		
		Scanner Userinput = new Scanner(System.in);
		
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
		
		for (int i = 1; i <= 20; i++) {
			
			if ((i % 2) == 0) {
				
				System.out.println(i);
				
				continue;

			}
		}
	}
}
