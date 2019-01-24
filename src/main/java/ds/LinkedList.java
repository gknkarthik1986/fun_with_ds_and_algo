package ds;

public class LinkedList {
	static class Node{
		Node prev, next;
		int val;
		Node(int val){
			this.val = val;
		}
	}
	
	int size;
	Node head, tail;
		
	public void add(int item) {
		if(head == null) {
			head = tail = new Node(item);
		}else {
			tail.next = new Node(item);
		}
		size++;
	}
	
	public int get(int index) {
		//todo
		return 0;
	}
	
	public void remove(int index) {
		
	}
	
	/**
	 * Checks if the given 2 linked lists converge with one another.
	 * @param first First linked list
	 * @param second Second linked list
	 * @return true if the linked lists converge.
	 */
	public static boolean checkConvergence(LinkedList first, LinkedList second) {
		//todo
		return true;
	}

}
