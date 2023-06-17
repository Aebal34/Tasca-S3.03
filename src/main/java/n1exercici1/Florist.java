package n1exercici1;

import java.util.*;
import java.io.*;

public class Florist {

	private String name;
	private Stock stock;
	private List<Ticket> purchases;
	
	public Florist(String name, Stock stock) {
		this.name = name;
		this.stock = stock;
		purchases = new ArrayList<Ticket>();
		stock.setFlorist(this);
	}
	
	public Stock getStock() {
		return stock;
	}
	
	public void loadStock(String stockPath) {
		
		try {
			var reader = new BufferedReader(new FileReader(stockPath));
			while(reader.readLine() != null) {
				String[] product = reader.readLine().split(";");
				String type = product[0];
				switch(type) {
				case "Tree":
					break;
				case "Flower":
					break;
				case "Decoration":
					break;
				}
				
			}
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
}
