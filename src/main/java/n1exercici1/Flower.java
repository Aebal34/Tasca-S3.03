package n1exercici1;

public class Flower extends Product{

	private static int count = 1;
	private String color;
	
	public Flower(String color, double price, int ammount) {
		super(price, ammount);
		this.color = color;
		this.id = count;
		count++;
	}
	
	
	@Override
	public String toString() {
		return "Flower color: " + color + ", price:" + price+", ammount:"+ammount;
	}
}
