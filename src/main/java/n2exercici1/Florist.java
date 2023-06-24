package n2exercici1;

import java.util.*;

public class Florist {

	//---ATTRIBUTES---
	private static int count = 1;
	private int id;
	private String name;
	private Stock stock;
	private Set<Ticket> tickets= new HashSet<Ticket>();
	private TicketDao ticketDao;
	
	//---CONSTRUCTOR---
	public Florist(String name, Stock stock, TicketDao ticketDao) {
		id = count;
		this.name = name;
		this.stock = stock;
		this.ticketDao = ticketDao;
		tickets = ticketDao.getAll();
		count++;
	}
	
	//---GETTERS & SETTERS---
	public Stock getStock() {
		return stock;
	}
	
	public int getId() {
		return id;
	}
	
	//---FUNCTIONALITY---
	public void purchase(String id, int amount) {
		var items = new HashSet<Product>();
		var product = stock.getProduct(id);
		for(Product item : stock.getProducts()) {
			if(item.equals(product)) {
				//Add item to "shopping cart" making sure they are in stock
				items.add(item);
			}
		}
		//Once we have the items selected, we add them to a created ticket
		var ticket = new Ticket(this.id);
		for(Product item : items) {
			ticket.addItem(item.getId(), amount, stock);
		}
		//Add ticket to hashset and to db
		tickets.add(ticket);
		ticketDao.save(ticket);
	}
	
	public void addItemToTicket(int ticketId, String productId, int amount) {
		for(Ticket ticket: tickets) {
			if(ticketId==ticket.getId()) {
				ticket.addItem(productId, amount, stock);
			}
		}
	}
	
	//---VIEW---
	public void printTickets() {
		for(Ticket ticket : tickets) {
			ticket.printTicket();
		}
	}
	
	public void printTotalSales() {
		double finalAmmount = 0;
		for(Ticket ticket : tickets) {
			finalAmmount += ticket.getValue();
		}
		System.out.println("The total ammount sold in this florist is: "+Math.round(finalAmmount));
	}
}
