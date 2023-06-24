package n2exercici1;

import java.sql.*;
import java.util.*;

public class TicketDao implements Dao<Ticket>{

	//---ATTRIBUTES---
	private Set<Ticket> tickets = new HashSet<>();
	private String userName;
	private String password;
	private String url;
	Connection connection = null;
	
	//---CONSTRUCTOR---
	public TicketDao(String url, String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.url = url;
	}
	
	//---LOGIC/VALIDATION---
		public void connect() throws SQLException {
			try {
				//Load the driver so it can be loaded dynamically though static block
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			}catch(ClassNotFoundException e) {
				e.getCause();
			}
			//We get the connection with the database
			connection = DriverManager.getConnection(url, userName, password);
		}
		
	//---PERSISTENCE---
	public Set<Product> getTicket(int id) throws SQLException {
		var items = new HashSet<Product>();
		
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT P.* FROM Purchases P WHERE ticketId="+id+";");
		
		while(result.next()) {
			String productId = result.getString("productId");
			int amount = result.getInt("amount");
			float price = result.getFloat("price");
			if(productId.charAt(0)=='T') {
				items.add(new Tree(price, amount, productId));
			}else if(productId.charAt(0)=='F') {
				items.add(new Flower(price, amount, productId));
			}else if(productId.charAt(0)=='D') {
				items.add(new Decoration(price, amount, productId));
			}
		}
		return items;
	}
	
	@Override
	public Set<Ticket> getAll() {
		try {
			connect();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT P.productId, P.ticketId, P.amount, P.price "
														+ "FROM Purchases P "
														+ "LEFT JOIN Tickets T ON P.ticketId = T.id;");
			//Loop through each result of the query to find different tickets and it's items
			int previousId = 1;
			while (result.next()) {
				int id = result.getInt("P.ticketId");  
				if(id != previousId) {
					tickets.add(new Ticket(getTicket(id), result.getInt("T.floristId")));
				}
				previousId = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}

	@Override
	public void save(Ticket ticket) {
		try {
			connect();
			String query = "INSERT INTO Purchases VALUES (?,?,?,?);";
			int rowsAffected = 0;
			try(PreparedStatement statement = connection.prepareStatement(query)){
				for(Product product : ticket.getItems()) {
					statement.setString(1, product.getId());
					statement.setInt(2, ticket.getId());
					statement.setInt(3, product.getAmount());
					statement.setFloat(4, product.getPrice());
					rowsAffected += statement.executeUpdate();
				}
			}
			query = "INSERT INTO Tickets VALUES (?,?,?);";
			try(PreparedStatement statement = connection.prepareStatement(query)){
				statement.setInt(1, ticket.getId());
				statement.setFloat(2, ticket.getValue());
				statement.setInt(3, ticket.getFloristId());
			}
			System.out.println(rowsAffected +" rows affected.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Ticket ticket) {
		try {
			connect();
			Statement statement = connection.createStatement();
			int rowsAffected = statement.executeUpdate("DELETE FROM Purchases P WHERE P.ticketId="
														+ticket.getId()+";");
			rowsAffected += statement.executeUpdate("DELETE FROM Tickets T WHERE T.id="
														+ticket.getId()+";");
			System.out.println(rowsAffected +" rows affected.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Ticket t, String[] parameters) {
		// Not necessary
		
	}
}
