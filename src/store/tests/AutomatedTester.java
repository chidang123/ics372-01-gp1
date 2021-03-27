package store.tests;

import store.entities.Product;

import java.util.ArrayList;

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
		 * This value is hard coded which is not ideal, but since we
		 * expect it to be input by human it might be reasonable.
		 */
		store.removeMember("XM0");
	}
	
	public void testAddProduct() {
		for ( int addProdIndex = 0; addProdIndex < productNames.length ;
			addProdIndex++ ) {
			products[addProdIndex] = store.addProduct(
			Integer.toString(addProdIndex),
			productNames[addProdIndex], prices[addProdIndex],
			addProdIndex + 5, ( addProdIndex + 1 ) * 2 );
		}
	}
	
	public void testCheckout() {
		ArrayList<String> productIds = new ArrayList<String>();
		ArrayList<Integer> productQtys = new ArrayList<Integer>();
		for ( int testChOutIndex = 0; testChOutIndex < products.length;
		testChOutIndex++  ) {
			productIds.add(products[testChOutIndex].getID());
			productQtys.add(products.length - testChOutIndex);
		}
		// Hard coded member again
		store.checkout("XM1", productIds,productQtys);
	}

	public void testProcessShipment() {
		
	}	
	
	public void testChangePrice() {
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
