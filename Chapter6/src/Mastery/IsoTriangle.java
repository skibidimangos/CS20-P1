package Mastery;

import java.util.Scanner;

public class IsoTriangle {
	
	public static int getInput(Scanner Userinput) {
		
		while (true) {
			
			String Input = Userinput.next();
			
			try {
				
				return Integer.parseInt(Input);

			} catch (Exception e) {
				
				System.out.println("Input must be an integer.");
				
			}
			
			
		}
		
	}
	
	public static int calculateSize(int Row) {
		
		return 1 + ((Row - 1) * 2);

	}
	
	public static int calculateSpaces(int CurrentRow, int SizeofLargestRow) {
		
		return Math.floorDiv(SizeofLargestRow, 2) - (CurrentRow - 1);
	}

	public static void main(String[] args) {
		
		Scanner Userinput = new Scanner(System.in);
		
		System.out.println("Enter the size of the triangle (in rows)");
		
		int Rows = getInput(Userinput);
		
		int MaxSize = calculateSize(Rows);
		
		for (int Row = 1; Row <= Rows; Row++) {
			
			String Spaces = " ".repeat(calculateSpaces(Row, MaxSize));
			
			String Size = "*".repeat(calculateSize(Row));
			
			System.out.println(Spaces + Size);
			
		}
				
	}

}
