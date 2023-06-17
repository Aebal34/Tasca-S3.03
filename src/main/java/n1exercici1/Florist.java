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
	
}
