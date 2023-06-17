package n1exercici1;

public class Tree extends Product implements Persistent{

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

	//---PERSISTENCE---
	@Override
	public String toData() {
		return "Tree;"+height+";"+price+";"+ammount+";"+id;
	}

	@Override
	public void fromData(String data) {
		String[] attributes = data.split(";");
		this.height = Double.parseDouble(attributes[1]);
		this.price = Double.parseDouble(attributes[2]);
		this.ammount = Integer.parseInt(attributes[3]);
		this.id = attributes[4];
	}
	
	//---DATA CONTROL---
	@Override
	public String toString() {
		return "Tree height: " + height + ", price:" + price+", ammount:"+ammount;
	}
}
