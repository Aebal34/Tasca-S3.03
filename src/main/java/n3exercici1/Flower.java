package n3exercici1;

import java.text.DecimalFormat;
import java.util.Objects;

public class Flower extends Product{

	//---ATTRIBUTES---
	private static int count = 1;
	private String color;
	private final DecimalFormat format = new DecimalFormat("#.##");
	
	//---CONSTRUCTORS---
	public Flower(String color, double price, int ammount) {
		super(price, ammount);
		this.color = color;
		this.id = "F"+count;
		count++;
	}
	
	public Flower(double price, int ammount, String id) {
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

	public static int getCount() {
		return count;
	}
	
	public static void setCount(int newCount) {
		count = newCount;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(color);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flower other = (Flower) obj;
		return Objects.equals(color, other.color);
	}

	//---VIEW---
	@Override
	public String toString() {
		return "Flower color: " + color + ", price:" + format.format(price)+", ammount:"+amount;
	}
}
