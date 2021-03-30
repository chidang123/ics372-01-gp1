package store.tests;

import java.util.ArrayList;

import store.entities.Member;
import store.entities.Product;
import store.entities.Store;

/**
 * This class generates sample automated tests for the store system using
 * asserts.
 * 
 * @author Brahma Dathan Modified by Andrew Vick, Keith Butterfield, Daniel
 *         Renaud, Nathan Souer, and Rael Ogega
 *
 */
public class AutomatedTester {
	private Store store;
	private String[] names = { "Nate", "Nathaniel", "Daniel", "Dan", "Andrew", "Andy", "Keith", "Rae" };
	private String[] addresses = { "123 Street", "234 Street", "345 Street", "456 Street", "567 Street" };
	private String[] phones = { "987-654-3210", "876-543-2109", "765-432-1098", "654-321-0987", "543-210-9876" };
	private Member[] members = new Member[5];
	private String[] productNames = { "Milk", "Milky Way", "Milk Bar", "Pizza", "Water", "Milk Duds", "Onions",
			"Tomatoes", "Lettuce", "Grapes", "Tuna", "Apples, Berries", "Pasta", "Rice", "Cereal", "Pickles", "Celery",
			"Bacon", "Floss" };
	private double[] prices = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 15, 17, 18, 19, 20 };
	private Product[] products = new Product[20];

	AutomatedTester() {
		this.store = Store.instance();
	}

	/**
	 * Tests Member creation.
	 */
	public void testEnrollMember() {
		for (int count = 0; count < members.length; count++) {
			String memberId = store.enrollMember(names[count], addresses[count], phones[count], true);
			members[count] = store.memberListSearch(memberId);
			assert (store.memberListSearch(memberId).getName()).equals(names[count]);
			assert (store.memberListSearch(memberId).getPhoneNumber()).equals(phones[count]);
			assert (store.memberListSearch(memberId).getAddress()).equals(addresses[count]);
		}

	}

	/*
	 * Test store.removeMember and ensure that it is no longer present in the
	 * output.
	 */
	public void testRemoveMember() {
		Member memberBuffer = members[members.length - 1];
		store.removeMember(members[members.length - 1].getID());
		Boolean notDeleted = store.retrieveMemberInfo(memberBuffer.getName()).contains(memberBuffer.getID());
		assert !notDeleted;
	}

	/*
	 * Test store.addProduct and ensure that new product is present in
	 */
	public void testAddProduct() {
		final int INITIAL_INVENTORY = 10;
		final int REORDER_THRESHOLD = 8;

		for (int addIndex = 0; addIndex < products.length - 1; addIndex++) {
			products[addIndex] = store.addProduct(Integer.toString(addIndex), productNames[addIndex], prices[addIndex],
					INITIAL_INVENTORY, REORDER_THRESHOLD);
			assert store.listAllProducts().contains(products[addIndex].getName());
		}

	}

	/*
	 * Test store.checkOut to ensure that order is added and that inventory is
	 * updated.
	 */
	public void testCheckout() {
		final int ORDER_QUANTITY = 3;
		ArrayList<String> productIds = new ArrayList<String>();
		ArrayList<Integer> productQtys = new ArrayList<Integer>();
		int inventoryBuffer = products[0].getInventory();

		productIds.add(products[0].getID());
		productQtys.add(ORDER_QUANTITY);

		store.checkout(members[0].getID(), productIds, productQtys);
		assert store.listOutstandingOrders().contains(products[0].getName());
		assert products[0].getInventory() == (inventoryBuffer - ORDER_QUANTITY);
	}

	/*
	 * Tests store.processShipment() to ensure that inventory is updated and order
	 * is removed from list
	 */
	public void testProcessShipment() {

	}

	public void testChangePrice() {
		testAddProduct();
		store.changePrice(products[0].getID(), products[0].getPrice() + 0.5);
	}

	public void testAll() {
		testEnrollMember();
		testRemoveMember();
		testAddProduct();
		testCheckout();
		testProcessShipment();
		testChangePrice();
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}
