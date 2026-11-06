package Mastery;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.util.ArrayList;
import java.util.Scanner;

public class Coder {
	
	public static int ShiftAmount;
	
	public static Scanner UserInput = new Scanner(System.in);
	
	public static ArrayList<Integer> UppercaseCharacters = IntStream.range(65, 90 + 1)
							        .boxed()
							        .collect(Collectors.toCollection(ArrayList::new));

	public static ArrayList<Integer> LowercaseCharacters = IntStream.range(97, 122 + 1)
								    .boxed()
								    .collect(Collectors.toCollection(ArrayList::new));
	
	public static ArrayList<Integer> AllCharacters = IntStream.range(20, 126 + 1)
									.boxed()
									.collect(Collectors.toCollection(ArrayList::new));
	
	public static ArrayList<Integer> SpecialCharacters = IntStream.of('\n', '\r')
														.boxed()
														.collect(Collectors.toCollection(ArrayList::new));
	
	
	public static int Shift(int character, String Mode) {
		
		if (SpecialCharacters.contains(character)) {
		    return character;
		}
		
		boolean isCapital = UppercaseCharacters.contains(character);
		
		boolean isUnderScore = LowercaseCharacters.contains(character);
		
	    int Start = isCapital ? UppercaseCharacters.getFirst() : 
	    			isUnderScore ? LowercaseCharacters.getFirst() : 
    				AllCharacters.getFirst();
	    
	    int Size = isCapital ? UppercaseCharacters.size() : 
	    			isUnderScore ? LowercaseCharacters.size() :
					AllCharacters.size();
    					
	    
	    int Operation = switch (Mode) {
	    	
	    	case "encode" -> 1;
	    	
	    	case "decode" -> -1;
	    	
	    	default -> throw new IllegalArgumentException("Unexpected value: " + Mode);
	    	
	    };
		
		int NewCharacter = Start + Math.floorMod(character + (ShiftAmount * Operation) - Start, Size);
		
		return NewCharacter;

	}
	
	public static String ProcessString(String Message, String Mode) {
		
		StringBuilder NewMessage = new StringBuilder();
		
		boolean isMultiLine = Message.lines().count() > 1;
		
		Message = isMultiLine ? Message : Message.strip();

		switch (Mode) {
		
		case "encode":
		
			Message.chars()
					.filter(chr -> AllCharacters.contains(chr)
					|| SpecialCharacters.contains(chr))
					.map(chr -> Shift(chr, Mode))
					.forEach(chr -> NewMessage.append((char) chr));
			
			break;
			
		case "decode":
			
			Message.chars()
					.filter(chr -> AllCharacters.contains(chr)
					|| SpecialCharacters.contains(chr))
					.map(chr -> Shift(chr, Mode))
					.forEach(chr -> NewMessage.append((char) chr));
			
			break;
		
		}
		

		return NewMessage.toString();
		
	}
	
	public static int getShift() {
		
		System.out.println("Input Shift Amount: ");
		
		while (true) {
			
			String Input = UserInput.nextLine()
									.strip();
			
			try {
				
				ShiftAmount = Integer.parseInt(Input);
				
				return ShiftAmount;
			
			} catch (NumberFormatException e) {
				
				System.out.println("Invalid input, must be an integer value.");
				
			}
			
		}
		
	}
	
	public static String getMessage() {
		
		System.out.println("Input Message: ");
		
		StringBuilder Message = new StringBuilder();
		
		while (UserInput.hasNextLine()) {
			
			String Input = UserInput.nextLine();
			
			boolean MessageEmpty = Message.toString()
										.isBlank();
			
			if (Input.isEmpty() && !MessageEmpty) {
				
				break;
				
			}
			
			Message.append(Input)
					.append("\n");
			
		}
		
		return Message.toString();
		
	}
	
	public static String getMode() {
		
		System.out.println("Input Mode (Encode/Decode): ");
		
		while (true) {
			
			String Mode = UserInput.nextLine()
									.strip();
			
			if (Mode.equalsIgnoreCase("Encode") || Mode.equalsIgnoreCase("Decode")) {
			
				return Mode.toLowerCase();
				
			}
			
				System.out.println("Invalid input, must be encode or decode.");
			
		}
		
	}

	public static void main(String[] args) {
		
		while (true) {
		
			String Message = getMessage();
			
			String Mode = getMode();
			
			ShiftAmount = getShift();
			
			System.out.println(ProcessString(Message, Mode));
		
		}
		
		
	}

}
