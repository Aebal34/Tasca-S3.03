package n2exercici1;

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

	public Tree() {
		
	}
	
	//---GETTERS & SETTERS
	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	
	//---VIEW---
	@Override
	public String toString() {
		return "Tree height: " + height + ", price:" + price+", ammount:"+amount;
	}
}
