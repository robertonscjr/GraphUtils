package main.structure.components;

import java.util.LinkedList;
import java.util.List;

import main.structure.contracts.IVertex;

public class Vertex <T> implements IVertex<T>{

	private Long index;
	private T value;
	private List<Edge<T>> adjacencyList;
	
	public Vertex(Long index) {
		this.index = index;
		this.adjacencyList = new LinkedList<Edge<T>>();
	}
	
	public Vertex(Long index, T value) {
		this.index = index;
		this.value = value;
		this.adjacencyList = new LinkedList<Edge<T>>();
	}
	
	public Vertex(Long index, T value, LinkedList<Edge<T>> adjacencyList) {
		this.value = value;
		this.adjacencyList = adjacencyList;
	}

	@Override
	public void addEdge(Edge<T> edge) {
		if (edge != null) adjacencyList.add(edge);
	}

	@Override
	public void removeEdge(Edge<T> edge) {
		if (edge != null) adjacencyList.remove(edge);
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}
	
	public T getValue() {
		return value;
	}

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
