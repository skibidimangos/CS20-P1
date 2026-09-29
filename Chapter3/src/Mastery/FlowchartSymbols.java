package Mastery;

import java.util.HashMap;

public class FlowchartSymbols {
	
	static void Drawbase(int SpacesBefore) {
		

		
		String BlankSpace = " ".repeat(SpacesBefore);
				
		String Characters = "*********";
		
		System.out.println(BlankSpace + Characters);
		
	}
	
	static void Base(boolean isEllipse, boolean Top) {
		
		if (isEllipse) {
			
			Drawbase(2);
			
			return;
			
		}
		
		if (Top) {
			
			Drawbase(3);
			
			return;
			
		}
		
		Drawbase(0);
		
	}
	
	static void Drawside(HashMap<String, int[]> Pattern) {
		
		int[] LeftPattern = Pattern.get("Left");
		
		int[] RightPattern = Pattern.get("Right");
		
		for (int i = 0; i < LeftPattern.length; i++) {  // .size() is the same as .length
			
			int LeftSpaces = LeftPattern[i]; // .get is only used for lists
			
			int RightSpaces = RightPattern[i];
			
			String SpaceBefore = " ".repeat(LeftSpaces);
			
			String SpaceAfter = " ".repeat((RightSpaces - LeftSpaces) - 1); // subtracting 1 is necessary because of the added character "*"
			
			System.out.println(SpaceBefore + "*" + SpaceAfter + "*");
			
		}
		
		
		
	}
	
	static void Side(boolean isEllipse) {
		
		// Start/End symbol is similar to an ellipse, input symbol is similar to a parallelogram
		HashMap<String, int[]> Pattern = new HashMap<String, int[]>();
		
		if (isEllipse) {
			
			//Reaching the other side of the shape takes 10 characters
			
			Pattern.put("Left", new int[] {1,0,1});
			
			Pattern.put("Right", new int[] {11,12,11});
	
		} else {
		
			Pattern.put("Left", new int[] {2, 1,0});
			
			Pattern.put("Right", new int[] {11,10,9});
			
		}
		
		Drawside(Pattern);
		
		
	}
	
	static void DrawEllipse() {
		
		Base(true, true);
		
		Side(true);
		
		Base(true, false);
		
	}
	
	static void DrawParallelogram() {
		
		Base(false, true);
		
		Side(false);
		
		Base(false, false);
		
	}

	public static void main(String[] args) {
		
		
		DrawEllipse();
		
		DrawParallelogram();
		
		

	}

}
