package n1exercici1;

public class Decoration extends Product{

	private static int count = 1;
	private String material;
	
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
}
