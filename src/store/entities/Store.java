package store.entities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

//Change
public class Store {
	private static Store store;
	private MemberList memberList = new MemberList();
	private ProductList productList = new ProductList();
	private OrderList orderList = new OrderList();

	/**
	 * Private for the singleton pattern Creates the catalog and member collection
	 * objects
	 */

	private Store() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Store instance() {
		if (store == null) {
			return store = new Store();
		} else {
			return store;
		}
	}

	public String enrollMember(String name, String address, String phoneNumber) {
		Member member = new Member(name, address, phoneNumber);
		memberList.insertMember(member);
		return member.toString();
	}

	public boolean removeMember(String memberId) {
		Member member = memberList.search(memberId);
		if (member != null) {
			memberList.removeMember(member);
			return true;
		} else {
			return false;
		}
	}

	public Product addProduct(String id, String name, double price, int inventory, int reorderThreshold) {
		Product product = new Product(id, name, price, inventory, reorderThreshold);
		productList.addNewProduct(product);
		return product;
	}

	public String checkout(ArrayList<String> productIds,
	ArrayList<Integer> productQtys) {
		double runningTotal = 0;
		StringBuilder buffer = new StringBuilder();
		ArrayList<String> reorderList = new ArrayList<String>();
		
		if ( productIds.size() != productQtys.size() ) {
			return "Your parameters are mismatched.";
		}

		
		for ( int i = 0; i < productIds.size(); i++ ) {
			Product productBuffer = store.productList.search(productIds.get(i));
			buffer.append(productBuffer.getName());
			buffer.append(",");
			buffer.append(Integer.toString(productQtys.get(i)));
			buffer.append(",");
			buffer.append(Double.toString(productBuffer.getPrice()));
			buffer.append(",");
			buffer.append(Double.toString(productBuffer.getPrice() *
			productQtys.get(i)));
			runningTotal += ( productBuffer.getPrice() * productQtys.get(i) );
			Boolean reorder = productBuffer.setInventory(
			productBuffer.getInventory() - productQtys.get(i));
			if (reorder) {
				reorderList.add(productBuffer.getId());
			}
		}

		buffer.append(",,," + Double.toString(runningTotal));
		
		// Add logic here issuing reorders and adding output to buffer.
		
		return buffer.toString();
	}

	/**
	 * Processes items in the order
	 * 
	 * @param orderId
	 */
	public String processShipment(String orderId) {
		if (store.orderList.search(orderId) != null) {
			Product updatedProduct = store.orderList.search(orderId).getProduct();
			store.productList.addExisitingProduct(updatedProduct.getId());
			store.orderList.removeOrder(orderId);
			return updatedProduct.toString();
		}
		return null;

	}

	public String changePrice(int productId, int priceDollars, int priceCents) {
		return "Successfully changed price on " + productId + " to $" + priceDollars + "." + priceCents + ".";
	}

	public String retrieveProductInfo(int productId) {
		return "Product details for " + productId;
	}

	/**
	 * Displays all members starting with a given String
	 * 
	 * @param memberString the beginning of a member's name
	 * @return display of all members starting with the string
	 */
	public String retrieveMemberInfo(String memberString) {
		String memberDisplay = "Members Beginning with " + memberString + ":\n";
		for (Iterator<Member> iterator = memberList.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			if (member.getName().startsWith(memberString)) {
				memberDisplay += member.toString() + "\n";
			}
		}
		return memberDisplay;
	}

	public String printTransactions(int memberId) {
		return "Transactions for " + memberId;
	}

	public String listOutstandingOrders() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("id,product,date,qty\n");
		for (Iterator<Order> iterator = orderList.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			buffer.append(order.getId());
			buffer.append(",");
			buffer.append(order.getProduct().getName());
			buffer.append(",");
			buffer.append(order.getDate().toString());
			buffer.append(",");
			buffer.append(Integer.toString(order.getNewStock()));
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public String listAllProducts() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("name,id,onhand,price,reorder\n");
		for (Iterator<Product> iterator = productList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			buffer.append(product.getName());
			buffer.append(",");
			buffer.append(product.getId());
			buffer.append(",");
			buffer.append(Integer.toString(product.getInventory()));
			buffer.append(",");
			buffer.append(Double.toString(product.getPrice()));
			buffer.append(",");
			buffer.append(Integer.toString(product.getReorderThreshold()));
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * Displays all members
	 */
	public String listAllMembers() {
		Iterator<Member> iterator = memberList.iterator();
		String memberDisplay = "List of members (name, address, phone, id)";
		while (iterator.hasNext()) {
			Member member = iterator.next();
			memberDisplay += member.toString() + "\n";
		}
		return memberDisplay;
	}

	/**
	 * Serializes the Store object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("StoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(store);
			Member.save(output);
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	public Iterator<Member> iterator() {
		return memberList.iterator();
	}
}
