package n1exercici1;

public class Flower extends Product  implements Persistent{

	//---ATTRIBUTES---
	private static int count = 1;
	private String color;
	
	//---CONSTRUCTORS---
	public Flower(String color, double price, int ammount) {
		super(price, ammount);
		this.color = color;
		this.id = "F"+count;
		count++;
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

	//---PERSISTENCE---
	@Override
	public String toData() {
		return "Flower;"+color+";"+price+";"+ammount+";"+id;
	}

	@Override
	public void fromData(String data) {
		String[] attributes = data.split(";");
		this.color = attributes[1];
		this.price = Double.parseDouble(attributes[2]);
		this.ammount = Integer.parseInt(attributes[3]);
	}
	
	//---DATA CONTROL---
	@Override
	public String toString() {
		return "Flower color: " + color + ", price:" + price+", ammount:"+ammount;
	}
}
