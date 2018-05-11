package main.contracts;

import main.structure.components.Vertex;
import main.structure.components.enums.RepresentationType;

public interface IGraph<T> {
	void readGraph(String pathFile) throws Exception;
	void readWeightedGraph(String pathFile) throws Exception;
	int getVertexNumber();
	int getEdgeNumber();
	float getMeanEdge();
	String BFS(Vertex<T> vertex);
	String DFS(Vertex<T> vertex);
	String SCC();
	String shortestPath(Vertex<T> v1, Vertex<T> v2);
	String MST();
	String graphRepresentation(RepresentationType rt);
}