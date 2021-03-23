package store.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;

import store.entities.Store;

public class UserInterface {
	private static UserInterface userInterface;
	private static Store store = Store.instance();
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final int EXIT = 0;
	private static final int ENROLL_MEMBER = 1;
	private static final int REMOVE_MEMBER = 2;
	private static final int ADD_PRODUCT = 3;
	private static final int CHECKOUT = 4;
	private static final int PROCESS_SHIPMENT = 5;
	private static final int CHANGE_PRICE = 6;
	private static final int RETRIEVE_PRODUCT_INFO = 7;
	private static final int RETRIEVE_MEMBER_INFO = 8;
	private static final int PRINT_TRANSACTIONS = 9;
	private static final int LIST_OUTSTANDING_ORDERS = 10;
	private static final int LIST_ALL_MEMBERS = 11;
	private static final int LIST_ALL_PRODUCTS = 12;
	private static final int SAVE = 13;
	private static final int HELP = 14;

	// Keith Added
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		UserInterface.instance().mainMenu();
	}

	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	private UserInterface() {
		System.out.println("Do you want to load from disk?");
	}

	public void logo() {
		System.out.println("\n\n" + "                  WELCOME TO THE\n"
				+ "   ____  _____ ____    ____  _____     _______ ____  \n"
				+ "  |  _ \\| ____|  _ \\  |  _ \\|_ _\\ \\   / / ____|  _ \\ \n"
				+ "  | |_) |  _| | | | | | |_) || | \\ \\ / /|  _| | |_) |\n"
				+ "  |  _ <| |___| |_| | |  _ < | |  \\ V / | |___|  _ < \n"
				+ "  |_| \\_\\_____|____/  |_| \\_\\___|  \\_/  |_____|_| \\_\\\n"
				+ "                                                   \n"
				+ "         ____ ___   ___  ____  _____ ____      _  _____ _____     _______ \n"
				+ "        / ___/ _ \\ / _ \\|  _ \\| ____|  _ \\    / \\|_   _|_ _\\ \\   / / ____|\n"
				+ "       | |  | | | | | | | |_) |  _| | |_) |  / _ \\ | |  | | \\ \\ / /|  _|  \n"
				+ "       | |__| |_| | |_| |  __/| |___|  _ <  / ___ \\| |  | |  \\ V / | |___ \n"
				+ "        \\____\\___/ \\___/|_|   |_____|_| \\_\\/_/   \\_\\_| |___|  \\_/  |_____|\n" + "\n\n");
	}

	public void help() {
		System.out.println(" At the prompt enter the number for the function " + "you need to perform:\n\n" + "  "
				+ ENROLL_MEMBER + ": Enroll a member.\t\t" + REMOVE_MEMBER + ": Remove a member.\n" + "  " + ADD_PRODUCT
				+ ": Add a product.\t\t" + CHECKOUT + ": Check out a memeber's items.\n" + "  " + PROCESS_SHIPMENT
				+ ": Process Shipment.\t\t" + CHANGE_PRICE + ": Change price.\n" + "  " + RETRIEVE_PRODUCT_INFO
				+ ": Retrieve product info.\t" + RETRIEVE_MEMBER_INFO + ": Retrieve member info.\n" + "  "
				+ PRINT_TRANSACTIONS + ": Print Transactions.\t" + LIST_OUTSTANDING_ORDERS
				+ ": List all outstanding orders.\n" + "  " + LIST_ALL_MEMBERS + ": List all members.\t\t"
				+ LIST_ALL_PRODUCTS + ": List all products.\n" + "  " + SAVE + ": Save.\t\t\t" + HELP + ": Help.\n"
				+ "  " + EXIT + ": Exit.\n");
	}

	private void mainMenu() {
		int selection;
		logo();
		help();
		while ((selection = getSelection()) != EXIT) {
			switch (selection) {
			case ENROLL_MEMBER:
				enrollMember();
				break;
			case REMOVE_MEMBER:
				removeMember();
				break;
			case ADD_PRODUCT:
				addProduct();
				break;
			case CHECKOUT:
				checkout();
				break;
			case PROCESS_SHIPMENT:
				processShipment();
				break;
			case CHANGE_PRICE:
				changePrice();
				break;
			case RETRIEVE_PRODUCT_INFO:
				retrieveProductInfo();
				break;
			case RETRIEVE_MEMBER_INFO:
				retrieveMemberInfo();
				break;
			case PRINT_TRANSACTIONS:
				printTransactions();
				break;
			case LIST_OUTSTANDING_ORDERS:
				listOutstandingOrders();
				break;
			case LIST_ALL_MEMBERS:
				listAllMembers();
				break;
			case LIST_ALL_PRODUCTS:
				listAllProducts();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				// Help will print because of loop
				break;
			}

			/*
			 * Remove sleep after methods are complete.
			 */
			try {
				Thread.sleep(5000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			logo();
			help();
		}

		System.exit(0);
	}

	public int getSelection() {
		do {
			try {
				int value = Integer.parseInt(getToken());
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}

		} while (true);
	}

	public String getToken() {
		do {
			try {
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	private void enrollMember() {
		System.out.println("Enter yes if the new member payed the fee, or no if they did not pay the fee");
		String feePaid = scan.next();
		if (feePaid.equals("yes")) {
			System.out.println("Enter the name of the new member: ");
			String name = scan.next();
			System.out.println("Enter the address of the new member: ");
			String address = scan.nextLine();
			System.out.println("Enter the phone number of the new member: ");
			String phoneNumber = scan.nextLine();
			System.out.println(store.enrollMember(name, address, phoneNumber));
		} else if (feePaid.equals("no")) {
			System.out.println("The member was not added because they did not pay the fee");
		} else {
			System.out.println("Please enter yes, or no for if the new member paid the fee");
		}
	}

	private void removeMember() {
		System.out.println("Enter the member's Id: ");
		String memberId = scan.next();
		boolean removeOrNot = store.removeMember(memberId);
		if (removeOrNot) {
			System.out.println("Member was removed from the system.");
		} else {
			System.out.println("There is no member with the Id: " + memberId);
		}
	}

	private void addProduct() {
		// Update input details.
		System.out.println("Enter the ID of the product");
		String id = scan.next();
		System.out.println("Enter the name of the product");
		String name = scan.next();
		System.out.println("Enter the price of the product");
		double price = scan.nextDouble();
		System.out.println("Enter the current inventory");
		int inventory = scan.nextInt();
		System.out.println("Enter the reorder threshold");
		int reorder = scan.nextInt();

		System.out.println(store.addProduct(id, name, price, inventory, reorder));
	}

	private void checkout() {
		boolean cartNotEmpty = true;
		String productIdBuffer;
		int productQtyBuffer;
		Hashtable<String, Integer> cartContents =
		new Hashtable<String, Integer>();
		//double runningTotal;
		
		while (cartNotEmpty) {
			System.out.println("Please enter the productId of the item:");
			productIdBuffer = scan.next();
			System.out.println("Enter the quantity of "
			+ productIdBuffer + ":");
			productQtyBuffer = scan.nextInt();
			cartContents.put(productIdBuffer,productQtyBuffer);
			System.out.println("Do you have another item to checkout "
			+ "( true / false )?");
			cartNotEmpty = scan.nextBoolean();
		}
		System.out.println(store.checkout(cartContents));
	}

	private void processShipment() {
		System.out.println("Enter the ID of the order");
		String orderId = scan.next();
		System.out.println("Do you have more products to enter?");
		String moreShipments = scan.next();
		while (moreShipments.equals("yes")) {
			System.out.println("Enter the ID of the order");
			orderId = scan.next();
			System.out.println(store.processShipment(orderId));
		}
	}

	private void changePrice() {
		// Update input details.
		int productId = 123456;
		int priceDollars = 10;
		int priceCents = 0;
		store.changePrice(productId, priceDollars, priceCents);
	}

	private void retrieveProductInfo() {
		// Update input details.
		int productId = 123456;
		System.out.println(store.retrieveProductInfo(productId));
	}

	/**
	 * Displays all members starting with a given String
	 * 
	 * @param memberString the beginning of a member's name
	 * @return display of all members starting with the string
	 */
	public void retrieveMemberInfo() {
		System.out.println("Enter the  beginning of a member's name");
		String memberString = scan.next();
		System.out.println(store.retrieveMemberInfo(memberString));
	}

	private void printTransactions() {
		// Update input details.
		int memberId = 123456;
		store.printTransactions(memberId);
	}

	private void listOutstandingOrders() {
		System.out.println(store.listOutstandingOrders());
	}

	/**
	 * Displays all members
	 */
	public void listAllMembers() {
		store.listAllMembers();
	}

	private void listAllProducts() {
		System.out.println(store.listAllProducts());
	}

	/**
	 * Method to be called for saving the Library object. Uses the appropriate
	 * Library method for saving.
	 * 
	 */
	private void save() {
		if (Store.save()) {
			System.out.println(" The library has been successfully saved in the file LibraryData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}
}
