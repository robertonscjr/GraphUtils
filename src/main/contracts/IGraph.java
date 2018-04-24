package main.contracts;

import main.structure.components.Vertex;

public interface IGraph {
	void readGraph(String pathFile);
	void readWeightedGraph(String pathFile);
	int getVertexNumber();
	int getEdgeNumber();
	float getMeanEdge();
	String BFS(Vertex<?> vertex);
	String DFS(Vertex<?> vertex);
	String SCC();
	String shortestPath(Vertex<?> v1, Vertex<?> v2);
	String MST();
}