package SkillBuilders;

public class Reverse {

	public static void main(String[] args) {
		int[] Countdown = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		System.out.println("Countdown");
		
		for (int i = Countdown.length - 1; i >= 0; i--) {
			
			System.out.println(Countdown[i]);
			
		}

	}

}
