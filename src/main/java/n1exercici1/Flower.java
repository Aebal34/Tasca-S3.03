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


	@Override
	public String toData() {
		return "Flower;"+color+";"+price+";"+ammount;
	}


	@Override
	public void fromData(String data) {
		String[] attributes = data.split(";");
		this.color = attributes[1];
		this.price = Double.parseDouble(attributes[2]);
		this.ammount = Integer.parseInt(attributes[3]);
	}
}
