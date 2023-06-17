package n1exercici1;

public class Product implements Persistent{

	//---ATTRIBUTES---
	protected double price;
	protected String id;
	protected int ammount;
	
	//---CONSTRUCTOR---
	public Product(double price, int ammount) {
		this.price = price;
		this.ammount = ammount;
	}
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
	
	public void decreaseAmmount() {
		ammount--;
	}
	
	public void decreaseAmmount(int ammount) {
		this.ammount -= ammount;
	}
	@Override
	public String toData() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void fromData(String data) {
		// TODO Auto-generated method stub
		
	}
}
