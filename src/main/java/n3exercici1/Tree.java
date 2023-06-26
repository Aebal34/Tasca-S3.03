package n3exercici1;

import java.util.Objects;
import java.text.*;


public class Tree extends Product{

	//---ATTRIBUTES---
	private static int count = 1;
	private double height;
	private final DecimalFormat format = new DecimalFormat("#.##");
	
	//---CONSTRUCTORS---
	public Tree(double height, double price, int ammount) {
		super(price, ammount);
		this.height = height;
		this.id = "T"+count;
		count++;
	}

	public Tree() {
		
	}
	
	//---GETTERS & SETTERS
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int newCount) {
		count = newCount;
	}
	
	//Override HashCode and Equals to avoid having the same Trees duplicated
	@Override
	public int hashCode() {
		return Objects.hash(height);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tree other = (Tree) obj;
		return Double.doubleToLongBits(height) == Double.doubleToLongBits(other.height);
	}

	//---VIEW---
	@Override
	public String toString() {
		return "Tree "+id+"=height: " + format.format(height) + ", price:" + format.format(price)+", ammount:"+amount;
	}
}
