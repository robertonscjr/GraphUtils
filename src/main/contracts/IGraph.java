package main.contracts;

import main.Graph.RepresentationType;
import main.structure.components.Vertex;

public interface IGraph {
	void readGraph(String pathFile) throws Exception;
	void readWeightedGraph(String pathFile) throws Exception;
	int getVertexNumber();
	int getEdgeNumber();
	float getMeanEdge();
	String BFS(Vertex<?> vertex);
	String DFS(Vertex<?> vertex);
	String SCC();
	String shortestPath(Vertex<?> v1, Vertex<?> v2);
	String MST();
	String graphRepresentation(RepresentationType rt);
}