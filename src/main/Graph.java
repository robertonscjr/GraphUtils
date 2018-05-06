package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import main.contracts.IGraph;
import main.structure.components.*;


public class Graph <T extends Comparable<T>> implements Comparator<Graph <T>>, IGraph {
	public enum RepresentationType {
	    ADJACENCYMATRIX("AM"),ADJACENCYLIST("AL");

	    public String rt;
	    RepresentationType(String rt) {
	        this.rt = rt;
	    }
	}

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
			addEdge(destination, source);
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
			if (line.length > 3) throw new Exception("Invalid Format File!");
			
			T src = (T) line[0];
			T destination = (T) line[1];
			Float weight = Float.parseFloat(line[2]);
			
			addWeightedEdge(weight, src, destination);
			addWeightedEdge(weight, destination, src);
		}
			
	}
	

	public void addWeightedEdge(Float weight, T source, T destination) {
		Vertex<T> src = getVertex(source);
		Vertex<T> dest = getVertex(destination);
		
		if (src == null) src = addVertex(source);
		if (dest == null) dest = addVertex(destination);
		
		Edge<T> e = new Edge<T>(weight, src, dest);
		src.addEdge(e);
	}

	public void addEdge(T source, T destination) {
		Vertex<T> src = getVertex(source);
		Vertex<T> dest = getVertex(destination);
		
		if (src == null) src = addVertex(source);
		if (dest == null) dest = addVertex(destination);
		
		Edge<T> e = new Edge<T>(src, dest);
		src.addEdge(e);
		
	}

	public Vertex<T> addVertex(T value) {
		Vertex<T> v = new Vertex<T>(value);
		vertex.add(v);
		return v;
	}
	
	public Vertex<T> getVertex(T value) {
		for (Vertex<T> v : vertex)
			if (v.getValue().equals(value))
				return v;
		return null;
	}
	
	// ============================== READ GRAPH ==============================

	@Override
	public int getVertexNumber() {
		return vertex.size();
	}

	@Override
	public int getEdgeNumber() {
		int edgeNumber = 0;
		
		for (Vertex<T> v : vertex) {
			edgeNumber += v.getAdjacencyList().size();
		}
		
		return edgeNumber;
	}

	@Override
	public float getMeanEdge() {
		return 2 * getEdgeNumber() / getVertexNumber();
	}

	@Override
	public String BFS(Vertex<?> vertex) {
		
		Set<Vertex<T>> nonvisited = this.vertex;
		
		nonvisited.remove(vertex);
		
		String message = (String) vertex.getValue();
		
		List<Vertex<?>> queued = new ArrayList<Vertex<?>>();
		
		for (Edge<?> edge : vertex.getAdjacencyList()) {
			if (edge.getSource().equals(vertex)) {
				queued.add(edge.getDestination());
				nonvisited.remove(edge.getDestination());
			}
		}
		
		for (int i = 0; i < this.vertex.size() - 2; i++) {
			for (Edge<?> edge : vertex.getAdjacencyList()) {
				if (edge.getSource().equals(queued.get(i))) {
					if (nonvisited.contains(edge.getSource())) {
						queued.add(edge.getDestination());
						nonvisited.remove(edge.getDestination());
					}
				}
			}
			message = message + " " + queued.get(i).getValue();
		}
		
	return message;
	}

	@Override
	public String DFS(Vertex<?> vertex) {
		// TODO @Nicacio
		return null;
	}

	@Override
	public String SCC() {
		// TODO @Lucas
		return null;
	}

	@Override
	public String shortestPath(Vertex<?> v1, Vertex<?> v2) {
		// TODO @Ionesio
		return null;
	}

	@Override
	public String MST() {
		// TODO @Andre
		
		// Edge<?>[] edges = new Edge<?>[this.getEdgeNumber()];
		
		// precisa de acesso a lista de adjacencia.
		
		return null;
	}
	
	@Override
	public String graphRepresentation(RepresentationType rt) {
		String resp = "";
		
		if (rt.equals(RepresentationType.ADJACENCYLIST)) {
			resp = representationAdjList();
		}
		else if (rt.equals(RepresentationType.ADJACENCYMATRIX)) {
			resp = representationAdjMatrix();
		}
		else {
			// TODO: RepresentationNotAvaliableException
		}
		
		return resp;
	}
	
	private String representationAdjList() {
		String resp = "";
		
		// TODO: Sort vertexes		
		for (Vertex<T> v : vertex) {
			resp += v.getValue() +" -" + v.getAdjacencyListRepresentation() + "\n";
		}
		
		return resp;
	}
	
	private String representationAdjMatrix() {
		// TODO: Implementation of adjacency matrix
		//       Obs: The type of this class should be Integer
				
		return "";
	}
	
	private T maxVertex() {
		// TODO: Search the maximum vertex of graph
		//       Obs: Try it to use compareTo
		
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
