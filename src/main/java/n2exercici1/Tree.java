package n2exercici1;

public class Tree extends Product{

	//---ATTRIBUTES---
	private static int count = 1;
	private double height;
	
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
	
	//---DATA CONTROL---
	@Override
	public String toString() {
		return "Tree height: " + height + ", price:" + price+", ammount:"+amount;
	}
}
