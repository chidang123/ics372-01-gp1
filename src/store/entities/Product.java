/***
 * @author Keith Butterfield
 */

package store.entities;

import java.io.Serializable;

/**
 * This class represents individual products to be stocked in the store
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private double price;
	private int inventory;
	private int reorderThreshold;

	public Product(String id, String name, double price, int inventory,
			int reorderThreshold) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.inventory = inventory;
		this.reorderThreshold = reorderThreshold;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
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

	public Boolean setInventory(int inventory) {
		this.inventory = inventory;
		if (inventory <= this.reorderThreshold) {
			return true;
		}
		return false;
	}

	public int getReorderThreshold() {
		return reorderThreshold;
	}

	public void setReorderThreshold(int reorderThreshold) {
		this.reorderThreshold = reorderThreshold;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", inventory=" + inventory + ", reorderThreshold="
				+ reorderThreshold + "]";
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
