package SkillBuilders;

import java.util.Arrays;

public class AddData {
	
	 public static void main(String[] args) {
	
		int[] OriginalArray = {1, 2, 3, 4, 5, 6};
		
		int NewData = 1;
		
		int[] NewArray = new int[OriginalArray.length + 1];
		
		NewArray[0] = NewData;
		
		for (int i = 1; i < NewArray.length; i++) {
			
			NewArray[i] = OriginalArray[i - 1];
			
		}
		
		System.out.println(Arrays.toString(OriginalArray));
		
		System.out.println(Arrays.toString(NewArray));
	
	 }

}
