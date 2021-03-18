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

	}

	public void testRemoveMember() {
	}

	public void testProcessShipment() {
	}

	public void testAddProduct() {
	}

	public void testChangePrice() {
	}

	public void testAll() {
		testEnrollMember();
		testRemoveMember();
		testAddProduct();
		testProcessShipment();
		testChangePrice();
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}
