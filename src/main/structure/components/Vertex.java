package main.structure.components;

import java.util.LinkedList;
import java.util.List;

import main.structure.contracts.IVertex;

/**
 * This class represents an abstraction of graph edge 
 * */
public class Vertex <T> implements IVertex<T>{

	private Long index;
	private T value;
	private List<Edge<T>> adjacencyList;
	private double distance;
	
	/**
	 * Constructor to build vertex without value
	 * @param index of this vertex
	 * */
	public Vertex(Long index) {
		this.index = index;
		this.adjacencyList = new LinkedList<Edge<T>>();
		this.distance = Double.MAX_VALUE;
	}
	
	/**
	 * Constructor to build vertex with value
	 * @param index of this vertex
	 * @param value
	 * */	
	public Vertex(Long index, T value) {
		this.index = index;
		this.value = value;
		this.adjacencyList = new LinkedList<Edge<T>>();
	}
	
	/**
	 * Constructor to build vertex with value and adjacencyList
	 * @param index of this server
	 * @param value of this vertex
	 * @param adjacency list of this vertex (Edges)
	 * */
	public Vertex(Long index, T value, LinkedList<Edge<T>> adjacencyList) {
		this.value = value;
		this.adjacencyList = adjacencyList;
	}

	@Override
	public void addEdge(Edge<T> edge) {
		if (edge != null) adjacencyList.add(edge);
	}

	/**
	 * Update distance of this vertex from start vertex
	 * @param expected distance value
	 * @return boolean
	 * */
	public Boolean relaxDistance(double distanceValue) {
		if( distanceValue < this.distance) {
			this.distance = distanceValue;
			return true;
		}
		return false;
	}
	
	@Override
	public void removeEdge(Edge<T> edge) {
		if (edge != null) adjacencyList.remove(edge);
	}

	/**
	 * Get index of vertex
	 * @return index of vertex
	 * */
	public Long getIndex() {
		return index;
	}

	/**
	 * Set index of vertex
	 * @param new index value
	 * */
	public void setIndex(Long index) {
		this.index = index;
	}
	
	/**
	 * Get value of vertex
	 * @return index of vertex
	 * */	
	public T getValue() {
		return value;
	}

	/**
	 * Get distance of vertex
	 * @return distance of this vertex from start vertex (SHORTEST_PATH)
	 * */
	public double getDistance() {
		return this.distance;
	}

	/**
	 * Set index of vertex
	 * @param new index value
	 * */
	public void setValue(T value) {
		this.value = value;
	}

	public List<Edge<T>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(List<Edge<T>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}
	
	public String getAdjacencyListRepresentation() {
		String resp = "";
		
		for (Edge<T> edge : adjacencyList) {
			resp += " " + edge.getDestination().index;
			
			if (edge.getWeight() != null) resp += "(" + edge.getWeight() + ")";
		}
		
		return resp;
	}

	@Override
	public String toString() {
		return this.index.toString();
	}
}
