package Mastery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Life {
	
	public static Scanner UserInput = new Scanner(System.in);
	
	public static class CellManager {
		
		private static ArrayList<Cell> StoredCells = new ArrayList<Cell>();
		
		public static void initialize() {
			
			int Rows = Grids.getSize()[0];
			
			int Cols = Grids.getSize()[1];
			
			for (int a = 0; a < Rows; a++) {
				
				for (int b = 0; b < Cols; b++) {
					
					Cell newCell = new Cell(new int[] {a, b});
					
					storeCell(newCell);
					
				}
				
				
			}
			
										
										
			
		}
		
		
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
		
		public static Cell fetchCell(int[] Coordinates) {
			
			Cell FetchedCell = getStoredCells()
							.stream()
							.filter(cell -> Arrays.equals(cell.getLocation(), Coordinates))
							.findFirst()
							.orElse(null);
			
			if (FetchedCell == null) {
			
				System.out.println("Cell not found at: " + Coordinates.toString());
				
			}
			
			return FetchedCell;
			
		}
		
		public static void updateAlive() {
			
			ArrayList<Integer> LiveThreshold = new ArrayList<Integer>();
			
			ArrayList<Integer> ReviveThreshold = new ArrayList<Integer>();
			
			LiveThreshold.addAll(Arrays.asList(2,3)); /* Amount of neighbors for cell to come to survive */
			
			ReviveThreshold.addAll(Arrays.asList(3)); /* Amount of neighbors for cell to come to life */
			
			
			
			for (Cell cell : StoredCells) {
				
				cell.checkNeighbors();
				
				int NeighbourCount = cell.getNeighborCount();
				
				if (cell.isAlive()) {
					
					if (!LiveThreshold.contains(NeighbourCount)) {
						
						cell.updateAlive(false);
						
					}
					
					return;
					
				}
				
				if (!cell.isAlive()) {
					
					if (ReviveThreshold.contains(NeighbourCount)) {
						
						cell.updateAlive(true);
					}
					
				}
				
				
			}
			
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
		
		public void checkNeighbors() {
			
			Neighbors.clear();
		
			
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
			
			
			for (int[] Coordinates: SurroundingCoordinates) {
				
				Cell RetrievedCell = CellManager.fetchCell(Coordinates);
				
				if (RetrievedCell.isAlive()) {
					
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
		
		
		private static String[][] Grid = new String[20][20];
		
		
		public static void displayGrid() {
			
			for (int row = 0; row < Grid.length; row++) {
				
				  for (int col = 0; col < Grid[row].length; col++) {
					  
					  System.out.print(Grid[row][col]);
					  
					  
			    }
				  
				System.out.println();
				  
			}
			
		}
		
		public static int[] getSize() {
			
			int Rows = Grid.length;
					
			int Columns = Grid[0].length;
			
			return new int[] {Rows, Columns};
			
			
		}
		
		public static void updateGrid() {
			

			for (int Row = 0; Row < Grid.length; Row++) {
				
				  for (int Col = 0; Col < Grid[Row].length; Col++) {
					  
					  int[] Location = {Row,Col};
					  
					  Cell RetrievedCell = CellManager.fetchCell(Location);
						  
					  if (RetrievedCell.isAlive()) {
						  
						  Grid[Row][Col] = "X";
						  
					  } else {
						  
						  Grid[Row][Col] = "0";
						  
					  }
					  
					  
			    }
				  
			}
			
			
		}
		
		
		public static boolean gridContainsCell(int[] Coordinates) {
			
			int Col = Coordinates[0];
			
			int Row = Coordinates[1];
			
			if (Grid[Row][Col].equals("X")) {
				
				return true;
				
			}
			
			return false;
			
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
		
		ArrayList<Cell> AliveCells = CellManager.getStoredCells()
									.stream()
									.filter(cell -> cell.isAlive())
									.collect(Collectors.toCollection(ArrayList::new));
										
		
		while (AliveCells.size() < 1) {
			
			int[] Coordinates = getInputCoordinates(false);
			
			if (Coordinates == null) {
				
				continue;
				
			}
			
			Cell AliveCell = CellManager.fetchCell(Coordinates);
			
			AliveCell.updateAlive(true);
			
			AliveCells = CellManager.getStoredCells()
					.stream()
					.filter(cell -> cell.isAlive())
					.collect(Collectors.toCollection(ArrayList::new));
			
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
			
			Cell AliveCell = CellManager.fetchCell(Coordinates);
			
			AliveCell.updateAlive(true);
			
		}
		
		
	}
	

	public static void main(String[] args) {
		
		CellManager.initialize();
		
		getStartingCells();
		
		Grids.updateGrid();
		
		Grids.displayGrid();

	}

}
