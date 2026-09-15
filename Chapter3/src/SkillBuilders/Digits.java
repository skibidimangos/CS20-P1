package SkillBuilders;

import java.util.Scanner;

public class Digits {
	
	public static void main(String[] args)
	{
		
		//Ask User for input, type cast the input into a string, convert string into array, instance loop over the array to print both digits.
		
		Scanner userinput = new Scanner(System.in);
		
		System.out.println("Enter a float value");
		
		String Num;
		
		while (true) {
		
			Num = userinput.next();

			try {
			    if (Num.contains(".")) {
			        Float.parseFloat(Num);

			    } else {
			        Integer.parseInt(Num);

			    }
			    
		        break;
		        
			} catch (NumberFormatException e) {
			    System.out.println("Invalid input");
			}
			
		}
		
		userinput.close();
		
		char[] NumArray = Num.replace(".", "").toCharArray();
		
		String Digits = "";
		
		for (int i = 0; i < NumArray.length; i++) { //Enumerated For Loop
		    // i = index
		    // array[i] = value
			if (i < (NumArray.length - 1)) {
				Digits += NumArray[i] + ", ";
			} else {
				Digits += "and " + NumArray[i] + ".";
			}
		}
		
		System.out.println("Your value, " + Num + " contains the following digits: ");
		System.out.println(Digits);
		
	}
	
}
