/***
 * @author Keith Butterfield
 * 
 * Took code and inspiration from Andrew's Member List java file
 * 
 */

package store.entities;

//Change//Change//Change
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//Took code from Andrew's Member List java file

public class ProductList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Product> products = new LinkedList<Product>();

	/**
	 * Adds to the inventory of existing products
	 * 
	 * @param id of the product to be added
	 */
	public void addExisitingProduct(String id) {
		for (Iterator<Product> iterator = products.iterator(); iterator
				.hasNext();) {
			Product product = iterator.next();
			if (product.getID().equals(id)) {
				product.setInventory(product.getInventory()
						+ (product.getReorderThreshold() * 2));
				;
				break;
			}
		}
	}

	// business case 3,
	public boolean addNewProduct(Product product) {
		products.add(product);
		return true;
	}

	// business case 7
	public void showProductInfo(String id) {
		for (Iterator<Product> iterator = products.iterator(); iterator
				.hasNext();) {
			Product product = iterator.next();
			if (product.getID().equals(id)) {
				product.toString();
			}
		}
	}

	/**
	 * finds a product via ID number and changes the product's price to the
	 * price passed as an argument
	 */
	public void changePrice(String id, double newPrice) {
		for (Iterator<Product> iterator = products.iterator(); iterator
				.hasNext();) {
			Product product = iterator.next();
			if (product.getID().equals(id)) {
				product.setPrice(newPrice);
			}
		}
	}

	/**
	 * removes the passed product from the list of products
	 */
	public boolean removeProduct(Product product) {
		int index = products.indexOf(product);
		products.remove(index);
		return true;
	}

	/**
	 * checks whether a product with a given ID number exists in the list of
	 * products
	 */
	public Product search(String productId) {
		for (Iterator<Product> iterator = products.iterator(); iterator
				.hasNext();) {
			Product product = iterator.next();
			if (product.getID().equals(productId)) {
				return product;
			}
		}
		return null;
	}

	public Iterator<Product> iterator() {
		return products.iterator();
	}

	public String getBasics() {
		double total = 0;
		String result = "";
		for (Iterator<Product> iterator = products.iterator(); iterator
				.hasNext();) {
			Product product = iterator.next();
			result += product.getName() + " , " + product.getPrice() + "\n";
			total += product.getPrice();
		}
		result += "Total: " + total;
		return result;
	}

	@Override
	public String toString() {
		return products.toString();
	}
}
