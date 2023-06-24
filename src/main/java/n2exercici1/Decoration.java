package n2exercici1;

public class Decoration extends Product{

	//---ATTRIBUTES---
	private static int count = 1;
	private String material;
	
	//---CONSTRUCTORS---
	public Decoration(String material, float price, int ammount) {
		super(price, ammount);
		if(material.toUpperCase().equals("WOOD") || material.toUpperCase().equals("METAL")) {
			this.material = material;
		}else {
			System.out.println("The material is not correct. Material field empty");
			this.material = "";
		}
		this.id = "D"+count;
		count++;
	}
	
	public Decoration(float price, int ammount, String id) {
		super(price, ammount);
		this.id = id;
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
	
	public String getMaterial() {
		return material;
	}

	//---VIEW---
	@Override
	public String toString() {
		return "Decoration material: " + material + ", price:" + price+", ammount:"+amount;
	}
}
