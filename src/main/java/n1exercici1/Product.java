package n1exercici1;

public abstract class Product {

	//---ATTRIBUTES---
	protected double price;
	protected int id;
	protected int ammount;
	
	//---CONSTRUCTOR---
	public Product(double price, int ammount) {
		this.price = price;
		this.ammount = ammount;
	}
	
	//---GETTERS & SETTERS---
	public double getPrice() {
		return price;
	}
	
	public int getId() {
		return id;
	}
	
	public int getAmmount() {
		return ammount;
	}
	
	//---DATA CONTROL---
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
	
	//PERSISTENCE
	public abstract String toData();
	
	public abstract void fromData(String data);

}
