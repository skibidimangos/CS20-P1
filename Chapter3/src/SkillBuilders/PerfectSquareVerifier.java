package SkillBuilders;

import java.util.Scanner;

import java.util.HashMap;

public class PerfectSquareVerifier {
	
	static void print(String text) {
		
		System.out.println(text);
	}
	
	public static HashMap<String,Object> getInput() {
		
		Scanner userinput = new Scanner(System.in);
		
		while (true) {
			
			String Input = userinput.nextLine();
			
			try {
				
				HashMap <String, Object> Data = new HashMap<>();
				
				Data.put("Product", Integer.parseInt(Input.trim()));
				
				Data.put("Root", Math.sqrt(Integer.parseInt(Input)));
				
				return Data;
				
				
			} catch (NumberFormatException e) {
			
				print("Invalid input, only integers are allowed.");
			
			}
			
		}
		
	}
	
	public static boolean IsInteger(double Root) {
		
		return (Root % 1) == 0;
		
	}

	public static void main(String[] args) {
		
		while (true) {
		
			HashMap<String, Object> Values = getInput();
			
			if (IsInteger((double) Values.get("Root"))) {
				print(Values.get("Product") + " is a perfect square!");
				
			} else {
				print(Values.get("Product") + " is not a perfect square!");
			}
		
		}

	}

}
