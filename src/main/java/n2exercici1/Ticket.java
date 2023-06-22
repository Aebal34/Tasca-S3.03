package n2exercici1;

import java.util.*;

public class Ticket{

	//---ATTRIBUTES---
	private List<Product> items;
	private double value;
	private int id;
	private static int count = 1;
	
	//---CONSTRUCTORS---
	public Ticket(List<Product> items) {
		this.items = items;
		value = getItemsValue();
		this.id = count;
		count++;
	}
	
	public Ticket() {
		items = new ArrayList<Product>();
		value = 0;
	}
	
	//---GETTERS & SETTERS---
	private double getItemsValue() {
		double value = 0;
		for(Product item : this.items) {
			value += item.getPrice()*item.getAmmount();
		}
		return value;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getValue() {
		return value;
	}
	
	//---DATA CONTROL---
	public void addItem(String id, int ammount, Stock stock) {
		var item = stock.getProduct(id);
		if(ammount <= item.getAmmount()){
			stock.removeProduct(id, ammount);
			item.setAmmount(ammount);
			items.add(item);
		}
		value = getItemsValue();
	}
	
	public void printTicket() {
		
		System.out.println("Ticket: "+id+" { "+"\n");
		for(Product item : items) {
			System.out.println("	|"+item+"\n");
		}
		System.out.println("		|Total value: "+value+" }");
	}
	
	public void updateValue() {
		this.value = getItemsValue();
	}
}
