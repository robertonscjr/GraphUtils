package main.structure.components;

import main.structure.contracts.IEdge;

/**
 * This class represents an abstraction of graph edge
 * */
public class Edge<T> implements IEdge<T>{

	private Float weight;
	private Vertex<T> source, destination;
	
	/**
	 * Constructor of Edge without weight
	 * @param Vertex source
	 * @param Vertex destination
	 * */
	public Edge(Vertex<T> source, Vertex<T> destination) {
		this.source = source;
		this.destination = destination;
	}
	
	/**
	 * Constructor of Edge with weight
	 * @param Vertex source
	 * @param Vertex destination
	 * */
	public Edge(Float weight, Vertex<T> source, Vertex<T> destination) {
		this.weight = weight;
		this.source = source;
		this.destination = destination;
	}

	/**
	 * Retrieve edge weight
	 * @return weight of edge
	 * */
	public Float getWeight() {
		return weight;
	}
	/**
	 * Change weight value of this edge
	 * @param new weight value
	 * */
	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * Get source vertex of this edge
	 * @return source vertex of this edge
	 * */
	public Vertex<T> getSource() {
		return source;
	}

	/**
	 * Change source vertex of this edge
	 * @param new source vertex
	 * */
	public void setSource(Vertex<T> source) {
		this.source = source;
	}

	/**
	 * Get destination vertex of this edge
	 * @return destination vertex of this edge
	 * */
	public Vertex<T> getDestination() {
		return destination;
	}
	
	/**
	 * Change source vertex of this edge
	 * @param new source vertex
	 * */
	public void setDestination(Vertex<T> destination) {
		this.destination = destination;
	}

	@Override
	public String toString() {
		String resp = source + "-->" + destination;
		if (this.weight != null) resp += "(" + this.weight + ")";
		return resp;
	}
}
