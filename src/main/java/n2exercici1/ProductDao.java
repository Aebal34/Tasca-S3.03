package n2exercici1;

import java.util.*;
import java.sql.*;

public class ProductDao implements Dao<Product>{

	private List<Product> products = new ArrayList<>();
	private String userName;
	private String password;
	private String url;
	
	public ProductDao(String url, String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.url = url;
	}
	
	@Override
	public Optional<Product> get(String id) {
		return products.stream().filter(p -> p.getId().equals(id)).findFirst();
	}

	@Override
	public List getAll() {
		return products;
	}

	@Override
	public void update(Product product, String[] parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Product product) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.getCause();
		}
		try {
			Connection connection = DriverManager.getConnection(url, userName, password);
			Statement statement = connection.createStatement();
			int rowsAffected = statement.executeUpdate("INSERT INTO Products VALUES ("
														+product.getId()+", "
														+product.getPrice()+", "
														+product.getAmmount()+");");
			
			if(product instanceof Tree) {
				var tree = (Tree)product;
				rowsAffected += statement.executeUpdate("INSERT INTO Trees (height) VALUES ("
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
		// TODO Auto-generated method stub
		
	}

}
