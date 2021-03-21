/***
 * @author Keith Butterfield
 * 
 * Took code and inspiration from Andrew's Member java file
 * 
 */

//Change
package store.entities;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private double price;
	private int inventory;
	private int reorderThreshold;

	public Product(String id, String name, double price, int inventory, int reorderThreshold) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.inventory = inventory;
		this.reorderThreshold = reorderThreshold;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getReorderThreshold() {
		return reorderThreshold;
	}

	public void setReorderThreshold(int reorderThreshold) {
		this.reorderThreshold = reorderThreshold;
	}

	// Auto generated toString - will change if needed
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", inventory=" + inventory
				+ ", reorderThreshold=" + reorderThreshold + "]";
	}

	// Did auto generate for id
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// Did auto generate for two of the fields - name and id
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
