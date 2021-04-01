package store.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * maintains a list of Order objects
 * 
 * @author Rael Ogega
 */
public class OrderList implements Iterable<Order>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<Order> orders = new LinkedList<Order>();

	/**
	 * adds an order to the list
	 */
	public boolean addOrder(Order order) {
		orders.add(order);
		return true;
	}

	/**
	 * removes an order with the passed ID number from the list
	 */
	public Order removeOrder(String orderID) {
		for (ListIterator<Order> iterator = orders.listIterator(); iterator
				.hasNext();) {
			Order order = iterator.next();
			String id = order.getID();
			if (id.equals(orderID)) {
				iterator.remove();
				return order;
			}
		}
		return null;
	}

	/**
	 * removes an order that contains a product with the passed ID number
	 * 
	 * @param productID
	 */
	public Order removeOrderOnProduct(String productID) {
		for (ListIterator<Order> iterator = orders.listIterator(); iterator
				.hasNext();) {
			Order order = iterator.next();
			String id = order.getProduct().getID();
			if (id.equals(productID)) {
				iterator.remove();
				return order;
			}
		}
		return null;
	}

	/**
	 * checks whether an order with a given ID number exists
	 * 
	 * @param orderID
	 */
	public Order search(String orderID) {
		for (Iterator<Order> iterator = orders.iterator(); iterator
				.hasNext();) {
			Order order = iterator.next();
			if (order.getID().equals(orderID)) {
				return order;
			}
		}
		return null;
	}

	/**
	 * checks whether an order with a given productId exists
	 * 
	 * @param productId
	 */
	public Order searchByProduct(String productID) {
		for (ListIterator<Order> iterator = orders.listIterator(); iterator
				.hasNext();) {
			Order order = iterator.next();
			String id = order.getProduct().getID();
			if (id.equals(productID)) {
				return order;
			}
		}
		return null;
	}

	@Override
	public Iterator<Order> iterator() {
		return orders.iterator();
	}
}
