package n1exercici1;

import java.util.*;

public class Stock {

	private List<Product> products;
	private double stockValue;
	private static Stock stock;
	
	private Stock() {
		this.products = new ArrayList<Product>();
	}
	
	public static Stock getInstance() {
		
		if(stock == null) {
			stock = new Stock();
		}
		return stock;
	}
	
	public double getStockValue() {
		return stockValue;
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public List<Tree> getTreeStock(){
		List<Tree> trees = new ArrayList<Tree>();
		for(Product product : products) {
			if(product instanceof Tree) {
				trees.add((Tree)product);
			}
		}
		return trees;
	}
	public List<Flower> getFlowerStock(){
		List<Flower> flowers = new ArrayList<Flower>();
		for(Product product : products) {
			if(product instanceof Flower) {
				flowers.add((Flower)product);
			}
		}
		return flowers;
	}
	public List<Decoration> getDecorationStock(){
		List<Decoration> decorations = new ArrayList<Decoration>();
		for(Product product : products) {
			if(product instanceof Decoration) {
				decorations.add((Decoration)product);
			}
		}
		return decorations;
	}
	public void addProduct(Product product) {
		products.add(product);
		stockValue += product.getPrice();
	}
}
