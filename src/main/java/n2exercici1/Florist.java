package n2exercici1;

import java.util.*;
import java.io.*;

public class Florist {

	//---ATTRIBUTES---
	private String name;
	private Stock stock;
	private List<Ticket> purchases;
	
	//---CONSTRUCTOR---
	public Florist(String name, Stock stock) {
		this.name = name;
		this.stock = stock;
		purchases = new ArrayList<Ticket>();
	}
	
	//---GETTERS & SETTERS
	public Stock getStock() {
		return stock;
	}
	
	//---FUNCTIONALITY
	public void purchase(ShoppingCart cart) {
		var ticket = new Ticket(cart.getProducts());
		purchases.add(ticket);
		stock.updateValue();
	}
	
	public void printTickets() {
		for(Ticket ticket : purchases) {
			ticket.printTicket();
		}
	}
	
	public void printTotalSales() {
		double finalAmmount = 0;
		for(Ticket ticket : purchases) {
			finalAmmount += ticket.getValue();
		}
		System.out.println("The total ammount sold in this florist is: "+finalAmmount);
	}
	
	//---PERSISTENCE---
	public void loadStock(String stockPath) {
		
		try {
			var reader = new BufferedReader(new FileReader(stockPath));
			String line;
			while((line = reader.readLine()) != null) {
				String[] product = line.split(";");
				String type = product[0];
				switch(type) {
					case "Tree":
						var tree = new Tree();
						tree.fromData(line);
						stock.getProducts().add(tree);
						break;
					case "Flower":
						var flower = new Flower();
						flower.fromData(line);
						stock.getProducts().add(flower);
						break;
					case "Decoration":
						var decoration = new Decoration();
						decoration.fromData(line);
						stock.getProducts().add(decoration);
						break;
				}
			}
			reader.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public void loadTickets(String ticketsPath) {
		
		try {
			var reader = new BufferedReader(new FileReader(ticketsPath));
			String line;
			while((line = reader.readLine()) != null){
				String[] elements = line.split(",");
				var ticket = new Ticket();
				ticket.setId(Integer.parseInt(elements[1]));
				for (int i = 2; i<elements.length;i++) {
					ticket.fromData(elements[i]);
				}
				ticket.updateValue();
				purchases.add(ticket);
			}
			reader.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public void saveTickets(String ticketsPath) {
		
		try {
			var writer = new BufferedWriter(new FileWriter(ticketsPath));
			for(Ticket ticket : purchases) {
				writer.write(ticket.toData());
				writer.newLine();
			}
			writer.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
}
