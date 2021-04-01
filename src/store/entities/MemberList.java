package store.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * collection class for Member
 * 
 * @author Andrew Vick
 *
 */
public class MemberList implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Member> members = new LinkedList<Member>();

	/**
	 * searches for the given memberId
	 * 
	 * @param memberId
	 * @return the member if it exists, or null if there is no member with that
	 *         id
	 */

	public Member search(String memberId) {
		for (Iterator<Member> iterator = members.iterator(); iterator
				.hasNext();) {
			Member member = iterator.next();
			if (member.getID().equals(memberId)) {
				return member;
			}
		}
		return null;
	}

	/**
	 * Inserts a member into the memberList
	 * 
	 * @param member
	 */
	public void insertMember(Member member) {
		members.add(member);
	}

	/**
	 * removes the given member from the list of members
	 * 
	 * @param member
	 */
	public void removeMember(Member member) {
		members.remove(members.indexOf(member));
	}

	/**
	 * @returns an Iterator for the list of members
	 */
	public Iterator<Member> iterator() {
		return members.iterator();
	}

	/**
	 * The method below is an override of the toString method that displays a
	 * list of all the members, along with each members' respective information.
	 */
	@Override
	public String toString() {
		String memberDisplay = "All Members: \n";
		for (Iterator<Member> iterator = members.iterator(); iterator
				.hasNext();) {
			Member member = iterator.next();
			memberDisplay += member.toString() + "\n";
		}
		return memberDisplay;
	}
}