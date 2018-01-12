package ds;

import java.util.List;
import java.util.Set;

public class Graph<K, V> {	
	static class GraphNode<K, V> implements Comparable<K>{
		K key;
		V value; 
		List<GraphNode<K, V>> adjNodes;
		
		public int compareTo(K o) {
			//todo
			return 0;
		}
		
		public int hashCode() {
			//todo
			return 0;
		}
		public boolean equals(Object anObject) {
			//todo
			return false;
		}
		
		
	}
	
	Set<GraphNode<K, V>> nodes;
}
