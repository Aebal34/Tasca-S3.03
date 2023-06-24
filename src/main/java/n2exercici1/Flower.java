package n2exercici1;

public class Flower extends Product{

	//---ATTRIBUTES---
	private static int count = 1;
	private String color;
	
	//---CONSTRUCTORS---
	public Flower(String color, float price, int ammount) {
		super(price, ammount);
		this.color = color;
		this.id = "F"+count;
		count++;
	}
	
	public Flower(float price, int ammount, String id) {
		super(price, ammount);
		this.id = id;
	}
	
	public Flower() {
	}

	//---GETTERS & SETTERS
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	//---VIEW---
	@Override
	public String toString() {
		return "Flower color: " + color + ", price:" + price+", ammount:"+amount;
	}
}
