package n3exercici1;

import java.util.*;

import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.*;

import org.bson.Document;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

public class TicketDao implements Dao<Ticket>{

	//---ATTRIBUTES---
	private Set<Ticket> tickets = new HashSet<>();
	MongoCollection<Document> collection = null;
	
	//---CONSTRUCTOR---
	public TicketDao() {
		connect();
		registerCodecs();
	}

	//---LOGIC/VALIDATION---
	private void connect() {
		String uri = "mongodb+srv://admin:admin@mongodb.zf49r2c.mongodb.net/";
		MongoClientURI clientUri = new MongoClientURI(uri);
		MongoClient client = new MongoClient(clientUri);
		
		MongoDatabase mongoDatabase = client.getDatabase("MongoDB");
		collection = mongoDatabase.getCollection("Tickets");
		
		System.out.println("Connected to database");
	}
	
	// Register custom codecs to map products into bson
    private void registerCodecs() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
            MongoClient.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        collection = collection.withCodecRegistry(codecRegistry);
    }
	
	//---PERSISTENCE---
	@Override
	public Set<Ticket> getAll() {
		try(MongoCursor<Document> cursor = collection.find().iterator()){
			while(cursor.hasNext()) {
				Document doc = cursor.next();
				Set<Product> products = new HashSet<>();
				List<Document> items = doc.getList("items", Document.class);
				for(Document item : items) {
					Product prod = new Product(item.getDouble("price"), item.getInteger("amount"), item.getString("_id"));
					products.add(prod);
				}
				int id = doc.getInteger("id");
				var ticket = new Ticket(products, id);
				tickets.add(ticket);
				//Id integrity validation
				if(Ticket.getCount() <= id) {
					Ticket.setCount(id+1);
				}
			}
		}
		return tickets;
	}

	public void addItemIntoTicket(Ticket ticket, Product product) {
		Document found = (Document)collection.find(new Document("id", ticket.getId())).first();
		
		if(found != null) {
			System.out.println("The ticket has been found");
			Document doc = new Document("_id", product.getId());
			doc.append("amount", product.getAmount());
			doc.append("price", product.getPrice());
			Document updatedDoc = new Document("$push", new Document("items", doc));
			collection.updateOne(found, updatedDoc);
		}
	}

	@Override
	public void save(Ticket ticket) {
		Document doc = new Document("id", ticket.getId());
		doc.append("items", ticket.getItems());
		doc.append("ticketValue", ticket.getValue());
		
		collection.insertOne(doc);
	}

	@Override
	public void delete(Ticket ticket) {
		Document found = (Document)collection.find(new Document("id", ticket.getId())).first();
		collection.deleteOne(found);
	}

	@Override
	public void update(Ticket t, String[] parameters) {
		//Not going to use this one
	}
}
