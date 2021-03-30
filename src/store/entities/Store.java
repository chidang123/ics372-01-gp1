package store.entities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;

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

	public Iterator<Member> memberIterator() {
		return memberList.iterator();
	}

	public String enrollMember(String name, String address, String phoneNumber) {
		Member member = new Member(name, address, phoneNumber);
		memberList.insertMember(member);
		return member.getID();
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

	public String checkout(String memberID, ArrayList<String> productIds, ArrayList<Integer> productQtys) {
		double runningTotal = 0;
		StringBuilder buffer = new StringBuilder();
		ArrayList<String> reorderList = new ArrayList<String>();
		Transaction transactionBuffer = new Transaction(memberID);

		if (productIds.size() != productQtys.size()) {
			return "Your parameters are mismatched.";
		}

		buffer.append("\nItem\t\t\tQuantity\t\tUnit Price\t\tPrice\n");

		for (int cartIndex = 0; cartIndex < productIds.size(); cartIndex++) {
			Product productBuffer = store.productList.search(productIds.get(cartIndex));
			buffer.append(productBuffer.getName());
			buffer.append("\t\t\t");
			buffer.append(Integer.toString(productQtys.get(cartIndex)));
			buffer.append("\t\t\t");
			buffer.append(Double.toString(productBuffer.getPrice()));
			buffer.append("\t\t\t");
			buffer.append(Double.toString(productBuffer.getPrice() * productQtys.get(cartIndex)));
			buffer.append("\n");
			runningTotal += (productBuffer.getPrice() * productQtys.get(cartIndex));
			Boolean reorder = productBuffer.setInventory(productBuffer.getInventory() - productQtys.get(cartIndex));
			if (reorder) {
				reorderList.add(productBuffer.getID());
			}
			transactionBuffer.addProduct(new Product(productBuffer.getID(), productBuffer.getName(),
					productBuffer.getPrice(), productQtys.get(cartIndex), productBuffer.getReorderThreshold()));
		}

		memberList.search(memberID).addMemberTransaction(transactionBuffer);

		buffer.append("\nCart Total: " + Double.toString(runningTotal) + "\n");

		if (!reorderList.isEmpty()) {
			buffer.append("\n\n");
			for (int reorderIndex = 0; reorderIndex < reorderList.size(); reorderIndex++) {
				Product productToOrder = productList.search(reorderList.get(reorderIndex));
				Order newOrder = new Order(productToOrder);
				orderList.addOrder(newOrder);
				buffer.append(Integer.toString(productToOrder.getReorderThreshold() * 2));
				buffer.append(" units of ");
				buffer.append(productToOrder.getName());
				buffer.append(" were ordered in ");
				buffer.append(newOrder.getID());
				buffer.append(".\n");
			}
		}
		return buffer.toString();
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

	public String changePrice(String productID, double newPrice) {
		Product product = productList.search(productID);
		productList.changePrice(productID, newPrice);
		return "The new price for " + product.getName() + " is: " + product.getPrice();
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

	public String printTransactions(String memberID, Date startDate, Date endDate) {
		int count = 0;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Member member = memberList.search(memberID);
		String parsedDate = null;
		String display = member.getName() + "'s transactions\n";
		for (ListIterator<Transaction> iterator = member.getTransactions().listIterator(); iterator.hasNext();) {
			Transaction transaction = iterator.next();
			if ((transaction.getDate().equals(startDate) || transaction.getDate().after(startDate))
					&& (transaction.getDate().equals(endDate) || transaction.getDate().before(endDate))) {
				parsedDate = dateFormat.format(transaction.getDate());
				display += "Transaction ID: " + transaction.getTransactionID() + " , Date: " + parsedDate + " \n";
				display += transaction.listProducts() + "\n";
				count++;
			}
		}
		return display + "\n" + "Found " + count + " Results";
	}

	public String listOutstandingOrders() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("ID\tProduct\t\tDate\tQuantity\n");
		for (Iterator<Order> iterator = orderList.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			buffer.append(order.getID());
			buffer.append("\t");
			buffer.append(order.getProduct().getName());
			buffer.append("\t");
			buffer.append(order.getDate().toString());
			buffer.append("\t");
			buffer.append(Integer.toString(order.getNewStock()));
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public String listAllProducts() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("Name\t\tID\tOn Hand\tPrice\tReorder\n");
		for (Iterator<Product> iterator = productList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			buffer.append(product.getName());
			buffer.append("\t");
			buffer.append(product.getID());
			buffer.append("\t");
			buffer.append(Integer.toString(product.getInventory()));
			buffer.append("\t");
			buffer.append(Double.toString(product.getPrice()));
			buffer.append("\t");
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
}
