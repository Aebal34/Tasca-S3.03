package n3exercici1;

public class App {

	public static void main(String[] args) {
		
		//Instantiate florist with ProductDao and TicketDao
		Florist botanicula = new Florist("Botanicula", new Stock(new ProductDao()), new TicketDao());
		
		Stock botaniculaStock = botanicula.getStock();
		
		//Instantiate Stock and add 2 trees, 2 flowers and 2 decorations
		botaniculaStock.addProduct(new Tree(2.78f, 69.95f, 20));
		botaniculaStock.addProduct(new Tree(2.55f, 29.95f, 44));
		botaniculaStock.addProduct(new Flower("Purple", 18.45f, 13));
		botaniculaStock.addProduct(new Flower("White", 14.25f, 25));
		botaniculaStock.addProduct(new Decoration("Wood", 129.99f, 22));
		botaniculaStock.addProduct(new Decoration("Metal", 81.75f, 9));
		
		botaniculaStock.printStock();
		
		//Remove diverse products from stock
		botaniculaStock.removeProduct("T1", 5);
		botaniculaStock.removeProduct("F2", 14);
		botaniculaStock.removeProduct("D1", 12);
		
		botanicula.getStock().printStock();
		
		//We purchase the items we want
		//Every time we buy a product, it's added and substracted to the adequate tables on MySql
		botanicula.purchase("D2", 3);
		botanicula.addItemToTicket(1, "F1", 2);
		botanicula.purchase("T2", 1);
		
		botanicula.getStock().printStock();

		//We print all tickets we have on the florist
		botanicula.printTickets();
		
		//And we look at all the money we've won
		botanicula.printTotalSales();
	}
}
