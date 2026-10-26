package SkillBuilders;

import java.util.Scanner;

public class Squares {
	
	public static void print(String string) {
		
		System.out.println(string);
		
	}

	public static void main(String[] args) {
		
		int[] Squares = new int[5];
		
		Scanner UserInput = new Scanner(System.in);
		
		for (int i = 0; i < Squares.length; ) {
			
			String Input = UserInput.next();
			
			try {
				
				Squares[i] = Integer.parseInt(Input);
				
				i++;
				
			} catch (NumberFormatException e) {
				
				print("Invalid input, must be integer." + e);
				
			}
			
			
		}
		
		for (int i = 0; i < Squares.length; i++) {
			
			int Root = Squares[i];
			
			print((i + 1) + ". " + (int) Math.pow(Root, 2.0));
			
		}
		
		UserInput.close();

	}

}
