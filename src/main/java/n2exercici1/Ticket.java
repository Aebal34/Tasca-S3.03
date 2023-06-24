package n2exercici1;

import java.util.*;

public class Ticket{

	//---ATTRIBUTES---
	private List<Product> items;
	private double value;
	private int id;
	private static int count = 1;
	private Florist florist;
	
	//---CONSTRUCTORS---
	public Ticket(List<Product> items, Florist florist) {
		this.items = items;
		value = getItemsValue();
		this.id = count;
		count++;
		this.florist=florist;
	}
	
	public Ticket() {
		items = new ArrayList<Product>();
		value = 0;
	}
	
	//---GETTERS & SETTERS---
	private double getItemsValue() {
		double value = 0;
		for(Product item : this.items) {
			value += item.getPrice()*item.getAmount();
		}
		return value;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public double getValue() {
		return value;
	}
	
	public List<Product> getItems(){
		return items;
	}
	
	public Florist getFlorist() {
		return florist;
	}
	
	//---DATA CONTROL---
	public void addItem(String id, int ammount, Stock stock) {
		var item = stock.getProduct(id);
		if(ammount <= item.getAmount()){
			stock.removeProduct(id, ammount);
			item.setAmount(ammount);
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
