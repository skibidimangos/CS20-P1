package SkillBuilders;

public class RectangleMain {
	
	public static class Rectangle {
		
		// Instance Variables
		
		private double width;
		
		private double length;
		
		// Constructors
		
		public Rectangle() {
			
			this.width = 1;
			
			this.length = 2;
			
		}
		
		public Rectangle(double width, double length) {
			
			this.width = width;
			
			this.length = length;
			
		}
		
		// Class methods
		
		public static void displayAreaFormula() {
			
			System.out.println("Area = lw");
			
		}
		
		// Instance Methods
		
		// Modify
		
		public void changeWidth(double newWidth) {
			
			width = newWidth;
			
		}
		
		public void changeLength(double newLength) {
			
			length = newLength;
			
		}
		
		// Access
		
		public double getWidth() {
			
			return width;
			
		}
		
		public double getLength() {
			
			return length;
			
		}
		
		public double getArea() {
			
			return width * length;
			
		}
		
		public double getCircumference() {
			
			return (width * 2) + (length * 2);
			
		}
		
		// Override
		
		public boolean equals(Object OtherObject) {
			
			try {
				
				Rectangle OtherRectangle = (Rectangle) OtherObject;
				
				if (OtherRectangle.getWidth() == width && OtherRectangle.getLength() == length) {
					
					return true;
					
				}
				
				
			} catch (ClassCastException e) {
				
				System.out.println("Comparator is not an instace of class Circle.");
				
			}
			
			return false;
			
		}
		
		public String toString() {
			
			return "Rectangle with length: " + length + " and width: " + width + ".";
			
		}
		
		
		
	}

	public static void main(String[] args) {
		
		Rectangle rect1 = new Rectangle(2, 3);
		Rectangle rect2 = new Rectangle(2, 3);
		
		 if (rect1.equals(rect2)) {
			 System.out.println("Objects are equal.");
		 } else {
			 System.out.println("Objects are not equal.");
		 }
		 
		 System.out.println(rect1);
		 System.out.println(rect2);
		

	}

}
