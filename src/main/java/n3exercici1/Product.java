package n3exercici1;

import java.util.Objects;

public class Product{

	//---ATTRIBUTES---
	protected double price;
	protected String id;
	protected int amount;
	
	//---CONSTRUCTOR---
	public Product(double price, int amount) {
		this.price = price;
		this.amount = amount;
	}
	
	public Product(double price, int amount, String id) {
		this.price=price;
		this.amount=amount;
		this.id=id;
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
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	//---DATA CONTROL---
	public void increaseAmount(int amount) {
		this.amount += amount;
	}
	
	public void decreaseAmount(int amount) {
		this.amount -= amount;
	}

	@Override
	public String toString() {
		return "Product id: " + id + ", price:" + price + ", amount:" + amount;
	}
}
