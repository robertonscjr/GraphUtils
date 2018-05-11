package main;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import main.contracts.IGraph;
import main.structure.components.*;
import main.structure.components.enums.RepresentationType;


public class Graph <T> implements IGraph<T> {
	

	private Set<Vertex<T>> vertex;
	
	public Graph(String pathFile) throws Exception {
		this.readGraph(pathFile);
	}
	
	public Graph() {
		this.vertex = new HashSet<>();
	}

	
	// ============================== READ GRAPH ==============================
	@Override
	public void readGraph(String pathFile) throws Exception {
		File file = new File(pathFile);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		// first line is qtt of the edges
		//this.edges_number = Integer.parseInt(sc.nextLine());
		//
		sc.nextLine();
		
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			if (line.length > 2) throw new Exception("Invalide Format File!");
			
			Long srcIndex = Long.parseLong(line[0]);
			Long destinationIndex = Long.parseLong(line[1]);
			addEdge(srcIndex, destinationIndex);
			addEdge(destinationIndex, srcIndex);
		}
	}
	
	@Override
	public void readWeightedGraph(String pathFile) throws Exception {
		File file = new File(pathFile);
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file);
		
		// first line is qtt of the edges
		//this.edges_number = Integer.parseInt(sc.nextLine());
		//
		sc.nextLine();
		
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			if (line.length > 3) throw new Exception("Invalid Format File!");
			
			Long srcIndex = Long.parseLong(line[0]);
			Long destinationIndex = Long.parseLong(line[1]);
			Float weight = Float.parseFloat(line[2]);
			
			addWeightedEdge(weight, srcIndex, destinationIndex);
			addWeightedEdge(weight, destinationIndex, srcIndex);
		}
			
	}
	

	public void addWeightedEdge(Float weight, Long source, Long destination) {
		Vertex<T> src = getVertex(source);
		Vertex<T> dest = getVertex(destination);
		
		if (src == null) src = addVertex(source);
		if (dest == null) dest = addVertex(destination);
		
		Edge<T> e = new Edge<T>(weight, src, dest);
		src.addEdge(e);
	}

	public void addEdge(Long source, Long destination) {
		Vertex<T> src = getVertex(source);
		Vertex<T> dest = getVertex(destination);
		
		if (src == null) src = addVertex(source);
		if (dest == null) dest = addVertex(destination);
		
		Edge<T> e = new Edge<T>(src, dest);
		src.addEdge(e);
		
	}

	public Vertex<T> addVertex(Long index) {
		Vertex<T> v = new Vertex<T>(index);
		vertex.add(v);
		return v;
	}
	
	public Vertex<T> getVertex(Long index) {
		for (Vertex<T> v : vertex)
			if (v.getIndex().equals(index))
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
	public String BFS(Vertex<T> vertex) {
		// TODO @Andre
		return null;
	}

	@Override
	public String DFS(Vertex<T> vertex) {
		// TODO @Nicacio
		return null;
	}

	@Override
	public String SCC() {
		// TODO @Lucas
		return null;
	}

	@Override
	public String shortestPath(Vertex<T> v1, Vertex<T> v2) {
		// TODO @Ionesio
		return null;
	}

	@Override
	public String MST() {
		// TODO @Andre
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
	
	@SuppressWarnings("unused")
	private T maxVertex() {
		// TODO: Search the maximum vertex of graph
		//       Obs: Try it to use compareTo
		
		return null;
	}
	
	@Override
	public String toString() {
		String resp = "";
		for (Vertex<T> v : vertex) {
			resp += v.getIndex() + " - " + v.getAdjacencyList();
			resp += "\n";
		}
		return resp;
	}
}
