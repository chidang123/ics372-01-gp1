package store.tests;

import store.entities.Product;
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
		/*
		 * We need a way to get a member ID for this process without manually
		 * entering the number. Enroll member could return a Member object
		 * instead of a String, when whatever receives the object could
		 * call toString()
		 */
	}
	
	public void testAddProduct() {
		for ( int addProdIndex = 0; addProdIndex < productNames.length ;
			addProdIndex++ ) {
			store.addProduct(Integer.toString(addProdIndex),
			productNames[addProdIndex], prices[addProdIndex],
			addProdIndex + 5, ( addProdIndex + 1 ) * 2 );
		}
	}
	
	public void testCheckout() {
		/*
		 * Again how do we get a list of IDs to checkout without
		 * manual intervention?
		 */
	}

	public void testProcessShipment() {
		/*
		 * Again we're depending on human input?
		 */
		store.processShipment(String orderID);
	}

	
	public void testChangePrice() {
		/* Human input? */
		store.changePrice(int productID, int priceDollars, int priceCents);
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
