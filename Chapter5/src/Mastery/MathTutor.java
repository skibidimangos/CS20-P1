package Mastery;

import java.util.Random;

import java.util.Scanner;

import java.util.ArrayList;

public class MathTutor {
	
	static Random random = new Random();
	
	static int GenerateNumber(int min, int max) {
		
		return random.nextInt(min, max + 1);
		
	}
	
	static String GenerateOperation() {
		
		String[] OperationList = {"Addition", "Subtraction", "Multiplication", "Division"};
		
		return OperationList[random.nextInt(0, OperationList.length)]; // Choose Random Operation
	}
	
	static String translateToSymbol(String Operation) {
		
		if (Operation.equals("Addition")) {
			return "+";
			
		}
		
		if (Operation.equals("Subtraction")) {
			return "-";
			
		}
		
		if (Operation.equals("Multiplication")) {
			return "*";
			
		}
		
		if (Operation.equals("Division")) {
			
			return "/";
		}
		
		return null;
	}
	
	static Number CalculateAnswer(ArrayList<Integer> Values, String Operation) {
		
		
		int Value1 = Values.get(0);
		
		int Value2 = Values.get(1);
		
		if (Operation.equals("Addition")) {
			return Value1 + Value2;
			
		}
		
		if (Operation.equals("Subtraction")) {
			return Value1 - Value2;
			
		}
		
		if (Operation.equals("Multiplication")) {
			return Value1 * Value2;
			
		}
		
		if (Operation.equals("Division")) {
			return (double) Value1 / Value2;
			
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
		
		int Points = 0;
		
		while (true) {
			
			System.out.println("Points: " + Points);
			
			ArrayList<Integer> Values = new ArrayList<Integer>();
			
			Values.add(GenerateNumber(0,10));
			
			Values.add(GenerateNumber(0,10));
			
			String Operation = GenerateOperation();
			
			double AnswerKey = CalculateAnswer(Values, Operation).doubleValue();
			
			System.out.println(Values.get(0) + " " + translateToSymbol(Operation) + " " + Values.get(1));
			
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
