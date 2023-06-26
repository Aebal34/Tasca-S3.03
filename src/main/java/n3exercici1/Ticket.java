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
		switch(id.charAt(0)) {
			case 'T':
				Tree tree = new Tree(price, amount, id);
				items.add(tree);
				break;
			case 'F':
				Flower flower = new Flower(price, amount, id);
				items.add(flower);
				break;
			case 'D':
				Decoration deco = new Decoration(price, amount, id);
				items.add(deco);
				break;
		}
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
