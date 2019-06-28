package algo.graph.search;

import java.util.LinkedList;
import java.util.Deque;
import java.util.HashSet;

import ds.Graph;

/*
 * This class does BFS on a connected graph.  
 */
public class BreadthFirstSearch implements GraphSearch
{	
	@Override
	public Graph.Node Search(Graph g, int targetNodeId, Graph.Node start) 
	{
		HashSet<Graph.Node> visitedNodes = new HashSet<Graph.Node>();
		Deque<Graph.Node> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()) 
		{
			Graph.Node curNode = q.remove();
			if(curNode.nodeId == targetNodeId) 
			{
				return curNode;
			}
			
			for(Graph.Node node : g.adjacentNodes(curNode)) 
			{
				if(!visitedNodes.contains(node)) 
				{
					visitedNodes.add(node);
					q.add(node);
				}
			}
		}
		
		return null;
	}
}
