package n1exercici1;

public class App {

	public static void main(String[] args) {
		
		//Instantiate florist with filepath of Stock.txt
		String stockPath = "D:\\eclipse-workspace\\Tasca-S3.03\\src\\main\\resources\\StockData.txt";
		Florist botanicula = new Florist("Bonaticula", new Stock(stockPath));
		
		//Instantiate Stock and add 2 trees, 2 flowers and 2 decorations

		Stock botaniculaStock = botanicula.getStock();
		botaniculaStock.addProduct(new Tree(2.78, 69.95, 2));
		botaniculaStock.addProduct(new Tree(0.54, 29.95, 1));
		botaniculaStock.addProduct(new Flower("Purple", 18.45, 5));
		botaniculaStock.addProduct(new Flower("White", 14.25, 3));
		botaniculaStock.addProduct(new Decoration("Wood", 129.99, 2));
		botaniculaStock.addProduct(new Decoration("Metal", 81.75, 4));
		
		botaniculaStock.printStock();
		
		//Remove diverse products from stock
		botaniculaStock.removeProduct("T1", 1);
		botaniculaStock.removeProduct("F2", 2);
		botaniculaStock.removeProduct("D1", 2);
		
		botanicula.getStock().printStock();
		
		

	}

}
