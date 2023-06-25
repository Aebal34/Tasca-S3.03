package n3exercici1;

import java.util.*;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;

import org.bson.*;
import org.bson.conversions.*;

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
	public void update(Product product, String[] parameters) {//Parameters should be Price, Amount, Special_Characteristic
		connect();
		//Find the document we want to update
		Document found = (Document)collection.find(new Document("id", product.getId())).first();
		if(found != null) { 
			System.out.println("Product found");
			if(parameters[0] != null) { //If there's a price to be updated
				float price = Float.parseFloat(parameters[0]);
				Bson updatedValue = new Document("price", price);
				Bson updateOperation = new Document("$set", updatedValue);
				collection.updateOne(found, updateOperation);
				System.out.println("Price from product updated");
			}
			if(parameters[1] != null) { //If there's an amount to be updated
				int amount = Integer.parseInt(parameters[1]);
				Bson updatedValue = new Document("amount", amount);
				Bson updateOperation = new Document("$set", updatedValue);
				collection.updateOne(found, updateOperation);
				System.out.println("Amount from product updated");
			}
			if(product instanceof Tree) {
				if(parameters[2] != null) { //If there's a height to be updated
					float height = Float.parseFloat(parameters[3]);
					Bson updatedValue = new Document("height", height);
					Bson updateOperation = new Document("$set", updatedValue);
					collection.updateOne(found, updateOperation);
					System.out.println("Height from product updated");
				}
			}else if(product instanceof Flower) {
				if(parameters[2] != null) { //If there's a color to be updated
					String color = parameters[3];
					Bson updatedValue = new Document("color", color);
					Bson updateOperation = new Document("$set", updatedValue);
					collection.updateOne(found, updateOperation);
					System.out.println("Color from product updated");
				}
			}else if(product instanceof Decoration) {
				if(parameters[2] != null) { //If there's a material to be updated
					String material = parameters[3];
					Bson updatedValue = new Document("material", material);
					Bson updateOperation = new Document("$set", updatedValue);
					collection.updateOne(found, updateOperation);
					System.out.println("Material from product updated");
				}
			}
		}
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
