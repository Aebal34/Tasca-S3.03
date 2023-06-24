package n2exercici1;

import java.util.*;
import java.io.*;

public class Florist {

	//---ATTRIBUTES---
	private static int count = 1;
	private int id;
	private String name;
	private Stock stock;
	private Set<Ticket> tickets;
	private TicketDao ticketDao;
	
	//---CONSTRUCTOR---
	public Florist(String name, Stock stock, TicketDao ticketDao) {
		id = count;
		this.name = name;
		this.stock = stock;
		tickets = new HashSet<Ticket>();
		this.ticketDao = ticketDao;
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
	public void purchase(Product product, int amount) {
		var items = new HashSet<Product>();
		for(Product item : stock.getProducts()) {
			if(item.equals(product)) {
				//Add item to "shopping cart"
				var purchasedItem = product;
				purchasedItem.setAmount(amount);
				items.add(purchasedItem);
				//Remove items from stock
				stock.removeProduct(product.getId(), amount);
			}
		}
		//Add all items to set and to db
		var ticket = new Ticket(items, this.id);
		tickets.add(ticket);
		ticketDao.save(ticket);
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
		System.out.println("The total ammount sold in this florist is: "+finalAmmount);
	}
}
