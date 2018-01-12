package misc;

import javafx.util.Pair;

/**
 * Splits an even sized singly linked list and retruns the 2 halfs in reverse
 * For example:
 * If the input list is 1->2->3->4->5->6 the output is 2 lists 3->2->1 and 6->5->4
 * 
 */
public class LinkedListSplitAndReverse {
	static class Node{
		final int value;
		Node next;
		Node(int v, Node n){
			value = v;
			next = n;
		}
		
		@Override
		public String toString(){
			Node cur = this;
			Node next = cur.next;
			StringBuilder sb = new StringBuilder();
			sb.append(cur.value);
			while(next != null){
				sb.append("->");
				sb.append(next.value);
				next = next.next;
			}
			return sb.toString();
		}
	}
	
	//assumes that the head points to a singly linked list of even length
	public static Pair<Node, Node> splitAndReverse(Node head){
		Node f = head; //fast
		Node s = head; //slow
		int pos = 0;
		
		Node next = null;
		
		do{
			if(pos == 0){
				next = s.next;
			}else if(pos % 2 == 0){
				Node prev = s;
				s = next;
				next = next.next;
				s.next = prev;
			}
			
			pos++;
			f = f.next;
		}while(f != null);
		
		head.next = null;
		
		System.out.println(s != null ? s.value : null);
		
		Node firstHalfInReverese = s;
		
		
		//process the 2nd half
		s = next;    
		next = s.next;
		s.next = null;//breaking link between 1st and 2nd halves
		
		while(next != null){
			Node prev = s;
			s = next;
			next = next.next;
			s.next = prev;
		}
		
		System.out.println(s != null ? s.value : null);
		
		Node secondHalfInReverse = s;
		
		return new Pair<>(firstHalfInReverese, secondHalfInReverse);
	}
	
	
	
	public static void main(String[] args){
		
		Pair<Node, Node> result = 
				splitAndReverse(new Node(1, new Node(2, new Node (3, new Node(4, new Node(5, new Node(6, null)))))));
		
		System.out.println("Result:");
		System.out.println(result.getKey());
		System.out.println(result.getValue());
	}

}
