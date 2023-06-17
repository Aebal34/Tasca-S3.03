package n1exercici1;

public class Tree extends Product{

	private static int count = 1;
	private double height;
	
	public Tree(double height, double price, int ammount) {
		super(price, ammount);
		this.height = height;
		this.id = count;
		count++;
	}

	@Override
	public String toString() {
		return "Tree height: " + height + ", price:" + price+", ammount:"+ammount;
	}

	@Override
	public String toData() {
		return "Tree;"+height+";"+price+";"+ammount;
	}

	@Override
	public void fromData(String data) {
		String[] attributes = data.split(";");
		this.height = Double.parseDouble(attributes[1]);
		this.price = Double.parseDouble(attributes[2]);
		this.ammount = Integer.parseInt(attributes[3]);
	}
}
