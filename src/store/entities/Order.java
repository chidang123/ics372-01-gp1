//Change
package store.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Order represents an order for a product being shipped to the store
 * 
 * @author Rael Ogega
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Product product;
	private int newStock;
	private static final String ORDER_STRING = "OR";
	private static int idCounter;
	private Date date;

	/**
	 * Creates a single order
	 * 
	 * @param product the product associated with the order
	 */
	public Order(Product product) {
		this.product = product;
		newStock = 2 * product.getReorderThreshold();
		id = ORDER_STRING + ++idCounter;
		date = new Date();
	}

	public String getID() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public int getNewStock() {
		return newStock;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		String string = "id: " + id + ", Product name: " + product.getName() + ", New Stock: " + newStock;
		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
