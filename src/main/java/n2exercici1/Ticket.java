package n2exercici1;

import java.util.*;

public class Ticket{

	//---ATTRIBUTES---
	private Set<Product> items;
	private float value;
	private int id;
	private static int count = 1;
	private int floristId;
	
	//---CONSTRUCTORS---
	public Ticket(Set<Product> items, int floristId) {
		this.items = items;
		value = getItemsValue();
		count++;
		this.floristId=floristId;
	}
	
	public Ticket(int floristId) {
		items = new HashSet<Product>();
		value = 0;
		this.id = count;
		count++;
		this.floristId=floristId;
	}
	
	//---GETTERS & SETTERS---
	private float getItemsValue() {
		float value = 0;
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
	
	public float getValue() {
		return value;
	}
	
	public Set<Product> getItems(){
		return items;
	}
	
	public int getFloristId() {
		return floristId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return id == other.id;
	}

	//---LOGIC/VALIDATION---
	public void addItem(String id, int amount, Stock stock) {
		Product item = new Product();
		item = stock.getProduct(id);
		//Remove product from stock because it's being purchased
		stock.removeProduct(id, amount);
		//We set the amount of the new instance of the item to add it to tickets without affecting the ones in stock
		item.setAmount(amount);
		items.add(item);
		value = getItemsValue();
	}
	
	//---VIEW---
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
