package n1exercici1;

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
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
}
