package main.structure.components;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import main.structure.contracts.IVertex;

public class Vertex <T extends Comparable<T>> implements Comparator<Vertex <T>>, IVertex{

	private T value;
	private List<Edge<T>> adjacencyList;
	
	public Vertex() {
		this.adjacencyList = new LinkedList<Edge<T>>();
	}
	
	public Vertex(T value) {
		this.value = value;
		this.adjacencyList = new LinkedList<Edge<T>>();
	}
	
	public Vertex(T value, LinkedList<Edge<T>> adjacencyList) {
		this.value = value;
		this.adjacencyList = adjacencyList;
	}
		
	@Override
	public int compare(Vertex<T> o1, Vertex<T> o2) {
		return o1.value.compareTo(o2.value);
	}

	@Override
	public void addEdge(Edge edge) {
		if (edge != null) adjacencyList.add(edge);
	}

	@Override
	public void removeEdge(Edge edge) {
		if (edge != null) adjacencyList.remove(edge);
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

	@Override
	public String toString() {
		return this.value.toString();
	}
}
