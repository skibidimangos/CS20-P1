package Mastery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Life {
	
	public static Scanner UserInput = new Scanner(System.in);
	
	public static class CellManager {
		
		public static Set<Integer> SurvivalThreshold = Set.of(2,3);
		
		public static Set<Integer> RevivalThreshold = Set.of(3);
		
		private static ArrayList<Cell> StoredCells = new ArrayList<Cell>();
		
		public static void initialize() {
			
			int Rows = Grids.getSize()[0];
			
			int Cols = Grids.getSize()[1];
			
			StoredCells = IntStream.range(0, Rows)
					.boxed()
					.flatMap(row -> IntStream.range(0, Cols)
							.mapToObj(col -> new Cell(col, row))
							)
					.collect(Collectors.toCollection(ArrayList::new));
								
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
			
			Cell FetchedCell = StoredCells.stream()
							.filter(cell -> Arrays.equals(cell.getLocation(), Coordinates))
							.findFirst()
							.orElse(null);
			
			if (FetchedCell == null) {
			
				System.out.println("Cell not found at: " + Arrays.toString(Coordinates));
				
			}
			
			return FetchedCell;
			
		}
		
		public static void updateNeighbors() {
			
			StoredCells.forEach(cell -> cell.updateNeighbours());
			
		}
		
		public static void updateAlive() {
			
			ArrayList<Cell> TurnDead = StoredCells.stream()
					.filter(cell -> cell.isAlive()) // is alive
					.filter(cell -> !SurvivalThreshold.contains(cell.getNeighborCount())) // does not have 2 or 3 neighbors
					.collect(Collectors.toCollection(ArrayList::new));
			
			ArrayList<Cell> TurnAlive = StoredCells.stream()
					.filter(cell -> !cell.isAlive()) // is not alive
					.filter(cell -> RevivalThreshold.contains(cell.getNeighborCount())) // has three neighbors
					.collect(Collectors.toCollection(ArrayList::new));
			
			TurnDead.forEach(cell -> cell.updateAlive(false));
			
			TurnAlive.forEach(cell -> cell.updateAlive(true));
			
		}
	
	}
	
	public static class Cell {
		
		private int NeighborCount = 0;
		
		private int[] Coordinates = new int[2];
		
		private boolean Alive = false;
		
		public Cell(int[] Coordinates) {
			
			this.Coordinates = Coordinates;
			
		}
		
		public Cell(int col, int row) {
			
			this.Coordinates = new int[] {col,row};
			
		}
		
		public void updateNeighbours() {
		
			int[][] Possibilities = {
					{0,1}, {0, -1},
					
					{1, 0}, {-1, 0},
					
					{1, 1}, {-1, -1},
					
					{-1, 1}, {1, -1}
			};
			
			int OriginX = this.getLocation()[0];
			
			int OriginY = this.getLocation()[1];
			
			int[] GridSize = Grids.getSize();
			
			ArrayList<int[]> SurroundingCoordinates = Arrays.stream(Possibilities)
														.map(Possibility -> new int[] {
															OriginX + Possibility[0], 
															OriginY + Possibility[1]
														})
														.filter(Possibility -> 
														(Possibility[0] >= 0 && Possibility[0] < GridSize[0]) &&
														(Possibility[1] >= 0 && Possibility[1] < GridSize[1])
														)
														.collect(Collectors.toCollection(ArrayList::new));
			
			this.NeighborCount = (int) CellManager.getStoredCells()
									.stream()
									.filter(cell -> cell.isAlive())
									.filter(cell -> SurroundingCoordinates.stream()
										.anyMatch(Coordinate -> 
										Arrays.equals(Coordinate, cell.getLocation()))
									)
									.count();
			
		}
		
		public void updateAlive(boolean Enabled) {
			
			this.Alive = Enabled;
			
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
			
			for (int Y = 0; Y < Grid[0].length; Y++) {
				
				  for (int X = 0; X < Grid.length; X++) {
					  
					  System.out.print(Grid[X][Y]);
					  
					  
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
		
		
	}
	
	public static int[] getInputCoordinates(boolean canConfirm) {
		
		String Input = UserInput.nextLine();
		
		try {
			
			if (Input.equalsIgnoreCase("c") && canConfirm) {
				
				return new int[] {-1, -1};
				
			}
			
			if (Input.contains(",") == false) {
				
				System.out.println("Error: Input must contain comma.");
				
				return null;
				
			}
			
			int[] Coordinates = Arrays.stream(Input.split(","))
												.map(String::strip)
												.mapToInt(Integer::parseInt)
												.toArray();
			
			if (Coordinates.length != 2) {
				
				System.out.println("Invalid Coordinates, only two values allowed.");
				
				return null;
				
			}
			
			int[] GridSize = Grids.getSize();
			
			if ((Coordinates[0] < 0 || Coordinates[0] > GridSize[0]) || 
				(Coordinates[1] < 0 || Coordinates[1] > GridSize[1])) {
				
				System.out.println("Invalid Coordinates, values must be within values 0 - " + GridSize[0] + ".");
				
				return null;
				
			}
			
			
			return Coordinates;
				
		} catch (NumberFormatException e) {
			
			System.out.println("Error: Input can only contain integers and commas.");
			
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
	
	public static void newDay() {
		
		CellManager.updateNeighbors();
		
		CellManager.updateAlive();
		
		Grids.updateGrid();
		
	}
	

	public static void main(String[] args) {
		
		CellManager.initialize();
		
		getStartingCells();
		
		Grids.updateGrid();
		
		Grids.displayGrid();
		
		while (true) {
			
			System.out.println("Enter anything to continue to the next day");
			
			UserInput.nextLine();
			
			newDay();
			
			Grids.displayGrid();
			
		}

	}

}
