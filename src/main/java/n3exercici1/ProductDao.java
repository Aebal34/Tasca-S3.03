package n3exercici1;

import java.util.*;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;

import org.bson.*;

public class ProductDao implements Dao<Product>{

	//---ATTRIBUTES---
	private Set<Product> products = new HashSet<>();
	
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
		MongoCollection collection = mongoDatabase.getCollection("Florists");
		
		Document doc = new Document("Type: ", "Tree");
		doc.append("height", 2.95);
		doc.append("price", 29.95);
		doc.append("amount", 20);
		doc.append("id", "T1");
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
	public void save(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Product t) {
		// TODO Auto-generated method stub
		
	}
}
