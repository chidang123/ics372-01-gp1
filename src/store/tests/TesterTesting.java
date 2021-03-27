package store.tests;


/*
 * This class can be deleted when all parts of the AutomatedTester are
 * working. Using in the interim to test my parts.
 */
public class TesterTesting {
	public static void main(String[] args) {
		AutomatedTester quisCustodietIpsosCustodes = new AutomatedTester();
		quisCustodietIpsosCustodes.testCheckout();	
		quisCustodietIpsosCustodes.testAddProduct();
		System.out.println("If you see this, both tests have passed.");
	}

}
