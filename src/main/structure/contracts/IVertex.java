package main.structure.contracts;

import main.structure.components.Edge;

/**
 * Ivertex
 *
 * @param <T>
 */
public interface IVertex<T> {
	
	/** Add some edge in this vertex
	 * 
	 * @param edge
	 */
	void addEdge(Edge<T> edge);
	
	/**
	 * Remove some edge from this vertex
	 * 
	 * @param edge
	 */
	void removeEdge(Edge<T> edge);
	
	/**
	 * Compares the distance of another vertex with the current one.
	 * @param distanceValue
	 * @return
	 */
	Boolean relaxDistance(double distanceValue);
}
