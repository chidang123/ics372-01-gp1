package store.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * represents a single transaction of checkout
 */
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private String memberID;
	private int transactionID;
	private static int idCounter;
	private ProductList productList = new ProductList();;

	public Transaction(String memberID) {
		this.date = new Date();
		this.memberID = memberID;
		this.transactionID = idCounter;
		idCounter++;
	}

	public Date getDate() {
		return date;
	}

	public String getMemberID() {
		return memberID;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void addProduct(Product product) {
		productList.addNewProduct(product);
	}

	public String listProducts() {
		return productList.getBasics();
	}
}