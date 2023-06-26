package n3exercici1;

import java.util.*;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Ticket t, String[] parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Ticket ticket) {
		Document doc = new Document("id", ticket.getId());
		doc.append("items", ticket.getItems());
		doc.append("ticketValue", ticket.getValue());
		
		collection.insertOne(doc);
	}

	@Override
	public void delete(Ticket t) {
		// TODO Auto-generated method stub
		
	}
}
