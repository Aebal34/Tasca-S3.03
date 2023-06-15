package n1exercici1;

public class Decoration extends Product{

	private String material;
	
	public Decoration(String material, double price) {
		super(price);
		this.material = material;
	}
}
