package main;

import java.io.File;
import java.util.Comparator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import main.contracts.IGraph;
import main.structure.components.*;



public class Graph <T extends Comparable<T>> implements Comparator<Graph <T>>, IGraph {

	private Set<Vertex<T>> vertex;
	
	public Graph(String pathFile) throws Exception {
		this.readGraph(pathFile);
	}
	
	public Graph() {
		this.vertex = new HashSet<>();
	}
	
	@Override
	public int compare(Graph<T> o1, Graph<T> o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// ============================== READ GRAPH ==============================
	@Override
	public void readGraph(String pathFile) throws Exception {
		File file = new File(pathFile);
		
		Scanner sc = new Scanner(file);
		
		// first line is qtt of the edges
		//this.edges_number = Integer.parseInt(sc.nextLine());
		//
		sc.nextLine();
		
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			if (line.length > 2) throw new Exception("Invalide Format File!");
			
			T source  = (T) line[0];
			T destination 	= (T) line[1];
			addEdge(source, destination);
		}
	}
	
	@Override
	public void readWeightedGraph(String pathFile) throws Exception {
		File file = new File(pathFile);
		
		Scanner sc = new Scanner(file);
		
		// first line is qtt of the edges
		//this.edges_number = Integer.parseInt(sc.nextLine());
		//
		sc.nextLine();
		
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			if (line.length > 3) throw new Exception("Invalide Format File!");
			
			T src = (T) line[0];
			T destination = (T) line[1];
			Float weight = Float.parseFloat(line[2]);
			
			addWeightedEdge(weight, src, destination);
		}
			
	}
	

	private void addWeightedEdge(Float weight, T source, T destination) {
		Vertex<T> src = getVertex(source);
		Vertex<T> dest = getVertex(destination);
		
		if (src == null) src = addVertex(source);
		if (dest == null) dest = addVertex(destination);
		
		// --
		
		Edge<T> e = new Edge<T>(weight, src, dest);
		src.addEdge(e);
	}

	private void addEdge(T source, T destination) {
		Vertex<T> src = getVertex(source);
		Vertex<T> dest = getVertex(destination);
		
		if (src == null) src = addVertex(source);
		if (dest == null) dest = addVertex(destination);
		
		// --
		
		Edge<T> e = new Edge<T>(src, dest);
		src.addEdge(e);
		//from.add_v_adj(e);
		//edges.add(e);
		
	}

	private Vertex<T> addVertex(T value) {
		Vertex<T> v = new Vertex<T>(value);
		vertex.add(v);
		return v;
	}
	
	private Vertex<T> getVertex(T value) {
		for (Vertex<T> v : vertex)
			if (v.getValue().equals(value))
				return v;
		return null;
	}
	
	// ============================== READ GRAPH ==============================

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
	
	@Override
	public String toString() {
		String resp = "";
		for (Vertex<T> v : vertex) {
			resp += v.getValue() + " - " + v.getAdjacencyList();
			resp += "\n";
		}
		return resp;
	}

}
