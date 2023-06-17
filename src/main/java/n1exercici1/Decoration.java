package n1exercici1;

public class Decoration extends Product{

	//---ATTRIBUTES---
	private static int count = 1;
	private String material;
	
	//---CONSTRUCTORS---
	public Decoration(String material, double price, int ammount) {
		super(price, ammount);
		if(material.toUpperCase().equals("WOOD") || material.toUpperCase().equals("METAL")) {
			this.material = material;
		}else {
			System.out.println("The material is not correct. Material field empty");
			this.material = "";
		}
		this.id = count;
		count++;
	}
	
	public Decoration() {
		
	}

	//---SETTERS & GETTERS---
	public void setMaterial(String material) {
		if(material.toUpperCase().equals("WOOD") || material.toUpperCase().equals("METAL")) {
			this.material = material;
		}else {
			System.out.println("The material is not correct. Material field empty");
			this.material = "";
		} 
	}
	
	@Override
	public String toString() {
		return "Decoration material: " + material + ", price:" + price+", ammount:"+ammount;
	}

	//---PERSISTENCE---
	@Override
	public String toData() {
		return "Flower;"+material+";"+price+";"+ammount;
	}

	@Override
	public void fromData(String data) {
		String[] attributes = data.split(";");
		this.material = attributes[1];
		this.price = Double.parseDouble(attributes[2]);
		this.ammount = Integer.parseInt(attributes[3]);
	}
}
