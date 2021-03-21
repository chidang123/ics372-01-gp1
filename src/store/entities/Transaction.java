package store.entities;

//Change//Change
import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date date;
	private int memberID;
	private int transactionID;
	private static int idCounter;
	private ProductList productList = new ProductList();;

	public Transaction(int memberID) {
		this.date = new Date();
		this.memberID = memberID;
		this.transactionID = idCounter;
		idCounter++;
	}

	public Date getDate() {
		return date;
	}

	public int getMemberID() {
		return memberID;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void addProduct(Product product) {
		productList.addNewProduct(product);
	}
}