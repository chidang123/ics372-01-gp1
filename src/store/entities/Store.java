package store.entities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Iterator;

//Change
public class Store {
	private static Store store;
	private MemberList memberList = new MemberList();
	private ProductList productList = new ProductList();
	private OrderList orderList = new OrderList();

	/**
	 * private constructor for singleton pattern
	 */

	private Store() {
	}

	/**
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

	public boolean removeMember(String memberID) {
		Member member = memberList.search(memberID);
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

	public String checkout(Hashtable<String, Integer> cart) {
		return "Checkout Output.";
	}

	/**
	 * processes items in the order
	 */
	public String processShipment(String orderID) {
		if (store.orderList.search(orderID) != null) {
			Product updatedProduct = store.orderList.search(orderID).getProduct();
			store.productList.addExisitingProduct(updatedProduct.getID());
			store.orderList.removeOrder(orderID);
			return updatedProduct.toString();
		}
		return null;

	}

	public String changePrice(int productID, int priceDollars, int priceCents) {
		return "Successfully changed price on " + productID + " to $" + priceDollars + "." + priceCents + ".";
	}

	public String retrieveProductInfo(String productString) {
		int count = 0;
		String productDisplay = "Products beginning with " + productString + ":\n";
		for (Iterator<Product> iterator = productList.iterator(); iterator.hasNext();) {
			Product product = iterator.next();
			if (product.getName().startsWith(productString)) {
				productDisplay += product.toString() + "\n";
				count++;
			}
		}
		return productDisplay + "\n" + "Found " + count + " Results";

	}

	/**
	 * displays a list of members that have names matching the String argument
	 */
	public String retrieveMemberInfo(String memberString) {
		int count = 0;
		String memberDisplay = "Members beginning with " + memberString + ":\n";
		for (Iterator<Member> iterator = memberList.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			if (member.getName().startsWith(memberString)) {
				memberDisplay += member.toString() + "\n";
				count++;
			}
		}
		return memberDisplay + "\n" + "Found " + count + " Results";
	}

	public String printTransactions(int memberID) {
		return "Transactions for " + memberID;
	}

	public String listOutstandingOrders() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("id,product,date,qty\n");
		for (Iterator<Order> iterator = orderList.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			buffer.append(order.getID());
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
			buffer.append(product.getID());
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
	 * displays all members
	 */
	public String listAllMembers() {
		Iterator<Member> iterator = memberList.iterator();
		String memberDisplay = "List of members (name, address, phone, ID number)";
		while (iterator.hasNext()) {
			Member member = iterator.next();
			memberDisplay += member.toString() + "\n";
		}
		return memberDisplay;
	}

	/**
	 * @return true iff the data is saved
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
