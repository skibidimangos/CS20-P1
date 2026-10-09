package SkillBuilders;

import java.util.HashMap;

public class SpanishNumbers {
	
	static String spanishTranslation(int n) {
		
		HashMap<Integer, String> Dictionary = new HashMap<Integer, String>();
		
		Dictionary.put(1, "uno");
		Dictionary.put(2, "dos");
		Dictionary.put(3, "tres");
		Dictionary.put(4, "cuatro");
		Dictionary.put(5, "cinco");
		Dictionary.put(6, "seis");
		Dictionary.put(7, "siete");
		Dictionary.put(8, "ocho");
		Dictionary.put(9, "nueve");
		Dictionary.put(10, "diez");
		
		return Dictionary.get(n);
		
		
	}

	public static void main(String[] args) {
		
		for (int i = 1; i <= 10; i++) {
		
			System.out.println(spanishTranslation(i));
		
		}

	}

}
