package main.structure.components;

import main.structure.contracts.IEdge;

public class Edge <T extends Comparable<T>> implements IEdge{

	private Float weight;
	private Vertex<T> source, destination;
	
	public Edge(Vertex<T> source, Vertex<T> destination) {
		this.source = source;
		this.destination = destination;
	}
	
	public Edge(Float weight, Vertex<T> source, Vertex<T> destination) {
		this.weight = weight;
		this.source = source;
		this.destination = destination;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public Vertex<T> getSource() {
		return source;
	}

	public void setSource(Vertex<T> source) {
		this.source = source;
	}

	public Vertex<T> getDestination() {
		return destination;
	}

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
