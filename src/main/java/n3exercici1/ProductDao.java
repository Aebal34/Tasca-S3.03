package n3exercici1;

import java.util.*;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;

import org.bson.*;

public class ProductDao implements Dao<Product>{

	//---ATTRIBUTES---
	private Set<Product> products = new HashSet<>();
	MongoCollection<Document> collection = null;
	
	//---CONSTRUCTOR---
	public ProductDao() {
		
	}
	
	//---GETTERS&SETTERS---
	public Set<Product> getProducts() {
		return products;
	}
	
	//---PERSISTENCE---
	private void connect() {
		String uri = "mongodb+srv://admin:admin@mongodb.zf49r2c.mongodb.net/";
		MongoClientURI clientUri = new MongoClientURI(uri);
		MongoClient client = new MongoClient(clientUri);
		
		MongoDatabase mongoDatabase = client.getDatabase("MongoDB");
		collection = mongoDatabase.getCollection("Stock");
		
		System.out.println("Connected to database");
	}
	
	@Override
	public Set<Product> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Product t, String[] parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Product product) {
		connect();
		Document doc = new Document("id", product.getId());
		doc.append("price", product.getPrice());
		doc.append("amount", product.getAmount());
		if(product instanceof Tree) {
			Tree tree = (Tree)product;
			doc.append("type", "Tree");
			doc.append("height", tree.getHeight());
		}else if(product instanceof Flower) {
			Flower flower = (Flower)product;
			doc.append("type", "Flower");
			doc.append("color", flower.getColor());
		}else if(product instanceof Decoration) {
			Decoration deco = (Decoration)product;
			doc.append("type", "Decoration");
			doc.append("material", deco.getMaterial());
		}
		collection.insertOne(doc);
		System.out.println("Product added succesfully");
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}
}
