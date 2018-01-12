package misc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * The only way to solve the problem is to find the next non-empty item and then rewind once you find it.
 *
 */
public class NestedIteratorNode {
	NestedIteratorNode(int val){
		this.value = val;
	}
	
	NestedIteratorNode(NestedIteratorNode... lst){
		this.list = Arrays.asList(lst);
		isList = true;
	}
	
	@Override
	public String toString() {
		return Integer.toString(value);
	}

	int value;
	boolean isList = false;
	List<NestedIteratorNode> list;
	
	static class NestedIterator implements Iterator<NestedIteratorNode> {
		int curPosition = -1;
		int maxLimit;
		private List<NestedIteratorNode> listToIterate;
		Stack<NestedIterator> stack = new Stack<>();
		
		public NestedIterator(List<NestedIteratorNode> list) {
			listToIterate = list;
			maxLimit = list.size();
			if(!list.isEmpty()){
				stack.push(this);
			}			
		}
		
		// find the next non-empty item and then rewind once you find it. 
		public boolean hasNext() {
			NestedIterator it = null;
			// Necessary to prevent early false termination because of 
			// stack.peek().hasMore() being false. Repeated at the end of
			// the loop once again for the same reason.
			popAllEmptyStuff();
			while(!stack.isEmpty() && stack.peek().hasMore()) {
				NestedIteratorNode node = stack.peek().getNextItem();
				if(node.isList) {
					stack.push(new NestedIterator(node.list));
				}else {
					stack.peek().rollback();
					return true;
				}
				popAllEmptyStuff();
			}
			return false;
		}		
		
		// get the next non-list item
		public NestedIteratorNode next() {
			NestedIteratorNode node;
			do {
				node = stack.peek().getNextItem();
				if(node.isList) {
					stack.push(new NestedIterator(node.list));
				}
			} while(node.isList);			
			return node;
		}
		
		private boolean hasMore() {
			return maxLimit > 0 && curPosition < maxLimit-1; 
		}
		
		private void rollback() {
			--curPosition;
		}		
		
		//unsafe to call without checking if curPosition is within bounds
		private NestedIteratorNode getNextItem() {
			return listToIterate.get(++curPosition); 
		}
		
		private void popAllEmptyStuff() throws EmptyStackException{
			NestedIterator it;
			while(!stack.isEmpty()) {
				it = stack.pop();
				if(it.hasMore()) {
					stack.push(it);
					return;
				}
			}
		}

	}//end of NestedIterator class

	public static void main(String[] args){
		NestedIteratorNode topNode = new NestedIteratorNode(
				new NestedIteratorNode(1),
				new NestedIteratorNode(new NestedIteratorNode(2), new NestedIteratorNode(3)),
				new NestedIteratorNode(),
				new NestedIteratorNode(new NestedIteratorNode(),new NestedIteratorNode(3)),
				new NestedIteratorNode(4),
				new NestedIteratorNode(new NestedIteratorNode(5), new NestedIteratorNode(new NestedIteratorNode(6), new NestedIteratorNode(7))),
				new NestedIteratorNode(8),
				new NestedIteratorNode(new NestedIteratorNode(), new NestedIteratorNode())
				);
		
		List<NestedIteratorNode> lst = new ArrayList<>();
		lst.add(topNode);
		
		NestedIterator itr = new NestedIterator(lst);
		System.out.print("{");
		while(itr.hasNext()){
			System.out.print(itr.next() + ",");
		}
		System.out.print("}");
	}
}
