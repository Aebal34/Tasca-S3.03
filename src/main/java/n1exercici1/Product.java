package n1exercici1;

public abstract class Product {

	protected double price;
	protected int id;
	protected int ammount;
	
	public Product(double price, int ammount) {
		this.price = price;
		this.ammount = ammount;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getId() {
		return id;
	}
	
	public int getAmmount() {
		return ammount;
	}
	
	public void increaseAmmount() {
		ammount++;
	}
	
	public void decreaseAmmount() {
		ammount--;
	}
	
	public void decreaseAmmount(int ammount) {
		if(this.ammount > ammount) {
			this.ammount -= ammount;
		}else if(this.ammount == ammount) {
			
		}
	}
}
