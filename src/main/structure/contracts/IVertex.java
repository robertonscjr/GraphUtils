package main.structure.contracts;

import java.util.Comparator;

import main.structure.components.Edge;
import main.structure.components.Vertex;

/**
 * Vertex interface of this graphlib
 * @author Nicácio Oliveira
 *
 * @param <T>
 */
public interface IVertex<T extends Comparable<T>> extends Comparator<Vertex<T>>{
	
	/**
	 * Must add an object that represents an edge of the vertex.
	 * @param edge object
	 */
	void addEdge(Edge<T> edge);
	
	/**
	 * Must remove an object that represents an edge of the vertex 
	 * @param edge object
	 */
	void removeEdge(Edge<T> edge);
}
