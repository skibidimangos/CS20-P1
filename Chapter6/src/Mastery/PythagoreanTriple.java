package Mastery;

public class PythagoreanTriple {
	
	public static boolean isPerfectSquare(int Value) {
		
	    int Root = (int) Math.sqrt(Value);
	    return Root * Root == Value;
	    
	}
	
	public static boolean isPythagoreanTriple(int A, int B) {
		
		
		int ASquared = A * A;
		
		int BSqaured = B * B;
		
		int CSquared = ASquared + BSqaured;
		
		return isPerfectSquare(CSquared);
		
	}
	
	public static void main(String args[]) {
		
		int maxValue = 100; //Exclusive
		
		for (int a = 1; a < maxValue; a++) {
			
			for (int b = a + 1; b < maxValue; b++) { // loop through all B values for each A value.
				
				if (isPythagoreanTriple(a, b)) {
				
					System.out.println("A: " + a + "| B: " + b + " | C: " +  (int) Math.sqrt(a * a + b * b));
				}
				
			}
			
		}
	
	}

}
