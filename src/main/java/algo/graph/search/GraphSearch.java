package algo.graph.search;

import ds.Graph;

public interface GraphSearch {
	Graph.Node Search(Graph g, int targetNodeId, Graph.Node start);
}
