package SkillBuilders;

import java.util.Scanner;

import java.util.HashMap;

public class TimeConverter {
	
	public class Converter {
		
		private static HashMap<String, Double> ConversionRatios = new HashMap<String, Double>();
		
		public static HashMap<String, String> UnitToName = new HashMap<String, String>();
		
		private static void insertRatios() {
			
			// Ratios based off when diving value //
			
			// Seconds //
			
			Converter.ConversionRatios.put("S/M", 60.0);
			
			Converter.ConversionRatios.put("S/H", 3600.0);
			
			Converter.ConversionRatios.put("S/D", 86400.0);
			
			// Minutes //
			
			Converter.ConversionRatios.put("M/S", (double) (1 / 60));
			
			Converter.ConversionRatios.put("M/H", 60.0);
			
			Converter.ConversionRatios.put("M/D", 1440.0);
			
			// Hours //
			
			Converter.ConversionRatios.put("H/S", (double) (1 / 3600));
			
			Converter.ConversionRatios.put("H/M", (double) (1 / 60));
			
			Converter.ConversionRatios.put("H/D", 60.0);
			
			// DAys //
			
			Converter.ConversionRatios.put("D/S", (double) (1 / 86400.0));
			
			Converter.ConversionRatios.put("D/M", (double) (1 / 1440.0));
			
			Converter.ConversionRatios.put("D/H", (double) (1 / 24));
			
		}
		
		private static void insertNames() {
			
			Converter.UnitToName.put("S", "Second(s)");
			
			Converter.UnitToName.put("M", "Minute(s)");
			
			Converter.UnitToName.put("H", "Hour(s)");
			
			Converter.UnitToName.put("D", "Day(s)");
			
		}
		
		public static String getUnit(Scanner Userinput) {
			
			HashMap<Integer, String> IndexToUnit = new HashMap<Integer, String>();
			
			IndexToUnit.put(1, "S");
			
			IndexToUnit.put(2, "M");
			
			IndexToUnit.put(3, "H");
			
			IndexToUnit.put(4, "D");
			
			System.out.println("Enter the index of the unit to be converted: ");
			
			System.out.println("1. Seconds");
			
			System.out.println("2. Minutes");
			
			System.out.println("3. Hours");
			
			System.out.println("4. Days");
			
			while (true) {
				
				String Input = Userinput.next();
				
				try {
					
					int Index = Integer.parseInt(Input);
					
					if (IndexToUnit.containsKey(Index)) {
						
						return IndexToUnit.get(Index);
				
					}
					
					
				} catch(Exception e) {
					
					System.out.println("Invalid input, has to be an int from 1 - 3");
					
				}
				
				
			}
			
		}
		
		public static Double getValue(Scanner Userinput) {
			
			System.out.println("Enter quantity: ");
			
			while (true) {
				
				String Input = Userinput.next();
				
				try {
					
					return Double.parseDouble(Input);
					
					
				} catch(Exception e) {
					
					System.out.println("Invalid input, has to be a number");
					
				}
				
				
			}
		}
		
		public static Double CalculateConversion(Double Amount1, String Conversion) {
			
			double ConversionRatio = Converter.ConversionRatios.get(Conversion);
			
			return Amount1 / ConversionRatio;
			
		}
		
	}
	
	public static void main(String args[]) {
		
		Scanner Userinput = new Scanner(System.in);
		
		Converter.insertRatios();
		
		Converter.insertNames();
		
		while (true) {
			
			String Unit1 = Converter.getUnit(Userinput);
			
			Double Amount = Converter.getValue(Userinput);
			
			String Unit2 = Converter.getUnit(Userinput);
			
			String Conversion = Unit1 + "/" + Unit2;
			
			String UnitName1 = Converter.UnitToName.get(Unit1);
			
			String UnitName2 = Converter.UnitToName.get(Unit2);
			
			System.out.println(Amount + " " + UnitName1 + " is equal to " + 
			Math.round(Converter.CalculateConversion(Amount, Conversion) * 100) / 100 + " " + UnitName2);

			
		}
				
		
	}
	

}
