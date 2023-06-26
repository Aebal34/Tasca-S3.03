package n3exercici1;

import java.text.DecimalFormat;
import java.util.Objects;

public class Decoration extends Product{

	//---ATTRIBUTES---
	private static int count = 1;
	private String material;
	private final DecimalFormat format = new DecimalFormat("#.##");
	
	//---CONSTRUCTORS---
	public Decoration(String material, double price, int ammount) {
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
	
	public Decoration(double price, int ammount, String id) {
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
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int newCount) {
		count = newCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(material);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Decoration other = (Decoration) obj;
		return Objects.equals(material, other.material);
	}

	//---VIEW---
	@Override
	public String toString() {
		return "Decoration material: " + material + ", price:" + format.format(price)+", ammount:"+amount;
	}
}
