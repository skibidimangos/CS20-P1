package SkillBuilders;

import java.util.Scanner;

public class CountLetters {
	
	public static Scanner Userinput = new Scanner(System.in);
	
	public static void print(String string) {
		
		System.out.println(string);
		
	}
	
	public static String getPhrase() {
		
		print("Enter a phrase.");
		
		while (true) {
			
			String Input = Userinput.nextLine();
			
			return Input;
			
		}
	}

	public static void main(String[] args) {
		
		final int CharOffset = 'A';
		
		int[] LetterCount = new int[26];
		
		char[] Phrase = getPhrase()
				.strip()
				.toUpperCase()
				.toCharArray();
		
		for (int i = 0; i < Phrase.length; i++) {
			
		    if (Phrase[i] >= 'A' && Phrase[i] <= 'Z') { // White space in the middle of the string

		        int IndexOfLetter = Phrase[i] - CharOffset;
		        LetterCount[IndexOfLetter] += 1;

		    }

		}
		
		for (int i = 0; i < LetterCount.length; i++) {
			
			print((char) (i + CharOffset) + " : " + LetterCount[i]);

		}
	}

}
