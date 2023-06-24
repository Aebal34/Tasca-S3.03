package n2exercici1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDao implements Dao<Ticket>{

	//ATTRIBUTES
	private List<Ticket> tickets = new ArrayList<>();
	private String userName;
	private String password;
	private String url;
	Connection connection = null;
	
	//CONSTRUCTOR
	public TicketDao(String url, String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.url = url;
	}
	
	//PERSISTENCE
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
	
	public List<Product> getTicket(int id) throws SQLException {
		var items = new ArrayList<Product>();
		
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT P.* FROM Purchases P WHERE ticketId"+id+";");
		
		while(result.next()) {
			String productId = result.getString(0);
			
		}
		
		return items;
	}
	
	@Override
	public List getAll() {
		try {
			connect();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT P.id, P.price, P.amount, "
														+"CASE "
														+ "WHEN T.id IS NOT NULL THEN T.height "
														+ "WHEN F.id IS NOT NULL THEN F.color "
														+ "WHEN D.id IS NOT NULL THEN D.material "
														+ "ELSE NULL END AS specific_characteristic "
														+ "FROM Products P "
														+ "LEFT JOIN Trees T ON P.id = T.id "
														+ "LEFT JOIN Flowers F ON P.id = F.id "
														+ "LEFT JOIN Decorations D ON P.id = D.id;");
			//Loop through each result of the query
			while (result.next()) {
				String id = result.getString("P.id");
				double price = result.getDouble("P.price");
				int amount = result.getInt("P.amount");
				boolean productExists = false;
				//For each product that exists but the amount is different, we update the amount in the List
				for(Product product : products) { 
					if(id.equals(product.getId())) {
						productExists = true;
						if(product.getAmount()!=amount) {
							product.setAmount(amount);
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return tickets;
}

	@Override
	public void save(Ticket ticket) {
		try {
			connect();
			//Instantiate statement to work with queries
			Statement statement = connection.createStatement();
			int rowsAffected = 0;
			for(Product product : ticket.getItems()) {
				rowsAffected += statement.executeUpdate("INSERT INTO Purchases VALUES ("
														+product.getId()+", "
														+ticket.getId()+", "
														+product.getAmount()+");");
			}
			rowsAffected += statement.executeUpdate("INSERT INTO Tickets VALUES ("
													+ticket.getId()+", "
													+ticket.getValue()+", "
													+ticket.getFlorist().getId()+");");
			System.out.println(rowsAffected +" rows affected.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Ticket ticket) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Ticket t, String[] parameters) {
		// TODO Auto-generated method stub
		
	}

}
