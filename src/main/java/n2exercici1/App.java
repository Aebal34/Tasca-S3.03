package n2exercici1;

public class App {

	public static void main(String[] args) {
		
		//Instantiate florist with filepath of Stock.txt
		String url = "jdbc:mysql://localhost:3306/florists";
		String userName = "root";
		String password = "9595";
		Florist botanicula = new Florist("Bonaticula", new Stock(new ProductDao(url, userName, password)));
		
		//Instantiate Stock and add 2 trees, 2 flowers and 2 decorations
		
		
		Stock botaniculaStock = botanicula.getStock();
		
		botaniculaStock.addProduct(new Tree(2.78f, 69.95f, 23));
		botaniculaStock.addProduct(new Tree(2.55f, 29.95f, 44));
		botaniculaStock.addProduct(new Flower("Purple", 18.45f, 13));
		botaniculaStock.addProduct(new Flower("White", 14.25f, 25));
		botaniculaStock.addProduct(new Decoration("Wood", 129.99f, 22));
		botaniculaStock.addProduct(new Decoration("Metal", 81.75f, 9));
		
		botaniculaStock.printStock();
		
		//Remove diverse products from stock
		botaniculaStock.removeProduct("T1", 11);
		botaniculaStock.removeProduct("F2", 30);
		botaniculaStock.removeProduct("D1", 12);
		
		botanicula.getStock().printStock();
		/*
		//Instantiate a cart to buy items
		var cart = new ShoppingCart();
		
		//We add the items we want to buy
		
		cart.addItem("D2", 3, botaniculaStock);
		cart.addItem("F1", 2, botaniculaStock);
		cart.addItem("T2", 1, botaniculaStock);
		
		//And now we create a ticket for that shopping cart
		botanicula.purchase(cart);
		
		botanicula.getStock().printStock();
		
		//We save purchases of the day
		botanicula.saveTickets();
		
		//We load them again
		//botanicula.loadTickets(ticketsPath);
		
		botanicula.printTickets();
		
		//And we look at all the money we've won
		botanicula.printTotalSales();*/
	}
}
