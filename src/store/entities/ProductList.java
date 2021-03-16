/***
 * @author Keith Butterfield
 * 
 * Took code and inspiration from Andrew's Member List java file
 * 
 */

package store.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
//import java.util.Scanner;  

//Took code from Andrew's Member List java file

public class ProductList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Product> products = new LinkedList<Product>();
//	private Scanner scan = new Scanner(System.in);

	// business case 5
	public void addExisitingProduct(String id) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if (product.getId().equals(id)) {
				product.setInventory(product.getReorderThreshold() * 2); // can't remember is its * 2 all the time of or just first time
				System.out.println("Product [id=" + product.getID() + ", name=" + product.getName() + ", price=" + product.getPrice() + ", inventory=" + inventory);
				break; // breaking loop and function - no longer needs it since item found
			}
		}
	}

	// business case 3	
	public boolean addNewProduct(Product product) {
		products.add(product);
		return true;
	}

	// business case 7
	public void showProductInfo(String id) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if (product.getId().equals(id)) {
				product.toString();
			}
		}
	}

	// business case 6
	public void changePrice(String id, double newPrice) {
		for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if (product.getId().equals(id)) {
				product.setPrice(newPrice);
			}
		}
	}

	public boolean removeProduct(Product product) {
		int index = products.indexOf(product);
		products.remove(index);
		return true;
	}

	public Iterator<Product> iterator() {
		return products.iterator();
	}

	@Override
	public String toString() {
		return products.toString();
	}
}
