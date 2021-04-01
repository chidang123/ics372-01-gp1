package store.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

import store.entities.Store;

/**
 * This class provides the display for the end-user to read information from and
 * also provides a menu with options the user can choose to perform necessary
 * functions.
 */
public class UserInterface {
	private static UserInterface userInterface;
	private static Store store;
	private BufferedReader reader = new BufferedReader(
			new InputStreamReader(System.in));
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

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved
	 * data. Otherwise, it gets a singleton Library object.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			store = Store.instance();
		}
	}

	private Scanner scan = new Scanner(System.in);

	/**
	 * The method called in main launches the program.
	 */
	public static void main(String[] args) {
		UserInterface.instance().mainMenu();
	}

	/**
	 * @returns the instance of user interface needed to run the program
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * checks prompt for the associated characters to determine boolean result
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * grabs a token from the String prompt
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
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

	/**
	 * The following method calls the Store.retrieve() method to attempt to
	 * retrieve saved data and load it for use. If unsuccessful, a new instance
	 * of Store is created for use while the program is running.
	 */
	private void retrieve() {
		try {
			if (store == null) {
				store = Store.retrieve();
				if (store != null) {
					System.out.println(
							"The store has been successfully retrieved from the file StoreData.\n");
				} else {
					System.out
							.println("File doesnt exist; creating new library");
					store = Store.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * provides a fancy logo that displays when the program starts
	 */
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
				+ "        \\____\\___/ \\___/|_|   |_____|_| \\_\\/_/   \\_\\_| |___|  \\_/  |_____|\n"
				+ "\n\n");
	}

	/**
	 * provides a printout of the menu options
	 */
	public void help() {
		System.out.println(" At the prompt enter the number for the function "
				+ "you need to perform:\n\n" + "  " + ENROLL_MEMBER
				+ ": Enroll a member.\t\t" + REMOVE_MEMBER
				+ ": Remove a member.\n" + "  " + ADD_PRODUCT
				+ ": Add a product.\t\t" + CHECKOUT
				+ ": Check out a member's items.\n" + "  " + PROCESS_SHIPMENT
				+ ": Process Shipment.\t\t" + CHANGE_PRICE + ": Change price.\n"
				+ "  " + RETRIEVE_PRODUCT_INFO + ": Retrieve product info.\t"
				+ RETRIEVE_MEMBER_INFO + ": Retrieve member info.\n" + "  "
				+ PRINT_TRANSACTIONS + ": Print Transactions.\t"
				+ LIST_OUTSTANDING_ORDERS + ": List all outstanding orders.\n"
				+ "  " + LIST_ALL_MEMBERS + ": List all members.\t\t"
				+ LIST_ALL_PRODUCTS + ": List all products.\n" + "  " + SAVE
				+ ": Save.\t\t\t" + HELP + ": Help.\n" + "  " + EXIT
				+ ": Exit.\n");
	}

	/**
	 * provides the mechanism used to select different menu options
	 */
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
				try {
					printTransactions();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
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
				help();
				break;
			}
			System.out.println("\n\nEnter 14 for help.");
		}

		System.exit(0);
	}

	/**
	 * used to parse main menu selection
	 */
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

	/**
	 * used to parse the string prompt in the yesOrNo method
	 */
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

	/**
	 * prompts the user to enter the required arguments for use in Store's
	 * enrollMember method
	 */
	private void enrollMember() {
		System.out.println(
				"Enter yes if the new member payed the fee, or no if they did not pay the fee");
		String feePaid = scan.nextLine();
		if (feePaid.equals("yes")) {
			System.out.println("Enter the name of the new member: ");
			String name = scan.nextLine();
			System.out.println("Enter the address of the new member: ");
			String address = scan.nextLine();
			System.out.println("Enter the phone number of the new member: ");
			String phoneNumber = scan.nextLine();
			System.out.println(
					store.enrollMember(name, address, phoneNumber, true));
		} else if (feePaid.equals("no")) {
			System.out.println(
					"The member was not added because they did not pay the fee");
		} else {
			System.out.println(
					"Only enter yes or no, to try again call the fuction with 1 again.");
		}
	}

	/**
	 * The following method prompts the user for a member ID and uses it to call
	 * Store's removeMember method in order to remove a member from the system.
	 * If the member is not found, a message is displayed to inform the
	 * end-user.
	 */
	private void removeMember() {
		System.out.println("Enter the member's Id: ");
		String memberId = scan.nextLine();
		boolean removeOrNot = store.removeMember(memberId);
		if (removeOrNot) {
			System.out.println("Member was removed from the system.");
		} else {
			System.out.println("There is no member with the Id: " + memberId);
		}
	}

	/**
	 * Prompts the user for parameters to be passed into Store's addProduct
	 * method, creating a new product.
	 */
	private void addProduct() {
		System.out.println("Enter the ID of the product");
		String id = scan.nextLine();
		System.out.println("Enter the name of the product");
		String name = scan.nextLine();
		System.out.println("Enter the price of the product");
		double price = scan.nextDouble();
		System.out.println("Enter the current inventory");
		int inventory = scan.nextInt();
		System.out.println("Enter the reorder threshold");
		int reorder = scan.nextInt();
		scan.nextLine();

		System.out
				.println(store.addProduct(id, name, price, inventory, reorder));
	}

	/**
	 * method to be called to check out products, prompts the user for member ID
	 * and product ID, and uses the appropriate Store method to check out
	 * products
	 */
	private void checkout() {
		boolean cartNotEmpty = true;
		ArrayList<String> productIds = new ArrayList<String>();
		ArrayList<Integer> productQtys = new ArrayList<Integer>();
		String memberId = null;

		System.out.println("Please enter the member's ID.");
		memberId = scan.nextLine();
		while (cartNotEmpty) {
			System.out.println("Please enter the productId of the item:");
			productIds.add(scan.nextLine());
			System.out.println("Enter the quantity:");
			productQtys.add(scan.nextInt());
			System.out.println("Do you have another item to checkout "
					+ "( true / false )?");
			cartNotEmpty = scan.nextBoolean();
			scan.nextLine();

		}
		System.out.println(store.checkout(memberId, productIds, productQtys));
	}

	/**
	 * method to be called to process the shipment, prompts the user for
	 * OrderID, and uses the appropriate Store method to process shipment
	 */
	private void processShipment() {
		System.out.println("Enter the ID of the order");
		String orderId = scan.nextLine();
		System.out.println(store.processShipment(orderId));
		System.out.println("Do you have more products to enter?");
		String moreShipments = scan.nextLine();
		while (moreShipments.equals("yes")) {
			System.out.println("Enter the ID of the order");
			orderId = scan.nextLine();
			System.out.println(store.processShipment(orderId));
			System.out.println("Do you have more products to enter?");
			moreShipments = scan.nextLine();
		}
	}

	/**
	 * prompts the user to enter a product ID and to enter the new price of the
	 * product, then calls the appropriate Store method to process the price
	 * change
	 */
	private void changePrice() {
		String productId;
		double newPrice;
		System.out
				.println("Enter ID for the product you would like to change.");
		productId = scan.nextLine();
		System.out.println("Enter the new price for the item.");
		newPrice = scan.nextDouble();
		scan.nextLine();
		System.out.println(store.changePrice(productId, newPrice));
	}

	/**
	 * prompts the user for the beginning of the product's name and passes it to
	 * the appropriate Store method
	 */
	private void retrieveProductInfo() {
		System.out.println("Enter the beginning of the product's name.");
		String productString = scan.nextLine();
		System.out.println(store.retrieveProductInfo(productString));
	}

	/**
	 * This method prompts for a string input, then uses the entered string as
	 * an argument to call retrieveMemberInfo(), which it then prints the
	 * results of.
	 */
	public void retrieveMemberInfo() {
		System.out.println("Enter the  beginning of a member's name.");
		String memberString = scan.nextLine();
		System.out.println(store.retrieveMemberInfo(memberString));
	}

	/**
	 * prompts the user for the member ID and two dates for the search range,
	 * then passes them to the appropriate Store method
	 * 
	 * @throws ParseException
	 */
	public void printTransactions() throws ParseException {
		boolean done = false;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		while (!done) {
			try {
				System.out.println("Enter the member ID.");
				String memberID = scan.nextLine();
				System.out.println(
						"Please enter the earliest date in the form mm/dd/yyyy.");
				Date firstDate = (Date) dateFormat.parse(scan.nextLine());
				System.out.println(
						"Please enter the latest date in the form mm/dd/yyyy.");
				Date lastDate = (Date) dateFormat.parse(scan.nextLine());
				if (firstDate.after(lastDate)) {
					System.out.println(
							"The earliest date cannot be after the latest date chronologically. Please try again.");
					continue;
				}
				System.out.println(
						store.printTransactions(memberID, firstDate, lastDate));
				done = true;
			} catch (ParseException exception) {
				System.out.println(
						"You didn't enter the dates in the correct format. Please try again.");
				continue;

			}
		}
	}

	/**
	 * prints the results of the listOutstandingOrders method in Store
	 */
	private void listOutstandingOrders() {
		System.out.println(store.listOutstandingOrders());
	}

	/**
	 * displays all members stored in the system
	 */
	public void listAllMembers() {
		System.out.println(store.listAllMembers());
	}

	/**
	 * displays all products stored in the system
	 */
	private void listAllProducts() {
		System.out.println(store.listAllProducts());
	}

	/**
	 * Method to be called for saving the Store object. Uses the appropriate
	 * Store method for saving the program data.
	 * 
	 */
	private void save() {
		if (Store.save()) {
			System.out.println(
					"The store has been successfully saved in the file StoreData \n");
		} else {
			System.out.println("There has been an error in saving \n");
		}
	}
}
