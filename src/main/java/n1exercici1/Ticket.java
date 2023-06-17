package n1exercici1;

import java.util.*;

public class Ticket {

	private List<Product> items;
	private double value;
	private Stock stock;
	private int id;
	private static int count = 1;
	
	public Ticket(ArrayList<Product> items, Stock stock) {
		this.items = items;
		value = getItemsValue();
		this.stock = stock;
		this.id = count;
		count++;
	}
	
	public Ticket() {
		items = new ArrayList<Product>();
		value = 0;
	}
	
	private double getItemsValue() {
		double value = 0;
		for(Product item : this.items) {
			value += item.getPrice()*item.getAmmount();
		}
		return value;
	}
	
	public void addItem(String id, int ammount) {
		var item = stock.getProduct(id);
		if(ammount <= item.getAmmount()){
			stock.removeProduct(id, ammount);
			item.setAmmount(ammount);
			items.add(item);
		}
		value = getItemsValue();
	}
	
	
}
