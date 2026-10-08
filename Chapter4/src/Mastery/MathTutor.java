package Mastery;

import java.util.Random;

import java.util.Scanner;

import java.util.ArrayList;

import java.util.HashMap;

public class MathTutor {
	
	static Random random = new Random();
	
	static int GenerateNumber(int min, int max) {
		
		return random.nextInt(min, max + 1);
		
	}
	
	static void InsertValues(HashMap<String,String> hm) {
		
		hm.put("Addition", "+");
		
		hm.put("Subtraction", "-");
		
		hm.put("Multiplication", "*");
		
		hm.put("Division", "/");
		
	}
	
	static String GenerateOperation() {
		
		String[] OperationList = {"Addition", "Subtraction", "Multiplication", "Division"};
		
		return OperationList[random.nextInt(0, OperationList.length)]; // Choose Random Operation
	}
	
	static Number CalculateAnswer(int[] Values, String Operation) {
		
		
		int Value1 = Values[0];
		
		int Value2 = Values[1];
		
		switch(Operation) {
		
		case "Addition":
		
			return Value1 + Value2;
			
		case "Subtraction":
		
			return Value1 - Value2;
			
		case "Multiplication":

			return Value1 * Value2;
			
		case "Division":

			return Math.round((double) Value1 / Value2 * 100) / 100.0;
		
			}

		
		return 0;
	}
	
	
	static double GetInput(Scanner Userinput) {
		
		while (true) {
			
			String input = Userinput.next();
			
			try {
				
				return Double.parseDouble(input);
				
				
			} catch (NumberFormatException e) {
				
				System.out.println("Invalid input, must be a number.");
			}
			
		}
		
	}

	public static void main(String[] args) {
		
		Scanner Userinput = new Scanner(System.in);
		
		HashMap<String, String> SymbolMap = new HashMap<String,String>();
		
		InsertValues(SymbolMap);
		
		int Points = 0;
		
		while (true) {
			
			System.out.println("Points: " + Points);
			
			String Operation = GenerateOperation();
			
			int Value1 = GenerateNumber(0,10);
			
			int Value2;
			
			switch (Operation) { // Can't divide by zero
			
			case "Division":
			
				Value2 = GenerateNumber(1,10);
				
				System.out.println("Answer to the nearest hundredth");
			
			default:
			
				Value2 = GenerateNumber(0,10);
			}
			
			double AnswerKey = CalculateAnswer(new int[] {Value1, Value2}, Operation).doubleValue();
			
			System.out.println(Value1 + " " + SymbolMap.get(Operation) + " " + Value2);
			
			double UserAnswer = GetInput(Userinput);
			
			if (UserAnswer == AnswerKey) {
				
				System.out.println("Correct! \n");
				
				Points += 1;
				
				continue;
				
			}
			
			System.out.println("Incorrect. \n");
			
		}


	}

}
