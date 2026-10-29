package Mastery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Life {
	
	public static Scanner UserInput = new Scanner(System.in);
	
	public static class CellManager {
		
		public static ArrayList<Cell> StoredCells = new ArrayList<Cell>();
	
		public boolean storeCell (Cell newCell) {
			
			int[] NewCoordinates = newCell.getLocation();
			
			for (Cell cell: StoredCells) {
				
				int[] StoredCoordinates = cell.getLocation();
				
				if (Arrays.equals(StoredCoordinates, NewCoordinates)) {
					
					return false;
					
				}
				
			}
			
			StoredCells.add(newCell);
			
			return true;
			
		}
	
	}
	
	public static class Cell {
		
		private ArrayList<Cell> Neighbors = new ArrayList<>();
		
		private int NeighborCount = 0;
		
		private int[] Coordinates = new int[2];
		
		private boolean AliveYesterday = false;
		
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
		
		public void updateAliveYesterday(boolean Enabled) {
			
			this.AliveYesterday = Enabled;
			
		}
		
		public void updateNeighborCount() {
			
			this.NeighborCount = this.Neighbors.size();
			
		}
		
		public int[] getLocation() {
			
			return this.Coordinates;

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
						  
					  if (RetrievedCell != null) {
						  
						  Grid[Row][Col] = "X";
						  
					  } else {
						  
						  Grid[Row][Col] = "0";
						  
					  }
					  
					  
			    }
				  
			}
			
			
		}
		
		public static void updateAliveBefore() {
			
			ArrayList<int[]> PastCells = new ArrayList<int[]>();
			
			ArrayList<int[]> NewCells = new ArrayList<int[]>();
			
			for (int Row = 0; Row < PastGrid.length; Row++) {
				
				  for (int Col = 0; Col < PastGrid[Row].length; Col++) {
					  
					  int[] Coordinates = {Row, Col};
					  
					  if (gridContainsCell(PastGrid, Coordinates)) {
						  
						  PastCells.add(Coordinates);
						  
					  } 
					  
			    }
				  
			}
			
			for (int Row = 0; Row < NewGrid.length; Row++) {
				
				  for (int Col = 0; Col < NewGrid[Row].length; Col++) {
					  
					  int[] Coordinates = {Row, Col};
					  
					  if (gridContainsCell(NewGrid, Coordinates)) {
						  
						  PastCells.add(Coordinates);
						  
					  } 
					  
			    }
				  
			}
			
			for (int Cell = 0; Cell < PastCells.size() ;){
				
				int[] PastCell = PastCells.get(Cell);
				
				if (NewCells.contains(PastCell)) {
					
					Cell RetrievedCell = findCell(PastCell);
					
					RetrievedCell.updateAliveYesterday(true);
					
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
			
			for (Cell cell: CellManager.StoredCells) {
			
				
				int CellCol = cell.getLocation()[0];
				
				int CellRow = cell.getLocation()[1];
				
				int Col = Coordinates[0];
				
				int Row = Coordinates[1];
				
				if (CellRow == Col && CellCol == Row) {
					
					return cell;
					
				}
				
			}
				
				return null;
			
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
			
			String[] StringCoordinates = Input.split(",");
			
			int[] Coordinates = new int[2];
			
			if (StringCoordinates.length != 2) {
				
				System.out.println("Error: Input must feature two axises");
				
				return null;
				
			}
			
			for (int i = 0; i < 2; i ++) {
				
				int Value = Integer.parseInt(StringCoordinates[i]);
				
				if (Value >= 0 && Value < 20) {
					
					Coordinates[i] = Integer.parseInt(StringCoordinates[i]);
					
				} else {
					
					System.out.println("Coordinate values must be in the range 0 - 19.");
					
					return null;
					
				}
				
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
		
		while (CellManager.StoredCells.size() < 1) {
			
			int[] Coordinates = getInputCoordinates(false);
			
			if (Coordinates == null) {
				
				continue;
				
			}
			
			Cell NewCell = new Cell(Coordinates);
			
			CellManager.StoredCells.add(NewCell);
			
		}
		
		while (true) {
			
			System.out.println("Enter Coordinates for a cell");
			
			System.out.println("Enter 'C' to confirm.");
			
			int[] Coordinates = getInputCoordinates(true);
			
			if (Coordinates == null) {
				
				continue;
				
 			} else if (Coordinates[0] == -1 && Coordinates[1] == -1) {
 				
 			    return;
 			    
 			}
			
			Cell NewCell = new Cell(Coordinates);
			
			boolean Success = CellManager.StoredCells.add(NewCell);
			
			if (Success == false) {
				
				Sys
				
			}
			
		}
		
		
	}


		

	public static void main(String[] args) {
		
		getStartingCells();
		
		Grids.updateGrid(Grids.PastGrid);
		
		Grids.displayGrid(Grids.PastGrid);


	}

}
