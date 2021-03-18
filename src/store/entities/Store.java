package store.entities;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

public class Store {
	private static Store store;
	ProductList pList = new ProductList();
	OrderList orderList = new OrderList();
	
    /**
     * Private for the singleton pattern Creates the catalog and member collection
     * objects
     */
	

	
    private Store() {
    }

    /**
     * Supports the singleton pattern
     * 
     * @return the singleton object
     */
    public static Store instance() {
        if (store == null) {
            return store = new Store();
        } else {
            return store;
        }
    }
    
    public int enrollMember(String name, String address, String phoneNumber,
    Date joined, boolean Paid ) {
    	int memberId = 123456;
        return memberId;
    }
    
    public void removeMember(int memberId) { 
    	System.out.println("Remove Member: " + memberId);
    }
    
    public Product addProduct(String id, String name, double price, int inventory, int reorderThreshold) {
    	Product product = new Product(id, name, price, inventory, reorderThreshold);
    	pList.addNewProduct(product);    	
    	return product;
    }
    
    public String checkout(Hashtable<String,Integer> cart) {
    	return "Checkout Output.";
    }
    
    public String processShipment(Hashtable<String,Integer> shipment) {
    	return "Process Output.";
    }
    
    public String changePrice(int productId, int priceDollars, int priceCents) {
    	return "Successfully changed price on " + productId + " to $"
    	+ priceDollars + "." + priceCents + ".";
    }
    
    public String retrieveProductInfo(int productId) {
    	return "Product details for " + productId;
    }
    
    public String retrieveMemberInfo(String memberString) {
    	return "Member details for " + memberString;
    }
    
    public String printTransactions(int memberId) {
    	return "Transactions for " + memberId;
    }
    
    public String listOutstandingOrders() {
    	StringBuilder buffer = new StringBuilder();
    	buffer.append("id,product,date,qty\n");
        for (Iterator<Order> iterator = pList.iterator(); iterator.hasNext();) {
            Order order = (Order) iterator.next();
            	buffer.append(order.getId());
            	buffer.append(",");
            	buffer.append(order.getProduct().getName());
            	buffer.append(",");
            	buffer.append(order.getDate().toString());
            	buffer.append(",");
            	buffer.append(Integer.toString(order.getNewStock()));
            	buffer.append("\n");
        }
    	return buffer.toString();
    }
    
    public String listAllMembers() {
    	return "Here are all the members.";
    }
    
    public String listAllProducts() {
    	StringBuilder buffer = new StringBuilder();
    	buffer.append("name,id,onhand,price,reorder\n");
        for (Iterator<Order> iterator = pList.iterator(); iterator.hasNext();) {
            Order product = (Order) iterator.next();
            	buffer.append(product.getName());
            	buffer.append(",");
            	buffer.append(product.getId());
            	buffer.append(",");
            	buffer.append(Integer.toString(product.getInventory()));
            	buffer.append(",");
            	buffer.append(Double.toString(product.getPrice()));
            	buffer.append(",");
            	buffer.append(Integer.toString(product.getReorderThreshold()));
            	buffer.append("\n");
        }
    	return buffer.toString();
    }
    
    public void save() {
    	System.out.println("Save.");
    }
}
