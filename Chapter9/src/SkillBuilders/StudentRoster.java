package SkillBuilders;

import java.util.Scanner;


public class StudentRoster {
	
	public static void print(String string) {
		
		System.out.println(string);
		
	}

	public static void main(String[] args) {
		
		int StudentRosterLength;
		
		Scanner Userinput = new Scanner(System.in);
		
		String[] StudentRoster = {};
		
		print("Enter length of student roster.");
		
		while (true) {
			
			String Input = Userinput.next();
			
			try {
				
				StudentRoster = new String[Integer.parseInt(Input)];
				
				break;
				
				
			} catch (NumberFormatException e) {
				
				print("Invalid input, must be an integer.");
				
			}
			
		}
		
		int CurrentStudent = 0;
		
		print("Enter Student");
		
			
		while (CurrentStudent < StudentRoster.length) {
		
			String Input = Userinput.next();
			
			try {
				
			    StudentRoster[CurrentStudent] = Input;
			    CurrentStudent++;
				
				
			} catch (Exception e) {
				
				print("Error: " + e);
				
			}
		
		
		}
		
		print("Student Roster:");
		
		for (int i = 0; i < StudentRoster.length; i++) {
			
			print((i + 1) + ". " + StudentRoster[i]);
			
		}
		
		Userinput.close();

	}

}
