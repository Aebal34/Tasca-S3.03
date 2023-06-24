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
																	+ "FROM Products AS P "
																	+ "LEFT JOIN Trees AS T ON P.id = T.id "
																	+ "LEFT JOIN Flowers AS F ON P.id = F.id "
																	+ "LEFT JOIN Decorations AS D ON P.id = D.id;");
				//Loop through each result of the query
				while (result.next()) {
					String id = result.getString("P.id");
					float price = result.getFloat("P.price");
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
								var tree = new Tree(result.getFloat("specific_characteristic"), price, amount);
								tree.setId(id);
								products.add(tree);
								break;
							case 'F':
								var flower = new Flower(result.getString("specific_characteristic"), price, amount);
								flower.setId(id);
								products.add(flower);
								break;
							case 'D':
								var decoration = new Decoration(result.getString("specific_characteristic"), price, amount);
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
			int rowsAffected = 0;
			String query = "INSERT INTO Products VALUES (?, ?, ?)";
			//Instantiate prepared statement to work with query, validating data type integrity
			//We use try-with-resource to close automatically the statement once is used
			try(PreparedStatement statement = connection.prepareStatement(query)){
				statement.setString(1, product.getId());
				statement.setFloat(2, product.getPrice());
				statement.setInt(3, product.getAmount());
				//We save the amount of rows affected at inserting values into database
				rowsAffected += statement.executeUpdate();
			}
			//Depending on the kind of product we insert values on their tables as well
			if(product instanceof Tree) {
				var tree = (Tree)product;
				query = "INSERT INTO Trees VALUES (?, ?)";
				try(PreparedStatement statement = connection.prepareStatement(query)){
					statement.setString(1, tree.getId());
					statement.setFloat(2, tree.getHeight());
					rowsAffected += statement.executeUpdate();
				}
			}else if(product instanceof Flower){
				var flower = (Flower)product;
				query = "INSERT INTO Flowers VALUES (?, ?)";
				try(PreparedStatement statement = connection.prepareStatement(query)){
					statement.setString(1, flower.getId());
					statement.setString(2, flower.getColor());
					rowsAffected += statement.executeUpdate();
				}
			}else if(product instanceof Decoration) {
				var decoration = (Decoration)product;
				query = "INSERT INTO Decorations VALUES (?, ?)";
				try(PreparedStatement statement = connection.prepareStatement(query)){
					statement.setString(1, decoration.getId());
					statement.setString(2, decoration.getMaterial());
					rowsAffected += statement.executeUpdate();
				}
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

	public void test() {
		try {
			connect();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Products");
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while(rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
			        String columnName = metaData.getColumnName(i);
			        String value = rs.getString(i);
			        System.out.println("Columna: " + columnName + ", Valor: " + value);
			    }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
