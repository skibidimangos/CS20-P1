package Mastery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Life {
	
	public static Scanner UserInput = new Scanner(System.in);
	
	public static class CellManager {
		
		private static ArrayList<Cell> StoredCells = new ArrayList<Cell>();
		
		
		public static ArrayList<Cell> getStoredCells() {
			
			return StoredCells;
			
		}
	
		public static boolean storeCell (Cell newCell) {
			
			if (StoredCells.contains(newCell)) {
				
				return false;
				
			}
			
			StoredCells.add(newCell);
			
			return true;
			
		}
		
		public static void removeCell (Cell removedCell) {
			
			if (StoredCells.contains(removedCell)) {
			
				StoredCells.remove(removedCell);
				
				return;
			
			}
			
			System.out.println("Cell does not exist in storage.");
		}
		
		public static int cellQuantity() {
			
			return StoredCells.size();
			
		}
		
		public static void updateAlive() {
			
			ArrayList<Cell> AliveCells = StoredCells.stream()
										.filter(cell -> cell.isAlive())
										.filter(cell -> cell.ne)
			
		}
	
	}
	
	public static class Cell {
		
		private ArrayList<Cell> Neighbors = new ArrayList<>();
		
		private int NeighborCount = 0;
		
		private int[] Coordinates = new int[2];
		
		private boolean Alive = false;
		
		public Cell(int[] Coordinates) {
			
			this.Coordinates = Coordinates;
			
		}
		
		public void checkNeighbors(String[][] Grid) {
		
			
			int[][] Possibilities = {
					{0,1}, {0, -1},
					
					{1, 0}, {-1, 0},
					
					{1, 1}, {-1, -1},
					
					{-1, 1}, {1, -1}
			};
			
			ArrayList<int[]> SurroundingCoordinates = new ArrayList<>();
			
			int OriginX = this.getLocation()[0];
			
			int OriginY = this.getLocation()[1];
			
			for (int[] Possibility: Possibilities) {
				
				int NewX = OriginX + Possibility[0];
				
				int NewY = OriginY + Possibility[1];
				
				if (NewX >= 0 && NewX < 20 && NewY >= 0 && NewY < 20) {
					
					SurroundingCoordinates.add(new int[]{NewX, NewY});
					
				}
				
				
			}
			
			
			for (int[] Coordinate: SurroundingCoordinates) {
				
				Cell RetrievedCell = Grids.findCell(Coordinate);
				
				if (RetrievedCell != null) {
					
					Neighbors.add(RetrievedCell);
					
				}
				
			}
			
			this.updateNeighborCount();
			
		}
		
		public void updateAlive(boolean Enabled) {
			
			this.Alive = Enabled;
			
		}
		
		public void updateNeighborCount() {
			
			this.NeighborCount = this.Neighbors.size();
			
		}
		
		public int[] getLocation() {
			
			return this.Coordinates;

		}
		
		public boolean isAlive() {
			
			return Alive;
			
		}
		
		public int getNeighborCount( ) {
			
			return this.NeighborCount;
			
		}
		
	}
	
	public static class Grids {
		
		
		private static String[][] PastGrid = new String[20][20];
		
		private static String[][] NewGrid = new String[20][20];
		
		
		public static void displayGrid(String[][] Grid) {
			
			for (int row = 0; row < Grid.length; row++) {
				
				  for (int col = 0; col < Grid[row].length; col++) {
					  
					  System.out.print(Grid[row][col]);
					  
					  
			    }
				  
				System.out.println();
				  
			}
			
		}
		
		public static void updateGrid(String[][] Grid) {
			

			for (int Row = 0; Row < Grid.length; Row++) {
				
				  for (int Col = 0; Col < Grid[Row].length; Col++) {
					  
					  Cell RetrievedCell = findCell(new int[] {Row, Col});
						  
					  if (RetrievedCell != null && RetrievedCell.isAlive()) {
						  
						  Grid[Row][Col] = "X";
						  
					  } else {
						  
						  Grid[Row][Col] = "0";
						  
					  }
					  
					  
			    }
				  
			}
			
			
		}
		
		
		public static boolean gridContainsCell(String[][] Grid, int[] Coordinates) {
			
			int Col = Coordinates[0];
			
			int Row = Coordinates[1];
			
			if (Grid[Row][Col].equals("X")) {
				
				return true;
				
			}
			
			return false;
			
		}
		
		public static Cell findCell(int[] Coordinates) {
			
			Cell SelectedCell = CellManager.getStoredCells()
								.stream()
								.filter(cell -> Arrays.equals(cell.getLocation(), Coordinates))
								.findFirst()
								.orElse(null);
			
			if (SelectedCell != null) {
				
				return SelectedCell.isAlive();
				
			}
			
		}
		
		
	}
	
	public static int[] getInputCoordinates(boolean canConfirm) {
		
		String Input = UserInput.nextLine();
		
		try {
			
			if (Input.equalsIgnoreCase("C") && canConfirm) {
				
				return new int[] {-1, -1};
				
			}
			
			if (Input.contains(",") == false) {
				
				System.out.println("Error: Input must contain comma.");
				
				return null;
				
			}
			
			int[] Coordinates = Arrays.stream(Input.split(","))
												.map(String::strip)
												.mapToInt(Integer::parseInt)
												.filter(num -> num >= 0 && num <= 19)
												.toArray();
			
			if (Coordinates.length != 2) {
				
				System.out.println("Error: Input must feature two axises / Error: Integers must be in the range 0 - 19");
				
				return null;
				
			}
			
			return Coordinates;
				
		} catch (NumberFormatException e) {
			
			System.out.println("Error: Input values must be integers besides for comma.");
			
		}
		
		return null;
		
	}
	
	public static void getStartingCells() {
		
		System.out.println("Enter Coordinates for a cell");
		System.out.println("Format: 5,8 (x = 5, y = 8)");
		
		while (CellManager.cellQuantity() < 1) {
			
			int[] Coordinates = getInputCoordinates(false);
			
			if (Coordinates == null) {
				
				continue;
				
			}
			
			Cell NewCell = new Cell(Coordinates);
			
			CellManager.storeCell(NewCell);
			
		}
		
		while (true) {
			
			System.out.println("Enter Coordinates for a cell");
			
			System.out.println("Enter 'C' to confirm.");
			
			int[] Coordinates = getInputCoordinates(true);
			
			if (Coordinates == null) {
				
				continue;
				
 			} else if (Arrays.equals(Coordinates, new int[] {-1,-1})) {
 				
 			    return;
 			    
 			}
			
			Cell NewCell = new Cell(Coordinates);
			
			boolean Success = CellManager.storeCell(NewCell);
			
			if (Success == false) {
				
				System.out.println("Cell with coordinates: " + Arrays.toString(Coordinates) + " already exist.");
				
			}
			
		}
		
		
	}
	
	public static void newDay() {
		
		
		
	}

	public static void main(String[] args) {
		
		getStartingCells();
		
		Grids.updateGrid(Grids.PastGrid);
		
		Grids.displayGrid(Grids.PastGrid);

	}

}
