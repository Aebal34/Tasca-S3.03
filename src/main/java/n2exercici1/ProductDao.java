package n2exercici1;

import java.util.*;
import java.sql.*;

public class ProductDao implements Dao<Product>{

	//ATTRIBUTES
	private List<Product> products = new ArrayList<>();
	private String userName;
	private String password;
	private String url;
	Connection connection = null;
	
	//CONSTRUCTOR
	public ProductDao(String url, String userName, String password) {
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

	@Override
	public List<Product> getAll() {
		//We get the information needed to create an object of the pertinent class to later save it on the list
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
					if(!productExists) {
						switch(id.charAt(0)) {
							case 'T':
								var tree = new Tree(result.getDouble("T.height"), price, amount);
								tree.setId(id);
								products.add(tree);
								break;
							case 'F':
								var flower = new Flower(result.getString("F.color"), price, amount);
								flower.setId(id);
								products.add(flower);
								break;
							case 'D':
								var decoration = new Decoration(result.getString("D.material"), price, amount);
								decoration.setId(id);
								products.add(decoration);
								break;
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return products;
	}

	@Override
	public void update(Product product, String[] parameters) { //Parameters should be Price, Amount, Special_Characteristic
		try {
			connect();
			Statement statement = connection.createStatement();
			int rowsAffected = 0;
			for(int i = 0; i < parameters.length; i++) {
				if(parameters[i] != null) {
					switch(i) {
						case 0:
							rowsAffected += statement.executeUpdate("UPDATE Products SET price="+parameters[i]
																	+" WHERE id="+product.getId()+";");
							break;
						case 1:
							rowsAffected += statement.executeUpdate("UPDATE Products SET amount="+parameters[i]
																	+" WHERE id="+product.getId()+";");
							break;
						case 2:
							if(product instanceof Tree) {
								rowsAffected += statement.executeUpdate("UPDATE Trees SET height="+Double.parseDouble(parameters[i])
																		+ "WHERE id="+product.getId()+";");
							}else if(product instanceof Flower) {
								rowsAffected += statement.executeUpdate("UPDATE Flowers SET color="+parameters[i]
																		+ "WHERE id="+product.getId()+";");
							}else if(product instanceof Decoration) {
								rowsAffected += statement.executeUpdate("UPDATE Decorations SET material="+parameters[i]
																		+ "WHERE id="+product.getId()+";");
							}
							break;
					}
				}
			}	
			System.out.print(rowsAffected+" rows affected.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void save(Product product) { //save product in database
		try {
			connect();
			//Instantiate statement to work with queries
			Statement statement = connection.createStatement();
			//We save the amount of rows affected at inserting values into database
			int rowsAffected = statement.executeUpdate("INSERT INTO Products VALUES ("
														+product.getId()+", "
														+product.getPrice()+", "
														+product.getAmount()+");");
			//Depending on the kind of product we insert values on their tables as well
			if(product instanceof Tree) {
				var tree = (Tree)product;
				rowsAffected += statement.executeUpdate("INSERT INTO Trees VALUES ("
														 +tree.getId()+", "
														 +tree.getHeight()+");");
			}else if(product instanceof Flower){
				var flower = (Flower)product;
				rowsAffected += statement.executeUpdate("INSERT INTO Flowers (color) VALUES ("
														 +flower.getColor()+");");			
			}else if(product instanceof Decoration) {
				var decoration = (Decoration)product;
				rowsAffected += statement.executeUpdate("INSERT INTO Decorations (material) VALUES ("
						 								 +decoration.getMaterial()+");");
			}
			System.out.println(rowsAffected +" rows affected.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Product product) {
		try {
			connect();
			Statement statement = connection.createStatement();
			int rowsAffected = statement.executeUpdate("DELETE FROM Products WHERE id="+product.getId()+";");
			if(product instanceof Tree) {
				rowsAffected += statement.executeUpdate("DELETE FROM Trees WHERE id="+product.getId()+";");
			}else if(product instanceof Flower){
				rowsAffected += statement.executeUpdate("DELETE FROM Flowers WHERE id="+product.getId()+";");	
			}else if(product instanceof Decoration) {
				rowsAffected += statement.executeUpdate("DELETE FROM Decorations WHERE id="+product.getId()+";");
			}
			System.out.println(rowsAffected +" rows affected.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
