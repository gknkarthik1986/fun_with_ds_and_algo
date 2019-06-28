package ds;

import java.util.Iterator;

/**
 * Singly linked list implementation that capable of handling only integers as values.
 */
public class LinkedListImpl implements Iterable<Integer>
{
	static class Node
	{
		Node next;
		int val;
		Node(int val){
			this.val = val;
		}
	}
	
	int size;
	Node head, tail;
		
	public void add(int item) 
	{
		if(head == null) 
		{
			head = tail = new Node(item);
		}
		else 
		{
			tail.next = new Node(item);
		}
		size++;
	}
	
	public int get(int index) throws Exception
	{
		validateIndex(index);
		return getInternal(index).val;
	}
	
	public void remove(int index) throws Exception 
	{
		validateIndex(index);
		
		Node prevNode = null;
		if(index > 0)
		{
			prevNode = getInternal(index - 1);
		}
		
		Node curNode = prevNode != null ? prevNode.next : head;
		if(curNode.next != null) 
		{
			if(prevNode != null) 
			{
				prevNode.next = curNode.next;
			}
			else 
			{
				// current node is the head
				head = curNode.next;
			}
		}
		else 
		{
			// the node to remove is the tail node
			if(prevNode != null) 
			{
				prevNode.next = null;
				tail = prevNode;
			}
			else 
			{
				// removing the only node is the linked list
				head = null;
				tail = null;
			}
		}
		size--;
	}
	
	// Expects index to be valid.
	private Node getInternal(int index) throws Exception
	{
		Node curNode = head;
		for(int i = 0; i < index; i++) 
		{
			if(curNode.next != null) 
			{
				curNode = curNode.next;
			}
		}
		return curNode;
	}
	
	private void validateIndex(int index) throws Exception
	{
		if(index >= size) 
		{
			throw new Exception("Index out of range");
		}	
	}
	
	/**
	 * Checks if the given 2 linked lists converge with one another.
	 * @param first First linked list
	 * @param second Second linked list
	 * @return true if the linked lists converge.
	 */
	public static boolean checkConvergence(LinkedListImpl first, LinkedListImpl second) 
	{
		//todo
		return true;
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
