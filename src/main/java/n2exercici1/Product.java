package n2exercici1;

public class Product{

	//---ATTRIBUTES---
	protected double price;
	protected String id;
	protected int ammount;
	
	//---CONSTRUCTOR---
	public Product() {
		
	}
	
	//---GETTERS & SETTERS---
	public double getPrice() {
		return price;
	}
	
	public String getId() {
		return id;
	}
	
	public int getAmmount() {
		return ammount;
	}
	
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
	
	//---DATA CONTROL---
	public void increaseAmmount() {
		ammount++;
	}
	
	public void increaseAmmount(int ammount) {
		this.ammount += ammount;
	}
	
	public void decreaseAmmount() {
		ammount--;
	}
	
	public void decreaseAmmount(int ammount) {
		this.ammount -= ammount;
	}
}
