package n1exercici1;

import java.util.*;
import java.io.*;

public class Stock {

	//---ATTRIBUTES---
	private List<Product> products;
	private double stockValue;
	private Florist florist;
	private String filePath;
	
	//---CONSTRUCTOR---
	public Stock(String filePath) {
		this.products = new ArrayList<Product>();
		stockValue = 0;
		this.filePath = filePath;
	}
	
	//---GETTERS & SETTERS---
	public double getStockValue() {
		return stockValue;
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public void setFlorist(Florist florist) {
		this.florist = florist;
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
	
	public Product getFlower(int id) {
		return products.stream()
					.filter(p -> p instanceof Flower)
					.filter(p -> p.getId()==id)
					.findFirst()
					.orElse(null);
	}
	
	public Product getDecoration(int id) {
		return products.stream()
					.filter(p -> p instanceof Decoration)
					.filter(p -> p.getId()==id)
					.findFirst()
					.orElse(null);
	}
	
	public Product getTree(int id) {
		return products.stream()
					.filter(p -> p instanceof Tree)
					.filter(p -> p.getId()==id)
					.findFirst()
					.orElse(null);
	}
	
	//---DATA CONTROL---
	
	public void addProduct(Product product) {
		products.add(product);
		stockValue += product.getPrice()*product.getAmmount();
	}
	
	
	
	public void removeFlower(int id) {
		if(getFlower(id)!=null) {
			if(getFlower(id).getAmmount()>1) {
				getFlower(id).decreaseAmmount();
			}else {
				products.remove(getFlower(id));	
			}
		}
	}
	
	public void removeDecoration(int id) {
		if(getDecoration(id)!=null) {
			if(getDecoration(id).getAmmount()>1) {
				getDecoration(id).decreaseAmmount();
			}else {
				products.remove(getDecoration(id));
			}
		}
		products.remove(getDecoration(id));	
	}
	
	public void removeTree(int id) {
		if(getTree(id)!=null) {
			if(getTree(id).getAmmount()>1) {
				getTree(id).decreaseAmmount();
			}else {
				products.remove(getTree(id));
			}
		}
		products.remove(getTree(id));	
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
	}
	
	//PERSISTENCE
	public List<String> toData() {
		var data = new ArrayList<String>();
		for(Product product : products) {
			data.add(product.toData());
		}
		return data;
	}
	
	private void updateFile() {
		try {
			var writer = new BufferedWriter(new FileWriter(filePath));
			for(Product product : products) {
				writer.write(product.toData());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
