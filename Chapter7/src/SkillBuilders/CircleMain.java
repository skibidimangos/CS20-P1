package SkillBuilders;

public class CircleMain {
	
	public static class Circle {
		
		private double radius;
		
		// Constructor
		
		public Circle() { // Default constructor
			
			this.radius = 1;
			
		}
		
		public Circle(double radius) {
			
			this.radius = radius;
			
		}
		
		// Class Methods
		
		public static void displayAreaFormula() {
			
			System.out.println("Area = πr²");
			
		}
		
		// Instance Methods
		
		// Modifying
		
		public void setRadius(double newradius) {
			
			radius = newradius;
			
		}
		
		// Access
		
		public double getRadius() {
			
			return radius;
			
		}
		
		public double getArea() {
			
			return Math.PI * (radius * radius);
			
		}
		
		public double getCircumference() {
			
			return Math.round((2 * Math.PI * radius) * 100) / 100.0;
			
		}
		
		public String toString() {
			
			return "Circle object with radius: " + radius;
			
		}
		
		public boolean isEqual(Object Comparator) {
			
			try {
				
				Circle Circle2 = (Circle) Comparator;
				
				if (Circle2.getRadius() == radius) {
					
					return true;
					
				}
				
				
			} catch (ClassCastException e) {
				
				System.out.println("Comparator is not an instace of class Circle.");
				
			}
			
			return false;
			
			
		}
		
		
	}
	
	

	public static void main(String[] args) {
		
		Circle spot1 = new Circle(3);
		Circle spot2 = new Circle(4);
		
		 if (spot1.equals(spot2)) {
			 System.out.println("Objects are equal.");
		 } else {
			 System.out.println("Objects are not equal.");
		 }
		 
		 System.out.println(spot1);
		 System.out.println(spot2);

	}

}
