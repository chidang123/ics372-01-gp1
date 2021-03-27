package store.tests;

import store.entities.Product;

import java.util.ArrayList;
import java.util.Random;

import store.entities.Member;
import store.entities.Order;
import store.entities.Store;
import store.ui.UserInterface;

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
	private String[] names = { "n1", "n2", "n3" };
	private String[] addresses = { "a1", "a2", "a3" };
	private String[] phones = { "p1", "p2", "p3" };
	private Member[] members = new Member[3];
	private String[] productNames = { "t1", "t2", "t3", "t4", "t5", "t6" };
	private double[] prices = { 10, 5, 6, 4, 3, 11 };
	private Product[] products = new Product[6];

	AutomatedTester() {
		this.store = Store.instance();
	}
	/**
	 * Tests Member creation.
	 */
	public void testEnrollMember() {
		for ( int enrollIndex = 0; enrollIndex < names.length; enrollIndex++ ) {
			store.enrollMember(names[enrollIndex], addresses[enrollIndex],
			phones[enrollIndex]);		
		}

	}

	public void testRemoveMember() {
		testEnrollMember();
		store.removeMember("XM1");
	}
	
	/*
	 * Test store.addProduct and ensure that new product is present
	 * in 
	 */
	public void testAddProduct() {
		final int INITIAL_INVENTORY = 10;
		final int REORDER_THRESHOLD = 8;
		Random random = new Random();
		
		Product checkoutProduct = store.addProduct(
		Integer.toString(random.nextInt()), "Dulce de Leche", 7.99,
		INITIAL_INVENTORY, REORDER_THRESHOLD);
		assert store.listAllProducts().contains(checkoutProduct.getName());
	}
	
	/*
	 * Test store.checkOut to ensure that order is added
	 * and that inventory is updated.
	 */
	public void testCheckout() {
		final int INITIAL_INVENTORY = 10;
		final int REORDER_THRESHOLD = 8;
		final int ORDER_QUANTITY = 3;
		
		ArrayList<String> productIds = new ArrayList<String>();
		ArrayList<Integer> productQtys = new ArrayList<Integer>();
		Random random = new Random();

		String memberString = store.enrollMember(
		"Nathan Souer", "Maplewood, MN", "6515555555");
		Product checkoutProduct = store.addProduct(
		Integer.toString(random.nextInt()), "Alfajores", 4.99,
		INITIAL_INVENTORY, REORDER_THRESHOLD);
		productIds.add(checkoutProduct.getID());
		productQtys.add(ORDER_QUANTITY);
		store.checkout(memberString, productIds, productQtys);
		assert store.listOutstandingOrders().contains(checkoutProduct.getName());
		assert checkoutProduct.getInventory() ==  (INITIAL_INVENTORY - ORDER_QUANTITY);
	}
	public void testProcessShipment() {
		
	}	
	
	public void testChangePrice() {
		testAddProduct();
		store.changePrice(products[0].getID(),
		products[0].getPrice() + 0.5);
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
