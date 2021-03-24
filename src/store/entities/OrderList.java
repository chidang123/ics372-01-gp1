package store.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Maintains a list of Order objects. It is used by OrderList
 * 
 * @author Brahma Dathan
 *
 */
//Change//Change
public class OrderList implements Iterable<Order>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<Order> orders = new LinkedList<Order>();

	/**
	 * Adds an Order object to the list.
	 * 
	 * @param hold the Order object to be added
	 */
	public boolean addOrder(Order order) {
		orders.add(order);
		return true;
	}

	/**
	 * Removes the order from the orderList
	 * 
	 * @param the orderId for removing an order
	 * @return the removed Order object if the order could be removed; otherwise,
	 *         null
	 */
	public Order removeOrder(String orderId) {
		for (ListIterator<Order> iterator = orders.listIterator(); iterator.hasNext();) {
			Order order = iterator.next();
			String id = order.getId();
			if (id.equals(orderId)) {
				iterator.remove();
				return order;
			}
		}
		return null;
	}

	/**
	 * Removes the order for the specific product
	 * 
	 * @param productId the product id for removing a product
	 * @return the removed Order object if the order could be removed; otherwise,
	 *         null
	 */
	public Order removeOrderOnProduct(String productId) {
		for (ListIterator<Order> iterator = orders.listIterator(); iterator.hasNext();) {
			Order order = iterator.next();
			String id = order.getProduct().getId();
			if (id.equals(productId)) {
				iterator.remove();
				return order;
			}
		}
		return null;
	}

	/**
	 * Checks whether an order with a given order id exists.
	 * 
	 * @param orderId the id of the order
	 * @return a reference to the Order if orderId is valid, otherwise it return
	 *         false
	 * 
	 */
	public Order search(String orderId) {
		for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
			Order order = iterator.next();
			if (order.getId().equals(orderId)) {
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
