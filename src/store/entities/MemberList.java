package store.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Collection class for members
 * 
 * @author Andrew Vick
 *
 */
public class MemberList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Member> members = new LinkedList<Member>();

	/**
	 * Checks whether a member with a given member id exists.
	 * 
	 * @param memberId the id of the member
	 * @return a reference to the member if memberId is valid, otherwise it return
	 *         false
	 * 
	 */
	public Member search(String memberId) {
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			if (member.getId().equals(memberId)) {
				return member;
			}
		}
		return null;
	}

	/**
	 * Inserts a member into the member collection
	 * 
	 * @param member the member to be inserted
	 */
	public void insertMember(Member member) {
		members.add(member);
	}

	/**
	 * Removes the member from the collection
	 * 
	 * @param member the member to be removed
	 */
	public void removeMember(Member member) {
		members.remove(members.indexOf(member));
	}

	/**
	 * Returns an iterator to all books
	 * 
	 * @return iterator to the member collection
	 */
	public Iterator<Member> iterator() {
		return members.iterator();
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		String memberDisplay = "All Members: \n";
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			memberDisplay += member.toString() + '\n';
		}
		return memberDisplay;
	}
}