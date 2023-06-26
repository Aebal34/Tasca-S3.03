package n3exercici1;

import java.util.*;

public class Florist {

	//---ATTRIBUTES---
	private String name;
	private Stock stock;
	private Set<Ticket> tickets= new HashSet<Ticket>();
	private TicketDao ticketDao;
	
	//---CONSTRUCTOR---
	public Florist(String name, Stock stock, TicketDao ticketDao) {
		this.name = name;
		this.stock = stock;
		this.ticketDao = ticketDao;
		tickets = ticketDao.getAll();
	}
	
	//---GETTERS & SETTERS---
	public Stock getStock() {
		return stock;
	}
	
	//---FUNCTIONALITY---
	public void purchase(String id, int amount) {
		Product product = stock.getProduct(id);
		if(product != null) {
			var ticket = new Ticket();
			ticket.addItem(id, amount, stock);
			//Add ticket to hashset and to db
			tickets.add(ticket);
			ticketDao.save(ticket);
		}		
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
