package n1exercici1;

import java.util.*;

public class Ticket implements Persistent{

	//---ATTRIBUTES---
	private List<Product> items;
	private double value;
	private Stock stock;
	private int id;
	private static int count = 1;
	
	//---CONSTRUCTORS---
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
	
	//---DATA CONTROL---
	public void addItem(String id, int ammount) {
		var item = stock.getProduct(id);
		if(ammount <= item.getAmmount()){
			stock.removeProduct(id, ammount);
			item.setAmmount(ammount);
			items.add(item);
		}
		value = getItemsValue();
	}

	//---PERSISTENCE---
	@Override
	public String toData() {
							
		String data = "Ticket|"+id;
		for(Product item : items) {
			data += "|"+item.toData();
		}
		
		return data;
	}

	@Override
	public void fromData(String data) {

		String[] product = data.split(";");
		if(product[0].equals("Tree")) {
			var tree = new Tree();
			tree.fromData(data);
			items.add(tree);
		}else if(product[0].equals("Flower")){
			var flower = new Flower();
			flower.fromData(data);
			items.add(flower);
		}else if(product[0].equals("Decoration")){
			var deco = new Decoration();
			deco.fromData(data);
			items.add(deco);
		}
	}
}
