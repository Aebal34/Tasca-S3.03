package n2exercici1;

import java.util.*;
import java.io.*;

public class Stock {

	//---ATTRIBUTES---
	private List<Product> products= new ArrayList<Product>();
	private double value;
	private ProductDao productDao;
	
	//---CONSTRUCTOR---
	public Stock(ProductDao productDao) {
		value = 0;
		this.productDao = productDao;
		this.products = productDao.getAll();
		updateValue();
	}
	
	//---GETTERS & SETTERS---
	public double getValue() {
		return value;
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
	
	public Product getProduct(String id) {
		return products.stream()
						.filter(p -> p.getId().equals(id))
						.findFirst()
						.orElse(null);
	}
	
	//---DATA CONTROL---
	public void addProduct(Product product) {
		products.add(product);
		value += product.getPrice()*product.getAmount();
		updateFile();
	}
	
	public void removeProduct(String id, int ammount) {
		if(getProduct(id) != null) {
			var product = getProduct(id);
			if((product.getAmount()-ammount)>=1) {
				value -= product.getPrice()*ammount;
				product.decreaseAmmount(ammount);
			}else {
				value -= product.getPrice()*product.getAmount();
				products.remove(product);
			}
		}
		updateFile();
	}
	
	public void updateValue() {
		this.value = 0;
		for(Product product : products) {
			this.value += product.getPrice()*product.getAmount();
		}
	}
	
	public void printStock() {
		
		  System.out.println(" __________________________________________"+"\n");
		for(Tree tree : getTreeStock()) {
			System.out.print("|    "+tree+"\n");
		}
		for(Flower flower : getFlowerStock()) {
			System.out.print("|    "+flower+"\n");
		}
		for(Decoration decoration : getDecorationStock()) {
			System.out.print("|    "+decoration+"\n");
		}
		System.out.println(" __________________________________________");
		System.out.println("Total stock value: "+value);
	}
	
	//PERSISTENCE
}
