package n1exercici1;

import java.util.*;

public class Florist {

	private String name;
	private Stock stock;
	private List<Ticket> purchases;
	
	public Florist(String name) {
		this.name = name;
		this.stock = new Stock();
		purchases = new ArrayList<Ticket>();
	}
	
	public Stock getStock() {
		return stock;
	}
	
}
