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
 * Member represents a member of Red River Co-Op.
 * 
 * @author Andrew Vick
 */
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String address;
	private Calendar dateJoined;
	private String phoneNumber;
	boolean feePaid;
	private static final String MEMBER_STRING = "XM";
	private static int idCounter;
	private List<Transaction> transactions = new LinkedList<Transaction>();

	/**
	 * creates a single member
	 * 
	 * @param name    name of the member
	 * @param address address of the member
	 * @param phone   phone number of the member
	 */
	public Member(String name, String address, String phoneNumber, boolean feePaid) {
		this.name = name;
		this.address = address;
		this.dateJoined = new GregorianCalendar();
		this.phoneNumber = phoneNumber;
		this.feePaid = feePaid;
		id = MEMBER_STRING + ++idCounter;
	}

	public boolean getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(boolean feePaid) {
		this.feePaid = feePaid;
	}

	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Calendar getDateJoined() {
		return dateJoined;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * add an instance of Transaction to transactions
	 */
	public void addMemberTransaction(Transaction transaction) {
		transactions.add(transaction);
	}

	/**
	 * returns the transaction Id and date of every transaction between two dates
	 * 
	 * @param startDate
	 * @param endDate
	 * @return a string of all transaction Ids and date between two dates
	 */

	public String getTransactionInfo(Date startDate, Date endDate) {
		String result = name + "'s transactions\n";
		for (ListIterator<Transaction> iterator = transactions.listIterator(); iterator.hasNext();) {
			Transaction transaction = iterator.next();
			if ((transaction.getDate().equals(startDate) || transaction.getDate().after(startDate))
					&& (transaction.getDate().equals(endDate) || transaction.getDate().before(endDate))) {
				result += "Transaction ID: " + transaction.getTransactionID() + "\nDate: " + transaction.getDate()
						+ "\n";
			}
		}
		return result;
	}

	/**
	 * returns an iterator of the list of transactions
	 * 
	 * @return iterator to list of transactions iterator
	 */
	public Iterator<Transaction> iterator() {
		return transactions.iterator();
	}

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
