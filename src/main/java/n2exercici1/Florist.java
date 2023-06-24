package n2exercici1;

import java.util.*;
import java.io.*;

public class Florist {

	//---ATTRIBUTES---
	private static int count = 1;
	private int id;
	private String name;
	private Stock stock;
	private List<Ticket> purchases;
	
	//---CONSTRUCTOR---
	public Florist(String name, Stock stock) {
		id = count;
		this.name = name;
		this.stock = stock;
		purchases = new ArrayList<Ticket>();
		count++;
	}
	
	//---GETTERS & SETTERS
	public Stock getStock() {
		return stock;
	}
	
	public int getId() {
		return id;
	}
	
	//---FUNCTIONALITY
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

}
