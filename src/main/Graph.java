package main;

import java.util.Comparator;

import java.util.HashSet;
import java.util.Set;

import main.contracts.IGraph;
import main.structure.components.Vertex;



public class Graph <T extends Comparable<T>> implements Comparator<Graph <T>>, IGraph {

	private Set<Vertex<T>> vertex;
	
	public Graph(String pathFile) {
		this.readGraph(pathFile);
	}
	
	public Graph() {
		this.vertex = new HashSet<Vertex<T>>();
	}
	
	@Override
	public int compare(Graph<T> o1, Graph<T> o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void readGraph(String pathFile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readWeightedGraph(String pathFile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getVertexNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEdgeNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMeanEdge() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String BFS(Vertex<?> vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String DFS(Vertex<?> vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String SCC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String shortestPath(Vertex<?> v1, Vertex<?> v2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String MST() {
		// TODO Auto-generated method stub
		return null;
	}

}
