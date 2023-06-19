package n2exercici1;

import java.util.*;

public class ShoppingCart {

	private List<Product> products;
	
	public ShoppingCart() {
		products = new ArrayList<Product>();
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public void addItem(String id, int ammount, Stock stock) {
		var product = stock.getProduct(id);
		stock.removeProduct(id, ammount);
		product.setAmmount(ammount);
		products.add(product);
	}
	
	public void removeItem(String id, int ammount, Stock stock) {
		var product = getProduct(id);
		if(stock.getProduct(id)!=null) {
			stock.getProduct(id).increaseAmmount(ammount);
		}else {
			stock.addProduct(product);
		}
		if(product.getAmmount()-ammount > 1) {
			product.decreaseAmmount(ammount);
		}else {
			products.remove(product);
		}
	}
	
	public Product getProduct(String id) {
		return products.stream()
						.filter(p -> p.getId().equals(id))
						.findFirst()
						.orElse(null);
	}
}
