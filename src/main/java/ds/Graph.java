package ds;

import java.util.List;
import java.util.Properties;

public class Graph {

	public static class Node implements Comparable<Node>
	{
		final int nodeId;
		Properties nodeProperties;
		Node(int nodeId, Properties properties)
		{
			this.nodeId = nodeId;
			this.nodeProperties = properties;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.nodeId, o.nodeId);
		}
	}
	
	public static interface IEdge{}
	
	/**
	 * Represents an edge in an undirected graph.
	 */
	public static class Edge implements IEdge
	{

		// There is no concept of source and destination in a 
		// undirected edge, so just using a generic naming 
		// structure here.
		final Node node1, node2;
		
		Edge(Node node1, Node node2)
		{
			if(node1 == null || node2 == null) 
			{
				throw new IllegalArgumentException();
			}
			
			if(node1.compareTo(node2) > 0) 
			{
				this.node1 = node1;
				this.node2 = node2;
			}
			else 
			{
				// Swapping to make sure that the nodes are in specific order 
				// which allows us to do equals check in a predictable manner.
				this.node1 = node2;
				this.node2 = node1;
			}			
		}
		
		@Override
		public boolean equals(Object o) 
		{
			if (this == o) {
		      return true;
		    }
			
		    if (o == null || getClass() != o.getClass()) {
		      return false;
		    }
		    
		    Edge oEdge = (Edge)o;
		    if(this.node1.equals(oEdge.node1) && this.node2.equals(oEdge.node2)) 
		    {
		    	return true;
		    }
		    else 
		    {
		    	return false;
		    }			
		}		
	}
	
	public static class DirectedEdge implements IEdge
	{
		final Node src, dst;
		public DirectedEdge(Node src, Node dst) {
			this.src = src;
			this.dst = dst;
		}
		
		@Override
		public boolean equals(Object o) 
		{
			if (this == o) {
		      return true;
		    }
			
		    if (o == null || getClass() != o.getClass()) {
		      return false;
		    }
		    
		    DirectedEdge oEdge = (DirectedEdge)o;
		    if(this.src.equals(oEdge.src) && this.dst.equals(oEdge.dst)) 
		    {
		    	return true;
		    }
		    else 
		    {
		    	return false;
		    }			
		}
	}
	
	private List<IEdge> edges;
	Graph(List<IEdge> edges)
	{
		this.edges = edges;
	}
}
