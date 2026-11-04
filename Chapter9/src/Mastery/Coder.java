package Mastery;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.util.ArrayList;

import java.util.Scanner;

public class Coder {
	
	public static StringBuilder sb;
	
	public static int ShiftAmount;
	
	public static Scanner UserInput = new Scanner(System.in);
	
	public static ArrayList<Integer> CapitalCodes = IntStream.range(65, 90 + 1)
							        .boxed()
							        .collect(Collectors.toCollection(ArrayList::new));

	public static ArrayList<Integer> UnderScoreCodes = IntStream.range(97, 122 + 1)
								    .boxed()
								    .collect(Collectors.toCollection(ArrayList::new));

	
	public static int Decode(int character) {
		
		boolean isCapital = CapitalCodes.contains(character);
		
		if (isCapital) {
			
			int Start = CapitalCodes.getFirst();
			
			int End = CapitalCodes.getLast();
			
			int NewCharacter = character - ShiftAmount;
			
			while (NewCharacter < Start) {
				
				int Offset = NewCharacter - Start;
				
				NewCharacter = End - Math.abs(Offset);
				
			}
			
			return NewCharacter;
			
		}
		
		int Start = UnderScoreCodes.getFirst();
		
		int End = UnderScoreCodes.getLast();
		
		int NewCharacter = character - ShiftAmount;
		
		while (NewCharacter < Start) {
			
			int Offset = NewCharacter - Start;
			
			NewCharacter = End - Math.abs(Offset);
			
		}
		
		return NewCharacter;
		
		
		
	}
	
	public static int Encode(int character) {
		
		boolean isCapital = CapitalCodes.contains(character);
		
		if (isCapital) {
			
			int Start = CapitalCodes.getFirst();
			
			int End = CapitalCodes.getLast();
			
			int NewCharacter = character + ShiftAmount;
			
			if (NewCharacter > End) {
				
				int Offset = NewCharacter - End;
				
				NewCharacter = Start + Math.abs(Offset) - 1;
				
			}
			
			return NewCharacter;
			
		}
		
		int Start = UnderScoreCodes.getFirst();
		
		int End = UnderScoreCodes.getLast();
		
		int NewCharacter = character + ShiftAmount;
		

		
		if (NewCharacter > End) {
			
			int Offset = NewCharacter - End;
			
			NewCharacter = Start + Math.abs(Offset) - 1;
			
		}
		
		return NewCharacter;
		
		
	}
	
	public static String ProcessString(String Message, String Mode) {
		
		int[] CharArray = null;
		
		switch (Mode) {
		
		case "encode":
		
			CharArray = Message.strip()
								.chars()
								.filter(chr -> CapitalCodes.contains(chr) 
										|| UnderScoreCodes.contains(chr))
								.map(chr -> Encode(chr))
								.toArray();
			
			break;
			
		case "decode":
			
			CharArray = Message.strip()
								.chars()
								.filter(chr -> CapitalCodes.contains(chr) 
										|| UnderScoreCodes.contains(chr))
								.map(chr -> Decode(chr))
								.toArray();
			
			break;
		
		}
			
		return sb.toString()
		
	}
	
	public static void getShift() {
		
		System.out.println("Input Shift Amount: ");
		
		while (true) {
			
			String Input = UserInput.next();
			
			try {
				
				ShiftAmount = Integer.parseInt(Input);
				
				return;
			
			} catch (NumberFormatException e) {
				
				System.out.println("Invalid input, must be an integer value.");
				
			}
			
		}
		
	}
	
	public static String getMessage() {
		
		System.out.println("Input Message: ");
		
		while (true) {
			
			String Message = UserInput.nextLine();
			
			sb = new StringBuilder(Message.length());
			
			return Message;
			
		}
		
	}
	
	public static String getMode() {
		
		System.out.println("Input Mode (Encode/Decode): ");
		
		while (true) {
			
			String Mode = UserInput.next();
			
			if (Mode.equalsIgnoreCase("Encode") || Mode.equalsIgnoreCase("Decode"));
			
				return Mode.toLowerCase();
			
		}
		
	}

	public static void main(String[] args) {
		
		while (true) {
		
			String Message = getMessage();
			
			String Mode = getMode();
			
			getShift();
			
			System.out.println(ProcessString(Message, Mode));
		
		}
		
		
	}

}
