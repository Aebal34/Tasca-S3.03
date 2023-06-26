package n3exercici1;

import java.util.*;

public class Ticket{

	//---ATTRIBUTES---
	private Set<Product> items;
	private float value;
	private int id;
	private static int count = 1;
	
	//---CONSTRUCTORS---
	public Ticket(Set<Product> items) {
		this.items = items;
		value = getItemsValue();
		this.id = count;
		count++;
	}
	public Ticket(Set<Product> items, int id) {
		this.items = items;
		value = getItemsValue();
		this.id = id;
	}
	public Ticket() {
		items = new HashSet<>();
		this.id = count;
		count++;
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
	
	public static int getCount() {
		return count;
	}
	
	public static void setCount(int newCount) {
		count = newCount;
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
		//We create a new instance of the product with the proper amount to save into the ticket
		double price = stock.getProduct(id).getPrice();
		Product prod = new Product(price, amount, id);
		items.add(prod);
		//Remove product from stock because it's being purchased
		stock.removeProduct(id, amount);
		updateValue();
	}
	
	//---VIEW---
	public void printTicket() {
		
		System.out.println("Ticket: "+id+" { "+"\n");
		for(Product item : items) {
			System.out.println("	|"+item+"\n");
		}
		System.out.println("		|Total value: "+value+" }");
	}
	
	private void updateValue() {
		this.value = getItemsValue();
	}
}
