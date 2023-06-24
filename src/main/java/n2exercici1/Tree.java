package n2exercici1;

import java.util.Objects;

public class Tree extends Product{

	//---ATTRIBUTES---
	private static int count = 1;
	private float height;
	
	//---CONSTRUCTORS---
	public Tree(float height, float price, int ammount) {
		super(price, ammount);
		this.height = height;
		this.id = "T"+count;
		count++;
	}

	public Tree(float price, int ammount, String id) {
		super(price, ammount);
		this.id = id;
	}
	
	public Tree() {
		
	}
	
	//---GETTERS & SETTERS
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
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
		return Float.floatToIntBits(height) == Float.floatToIntBits(other.height);
	}

	//---VIEW---
	@Override
	public String toString() {
		return "Tree height: " + height + ", price:" + price+", ammount:"+amount;
	}
}
