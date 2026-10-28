package Mastery;

import java.util.ArrayList;

public class Life {
	
	public static ArrayList<Cell> Cells = new ArrayList<Cell>();
	
	public class Cell {
		
		private ArrayList<int[]> Neighbors = new ArrayList<>();
		
		private int NeighborCount = 0;
		
		private int[] Coordinates = new int[2];
		
		private boolean AliveYesterday = false;
		
		public Cell(int[] Coordinates) {
			
			this.Coordinates = Coordinates;
			
		}
		
		public void checkNeighbors() {
		
			
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
				
				if (Grid.containsCell(Coordinate)) {
					
					Neighbors.add(Coordinate);
					
				}
				
			}
		
			
		}
		
		public int[] getLocation() {
			
			return this.Coordinates;

		}
		
	}
	
	
	public class Grid {
		
		
		private String[][] PastGrid = new String[20][20];
		
		private String[][] NewGrid = new String[20][20];
		
		
		public static void displayGrid(String[][] Grid) {
			
			for (int row = 0; row < Grid.length; row++) {
				
				  for (int col = 0; col < Grid[row].length; col++) {
					  
					  System.out.print(Grid[row][col]);
					  
					  
			    }
				  
				System.out.println();
				  
			}
			
		}
		
		public static void updateGrid(String[][] Grid) {
			

			for (int row = 0; row < Grid.length; row++) {
				
				  for (int col = 0; col < Grid[row].length; col++) {
					  
					  if (containsCell(row, col)) {
						  
						  Grid[row][col] = "X";
						  
					  } else {
						  
						  Grid[row][col] = "0";
						  
					  }
					  
					  
			    }
				  
			}
			
			
		}
		
		public static boolean containsCell(int row, int col) {
			
			for (Cell cell: Cells) {
			
				
				int CellCol = cell.getLocation()[0];
				
				int CellRow = cell.getLocation()[1];
				
				if (CellCol == col && CellRow == row) {
					
					return true;
					
				}
				
			}
				
				return false;
			
		}
		
		public static boolean containsCell(int[] Coordinates) {
			
			for (Cell cell: Cells) {
			
				
				int CellCol = cell.getLocation()[0];
				
				int CellRow = cell.getLocation()[1];
				
				int Col = Coordinates[0];
				
				int Row = Coordinates[1];
				
				if (CellRow == Col && CellCol == Row) {
					
					return true;
					
				}
				
			}
				
				return false;
			
		}
		
		
	}


		

	public static void main(String[] args) {


	}

}
