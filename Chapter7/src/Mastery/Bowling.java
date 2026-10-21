package Mastery;

import java.util.ArrayList;

import java.util.Random;

public class Bowling {
	
	public static Random RandomClass = new Random();
	
	static class Bowler {
		
		private int PinsKnocked;
		
		private int Score;
		
		private int Throw;
		
		// Constructor
		
		public Bowler() {
			
			
		}
		
		// Access Methods
		
		public int getScore() {
			
			return this.Score;
			
		}
		
		public int getPinsKnocked() {
			
			return this.PinsKnocked;
			
		}
		
		public int getThrow() {
			
			return this.Throw;
			
		}
		
		// Modify Methods
		
		public void Bowl() {
			
			this.changePinsKnocked(RandomClass.nextInt(0, (10 - PinsKnocked) + 1), true);
			
			switch (Throw) {
			
			
				case 1:
					
					if (PinsKnocked == 10) {
						
						this.addScore(20);
						
						this.changeThrow(0);
						
						return;
						
					}
					
					this.changeThrow(2);
					
					return;
					
					
				case 2:
					
					if (PinsKnocked == 10) {
						
						this.addScore(15);
						
						this.changeThrow(0);
						
						return;
						
					}
					
					this.addScore(PinsKnocked);
					
					this.changeThrow(0);
				
					return;
					
				}
			
			
			
		}
		
		public void changePinsKnocked(int PinsKnocked, boolean Add) {
			
			if (Add) {
			
				this.PinsKnocked += PinsKnocked;
				
			} else {
				
				changePinsKnocked(PinsKnocked);
				
			}
			
		}
		
		public void changePinsKnocked(int PinsKnocked) {
			
			this.PinsKnocked = PinsKnocked;
			
		}

		
		public void addScore(int Score) {
			
			this.Score += Score;
			
		}
		
		public void changeThrow(int Throw) {
			
			this.Throw = Throw;
			
		}
		
	}

	public static void main(String[] args) {
		
		int Frames = 0;
		
		int Players = 4;
		
		ArrayList<Bowler> Bowlers = new ArrayList<Bowler>();
		
		
		
		for (int i = 0; i <= Players; i++) {
			
			Bowler newBowler = new Bowler();
			
			Bowlers.add(newBowler);
			
		}
		
		
		

	}

}
