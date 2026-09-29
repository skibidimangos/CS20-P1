package Mastery;

public class TicTacToeBoard {
	
	static void VerticalLine(int Times) {
		
		VerticalLine(Times, false);
		
		
	}
	
	static void VerticalLine(int Times, boolean Center) {
		
		for (int i = 1; i <= Times; i++) {
			
			if (Math.ceil((double) Times / 2) == i && Center) {
				System.out.println("         X         ");
				continue;
			}
		
			System.out.println("    |          |    ");

		
		}
		
	}
	
	static void HorizontalIntersection() {
		String str = "-";
		
		String result = str.repeat(20);
		
		System.out.println(result);
	}
	public static void main(String[] args) {
		
		for (int i = 1; i <= 3; i++) {
			
			if (i == 2) {
				VerticalLine(3, true);
			} else {
				VerticalLine(3);
			}
			
			if (i != 3) {
				HorizontalIntersection();
			}
		
		}
		

	}

}
