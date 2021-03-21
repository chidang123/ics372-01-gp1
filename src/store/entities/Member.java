package store.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//Change
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Member represents a member of the store
 * 
 * @author Andrew Vick
 *
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String address;
	private Calendar dateJoined;
	private String phoneNumber;
	private static final String MEMBER_STRING = "XM";
	private static int idCounter;
	private List<Transaction> transactions = new LinkedList<Transaction>();

	/**
	 * Creates a single member
	 * 
	 * @param name        The name of the member
	 * @param address     The address of the member
	 * @param phoneNumber The phone number of hte member
	 */
	public Member(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.dateJoined = new GregorianCalendar();
		this.phoneNumber = phoneNumber;
		id = MEMBER_STRING + ++idCounter;
	}

	/**
	 * Getter for the Id
	 * 
	 * @return Member's Id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter for the member's name
	 * 
	 * @return member's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for member's name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for member's address
	 * 
	 * @return member's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Setter for member's address
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Getter for the date the member joined the store
	 * 
	 * @return date the member joined the store
	 */
	public Calendar getDateJoined() {
		return dateJoined;
	}

	/**
	 * Getter for member's phone number
	 * 
	 * @return member's phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Setter for member's phone number
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Adds a transaction to the transaction collection
	 * 
	 * @param transaction
	 */
	public void addMemberTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	public String getTransactionInfo(Date startDate, Date endDate) {
		String result = name + "'s transactions\n";
		for (ListIterator<Transaction> iterator = transactions.listIterator(); iterator.hasNext();) {
			Transaction transaction = iterator.next();
			if ((transaction.getDate().equals(startDate) || transaction.getDate().after(startDate))
					&& (transaction.getDate().equals(endDate) || transaction.getDate().before(endDate))) {
				result = "Transaction ID: " + transaction.getTransactionID() + "/nDate: " + transaction.getDate();
			}
		}
		return result;
	}

	/**
	 * return an iterator for the transaction collection
	 * 
	 * @return
	 */
	public Iterator<Transaction> iterator() {
		return transactions.iterator();
	}

	/**
	 * String form of the member
	 * 
	 */
	@Override
	public String toString() {
		String string = "Id: " + id + ", member name: " + name + ", address: " + address + ", date joined: "
				+ dateJoined.get(Calendar.MONTH) + "/" + dateJoined.get(Calendar.DATE) + "/"
				+ dateJoined.get(Calendar.YEAR) + ", phone number: " + phoneNumber;

		return string;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public static void save(ObjectOutputStream output) throws IOException {
		output.writeObject(idCounter);
	}

	public static void retrieve(ObjectInputStream input) throws IOException, ClassNotFoundException {
		idCounter = (int) input.readObject();
	}
}
