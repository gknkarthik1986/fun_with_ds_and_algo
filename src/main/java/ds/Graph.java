package ds;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.LinkedList;

public class Graph {

	public static class Node implements Comparable<Node>
	{
		public final int nodeId;
		Properties nodeProperties;
		
		/**
		 * 
		 * @param nodeId - Id of this node. Nodes are identified by an integer 
		 * between 0 and N-1 where N is the number of nodes in the graph. 
		 * @param properties
		 */
		public Node(int nodeId, Properties properties)
		{
			this.nodeId = nodeId;
			this.nodeProperties = properties;
		}
		
		public int getNodeId()
		{
			return this.nodeId;
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
	
	
	private final int[][] adjacencyMatrix;
	private final Map<Integer, Node> nodes;	
	
	private Graph(int[][] adjacencyMatrix, Map<Integer, Node> nodes)
	{
		this.adjacencyMatrix = adjacencyMatrix;
		this.nodes = nodes;
	}
	
	public List<Node> adjacentNodes(final Node node)
	{
		LinkedList<Node> adjNodes = new LinkedList<>();
		for(int i = 0; i < adjacencyMatrix[node.nodeId].length; i++)
		{
			if(adjacencyMatrix[node.nodeId][i] >= 0) 
			{
				adjNodes.add(nodes.get(i));
			}
		}
		return adjNodes;
	}
	
	public static Graph constructGraphFromEdgeList(List<String> edges, List<Node> nodes) throws Exception
	{	
		Map<Integer, Node> nodesMap = nodes.stream()
				.sorted(Comparator.comparingInt(Node::getNodeId).reversed())
				.collect(Collectors.toMap(
					Node::getNodeId, 
					n -> n,
					(oldValue, newValue) -> oldValue, // maybe of this is the case we should throw an exception
					LinkedHashMap::new));
		
		//each entry is a comma separated list of 2 node Ids
		int n = nodes.size(); 
		int[][] adjacencyMatrix = new int[n][n];
		for(String edgeStr : edges) 
		{
			String[] split = edgeStr.split(",");
			int src = Integer.parseInt(split[0]);
			int dst = Integer.parseInt(split[1]);
			if(src >= n || dst >= n)
			{
				throw new Exception("Invalid node Id seen in the edge list");
			}
			adjacencyMatrix[src][dst] = 1;
		}
		
		return new Graph(adjacencyMatrix, nodesMap);		
	}
}
