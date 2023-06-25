package n3exercici1;

import java.sql.*;
import java.util.*;

public class TicketDao implements Dao<Ticket>{

	//---ATTRIBUTES---
	private Set<Ticket> tickets = new HashSet<>();
	Connection connection = null;
	
	//---CONSTRUCTOR---
	public TicketDao() {

	}

	//---PERSISTENCE---
	@Override
	public Set<Ticket> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ticket t, String[] parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Ticket t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Ticket t) {
		// TODO Auto-generated method stub
		
	}
}
