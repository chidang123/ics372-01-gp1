package store.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
			String memberId = store.enrollMember(addresses[count], names[count], phones[count]);
			assert memberId.equals("XM" + Integer.toString(count));
		}
		int subscript = 0;
		for (Iterator<Member> memberIterator = store.memberIterator(); memberIterator.hasNext();) {
			Member member = memberIterator.next();
			assert member.getName().equals(names[subscript]);
			assert member.getAddress().equals(addresses[subscript]);
			assert member.getPhoneNumber().equals(phones[subscript++]);
		}

	}

	public void testRemoveMember() {
		testEnrollMember();
		store.removeMember("XM1");
	}

	/*
	 * Test store.addProduct and ensure that new product is present in
	 */
	public void testAddProduct() {
		final int INITIAL_INVENTORY = 10;
		final int REORDER_THRESHOLD = 8;
		Random random = new Random();

		Product checkoutProduct = store.addProduct(Integer.toString(random.nextInt()), "Dulce de Leche", 7.99,
				INITIAL_INVENTORY, REORDER_THRESHOLD);
		assert store.listAllProducts().contains(checkoutProduct.getName());
	}

	/*
	 * Test store.checkOut to ensure that order is added and that inventory is
	 * updated.
	 */
	public void testCheckout() {
		final int INITIAL_INVENTORY = 10;
		final int REORDER_THRESHOLD = 8;
		final int ORDER_QUANTITY = 3;

		ArrayList<String> productIds = new ArrayList<String>();
		ArrayList<Integer> productQtys = new ArrayList<Integer>();
		Random random = new Random();

		String memberString = store.enrollMember("Nathan Souer", "Maplewood, MN", "6515555555");
		Product checkoutProduct = store.addProduct(Integer.toString(random.nextInt()), "Alfajores", 4.99,
				INITIAL_INVENTORY, REORDER_THRESHOLD);
		productIds.add(checkoutProduct.getID());
		productQtys.add(ORDER_QUANTITY);
		store.checkout(memberString, productIds, productQtys);
		assert store.listOutstandingOrders().contains(checkoutProduct.getName());
		assert checkoutProduct.getInventory() == (INITIAL_INVENTORY - ORDER_QUANTITY);
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
