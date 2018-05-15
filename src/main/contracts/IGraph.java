package main.contracts;

import main.structure.components.Vertex;
import main.structure.components.enums.RepresentationType;

/**
 * Graph interface
 * 
 * @author nicacio
 * @author ionesio
 * @author roberto
 *
 * @param <T>
 */
public interface IGraph<T> {
	/**
	 * It reads from a file the edges of a graph</br>
	 * and automatically creates the vertices based on the edges</br>
	 * File format:</br>
	 * 		<\size value></br>
	 * 		<\source value> <\destination value></br>
	 * 		<...> <...></br>
	 * </br>
	 * Example:</br>
	 * 		2</br>
	 * 		1 2</br>
	 * 		2 1</br>
	 * 
	 * @param pathFile
	 * @throws FilePathException or GraphFormatFileException
	 */
	void readGraph(String pathFile) throws Exception;
	
	/**
	 * It reads from a file the edges of a weighted graph</br>
	 * and automatically creates the vertices based on the edges</br>
	 * File format:</br>
	 * 		<\size value></br>
	 * 		<\source value> <\destination value> <\weight value></br>
	 * 		<...> <...> <...></br>
	 * 
	 * Example:</br>
	 * 		2</br>
	 * 		1 2 1.5</br>
	 * 		2 1 2.5</br>
	 * </br>
	 * @param pathFile
	 * @throws FilePathException or GraphFormatFileException
	 */
	void readWeightedGraph(String pathFile) throws Exception;
	
	/**
	 * 
	 * @return number of vertices
	 */
	int getVertexNumber();
	
	/**
	 * 
	 * @return number of edges
	 */
	int getEdgeNumber();
	
	/**
	 * 
	 * @return
	 */
	float getMeanEdge();
	
	/**
	 * Breadth-first search</br>
	 * <a href="https://en.wikipedia.org/wiki/Breadth-first_search">BFS-Wiki</a>
	 * @param vertex
	 * @return string of path
	 */
	String BFS(Vertex<T> vertex);
	
	/**
	 * Depth-First Search</br>
	 * <a href="https://en.wikipedia.org/wiki/Depth-first_search">DFS-Wiki</a>
	 * @param vertex
	 * @return string of path
	 */
	String DFS(Vertex<T> vertex);
	/**
	 * Strongly connected component</br>
	 * <a href="https://en.wikipedia.org/wiki/Strongly_connected_component">SCC-Wiki</a>
	 * @return
	 */
	Boolean SCC();
	
	/**
	 * <a href="https://en.wikipedia.org/wiki/Shortest_path_problem">Shortest Path Problem</a>
	 * using 
	 * <a href="https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm">Dijkstra</a>
	 * @param vertex1
	 * @param vertex2
	 * @return path
	 */
	String shortestPath(Vertex<T> v1, Vertex<T> v2);
	
	/**
	 * Minimum spanning tree <a href="https://en.wikipedia.org/wiki/Minimum_spanning_tree">MST-Wiki</a>
	 * @return
	 */
	String MST();
	
	/**
	 * Graph Representation for:</br>
	 * 		Adjacency matrix ("AM") and adjacency list ("AL")
	 * 
	 * @param representation enum
	 * @return
	 * @throws InvalidGraphRepresentationException
	 */
	String graphRepresentation(RepresentationType rt) throws Exception;
}