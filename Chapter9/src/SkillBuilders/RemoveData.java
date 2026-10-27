package SkillBuilders;

import java.util.Arrays;

import java.util.ArrayList;

public class RemoveData {
	
	 public static void main(String[] args) {
	
		int[] OriginalArray = {1, 2, 3, 4, 5, 6};
		
		int DeletedElement = 1;
		
		int[] NewArray = new int[OriginalArray.length - 1];
		
		
		for (int n = 0, o = 0; n < NewArray.length;) {
			
			if (OriginalArray[o] == DeletedElement) {
				
				o++;
				
				continue;
				
			}
			
			NewArray[n] = OriginalArray[o];
			
			o++;
			n++;
			
		}
		
		System.out.println(Arrays.toString(OriginalArray));
		
		System.out.println(Arrays.toString(NewArray));
	
	 }

}
