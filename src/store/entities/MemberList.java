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
	 * checks whether a member with a given member id exists.
	 */
	public Member search(String memberId) {
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			if (member.getID().equals(memberId)) {
				return member;
			}
		}
		return null;
	}

	/**
	 * inserts a Member into members
	 */
	public void insertMember(Member member) {
		members.add(member);
	}

	public void removeMember(Member member) {
		members.remove(members.indexOf(member));
	}

	public Iterator<Member> iterator() {
		return members.iterator();
	}

	/**
	 * The method below is an override of the toString method that displays a list
	 * of all the members, along with each members' respective information.
	 */
	@Override
	public String toString() {
		String memberDisplay = "All Members: \n";
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			memberDisplay += member.toString() + "\n";
		}
		return memberDisplay;
	}
}