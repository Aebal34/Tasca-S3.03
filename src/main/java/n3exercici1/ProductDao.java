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
		connect();
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
		/*We use MongoCursor instead of FindIterable because we don't need the methods included in FindIterable
		  but as MongoCursor interface extends AutoCloseable, we can try-with-resource with it*/
		try(MongoCursor<Document> cursor = collection.find().iterator()){
			while(cursor.hasNext()) {
				Document doc = cursor.next();
				if(doc.getString("type").equals("Tree")) {
					Tree tree = new Tree(doc.getDouble("height"), doc.getDouble("price"), doc.getInteger("amount"));
					tree.setId(doc.getString("id"));
					products.add(tree);
					//To avoid storing ids that are equal to others created
					if(Tree.getCount() < Integer.parseInt(doc.getString("id").substring(1))) {
						Tree.setCount((Integer.parseInt(doc.getString("id").substring(1)))+1);
					}
				}else if(doc.get("type").equals("Flower")) {
					Flower flower = new Flower(doc.getString("color"), doc.getDouble("price"), doc.getInteger("amount"));
					flower.setId(doc.getString("id"));
					products.add(flower);
					//To avoid storing ids that are equal to others created
					if(Flower.getCount() < Integer.parseInt(doc.getString("id").substring(1))) {
						Flower.setCount((Integer.parseInt(doc.getString("id").substring(1)))+1);
					}
				}else if(doc.get("type").equals("Decoration")) {
					Decoration deco = new Decoration(doc.getString("material"), doc.getDouble("price"), doc.getInteger("amount"));
					deco.setId(doc.getString("id"));
					products.add(deco);
					//To avoid storing ids that are equal to others created
					if(Decoration.getCount() < Integer.parseInt(doc.getString("id").substring(1))) {
						Decoration.setCount((Integer.parseInt(doc.getString("id").substring(1)))+1);
					}
				}
			}
		}
		System.out.println("Products imported from database succesfully");
		return products;
	}

	@Override
	public void update(Product product, String[] parameters) {//Parameters should be Price, Amount, Special_Characteristic
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
					float height = Float.parseFloat(parameters[2]);
					Bson updatedValue = new Document("height", height);
					Bson updateOperation = new Document("$set", updatedValue);
					collection.updateOne(found, updateOperation);
					System.out.println("Height from product updated");
				}
			}else if(product instanceof Flower) {
				if(parameters[2] != null) { //If there's a color to be updated
					String color = parameters[2];
					Bson updatedValue = new Document("color", color);
					Bson updateOperation = new Document("$set", updatedValue);
					collection.updateOne(found, updateOperation);
					System.out.println("Color from product updated");
				}
			}else if(product instanceof Decoration) {
				if(parameters[2] != null) { //If there's a material to be updated
					String material = parameters[2];
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
	public void delete(Product product) {
		Document found = (Document)collection.find(new Document("id", product.getId())).first();
		collection.deleteOne(found);		
	}
}
