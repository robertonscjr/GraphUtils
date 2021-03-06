package main;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import exception.GraphFormatFileException;
import exception.InvalidGraphRepresentationException;
import exception.WeightedGraphFormatFileException;
import main.contracts.IGraph;
import main.structure.components.*;
import main.structure.components.enums.RepresentationType;

/**
 * Graph representation class</br>
 * This class represents an simple graph or a weighted graph
 * 
 * @param <T>
 */
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

		if (!checkGraphFormatFile(sc, false))
			throw new GraphFormatFileException();
		
		sc = new Scanner(file);
		// first line is qtt of the edges
		sc.nextLine();
		
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
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
		
		if (!checkGraphFormatFile(sc, true))
			throw new WeightedGraphFormatFileException();
		
		sc = new Scanner(file);
		// first line is qtt of the edges
		sc.nextLine();
		
		while (sc.hasNextLine()) {
			String[] line = sc.nextLine().split(" ");
			
			Long srcIndex = Long.parseLong(line[0]);
			Long destinationIndex = Long.parseLong(line[1]);
			Float weight = Float.parseFloat(line[2]);
			
			addWeightedEdge(weight, srcIndex, destinationIndex);
			addWeightedEdge(weight, destinationIndex, srcIndex);
		}
	}
	
	private boolean checkGraphFormatFile(Scanner sc , boolean weighted) {
		sc.nextLine();
		while (sc.hasNext()) {
			if (!weighted && sc.nextLine().split(" ").length != 2)
				return false;
			else if (weighted && sc.nextLine().split(" ").length != 3)
				return false;
		}
		return true;
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
		
		return edgeNumber / 2;
	}

	@Override
	public float getMeanEdge() {
		return 2 * getEdgeNumber() / getVertexNumber();
	}

	@Override
	public String BFS(Vertex<T> vertex) {
		// TODO @Andre
		return bfs(vertex);
	}
	
	private String bfs(Vertex<T> vertex) {
		String resp = "";
		Queue<Edge<T>> queue = new LinkedList<>();
		int level = 0;
		ArrayList<Vertex<T>> visited = new ArrayList<>();
		
		
		queue.add(new Edge<>(vertex, vertex));
		visited.add(vertex);
		
		while (!queue.isEmpty()) {
			Edge<T> v = queue.poll();
			
			if (v.getDestination().equals(v.getSource()))
				resp += v.getDestination() + " - - " + level + "\n";
			else
				resp += v.getDestination() + " - " + level + " " + v.getSource() + "\n";
			
			if (queue.isEmpty()) level++;
			
			for (Edge<T> e : v.getDestination().getAdjacencyList()) {
				if (e.getDestination() != null && !visited.contains(e.getDestination())) {
					queue.add(e);
					visited.add(e.getDestination());
				}
			}
			
		}
		return resp;
	}

	@Override
	public String DFS(Vertex<T> vertex) {
		
		if (vertex != null)
			return dfs(vertex);
		
		return "";
	}
	
	private String dfs(Vertex<T> vertex) {
		String resp = "";
		Stack<Edge<T>> stack = new Stack<>();
	
		ArrayList<Vertex<T>> visited = new ArrayList<>();
		
		
		stack.add(new Edge<>(vertex, vertex));
		visited.add(vertex);
		
		while (!stack.isEmpty()) {
			Edge<T> v = stack.pop();
			
			if (v.getDestination().equals(v.getSource()))
				resp += v.getDestination() + " - - " + stack.size() + "\n";
			else
				resp += v.getDestination() + " - " + v.getSource() + " " + stack.size() + "\n";
			
			for (Edge<T> e : v.getDestination().getAdjacencyList()) {
				if (e.getDestination() != null && !visited.contains(e.getDestination())) {
					stack.add(e);
					visited.add(e.getDestination());
				}
			}
			
		}
		return resp;
	}

	
	
	@Override
	public Boolean SCC() {
		// Simple DFS 
		String resp = "";
		Stack<Edge<T>> stack = new Stack<>();
	
		ArrayList<Vertex<T>> visited = new ArrayList<>();
		
		Vertex<T> start_vertex = null;
		for (Vertex tmp : this.vertex) {
			start_vertex = tmp;
		}
		
		stack.add(new Edge<T>(start_vertex, start_vertex));
		visited.add((Vertex<T>) start_vertex);
		
		while (!stack.isEmpty()) {
			Edge<T> v = stack.pop();
			
			if (v.getDestination().equals(v.getSource()))
				resp += v.getDestination() + " - - " + stack.size() + "\n";
			else
				resp += v.getDestination() + " - " + v.getSource() + " " + stack.size() + "\n";
			
			for (Edge<T> e : v.getDestination().getAdjacencyList()) {
				if (e.getDestination() != null && !visited.contains(e.getDestination())) {
					stack.add(e);
					visited.add(e.getDestination());
				}
			}
			
		}
		//Compare if number of visited vertex is equals to total vertex
		return this.vertex.size() == visited.size();
	}

	
	@Override
	public String shortestPath(Vertex<T> v1, Vertex<T> v2) {
		// TODO @Ionesio
		String response = "";
		//Validate vertex
		if( this.vertex.contains(v1) && this.vertex.contains(v2)) {
			
			Queue<Vertex<T>> vertexQueue = new ArrayDeque<>();
			ArrayList<Vertex<T>> visited = new ArrayList<>();
			HashMap<String,String> reversePath = new HashMap<>();
			v1.relaxDistance(0);
			vertexQueue.add(v1);
			//While unprocessed vertex exists ...
			while(!vertexQueue.isEmpty()) {
				Vertex<T> currentVertex = vertexQueue.remove();
				visited.add(currentVertex);
				
				for(Edge<T> e : currentVertex.getAdjacencyList()) {
					
					if(!visited.contains(e.getDestination())) {
						//Update distance for adj vertex
						Vertex<T> nextVertex = e.getDestination();
						double expectedDistance = currentVertex.getDistance();
						expectedDistance += e.getWeight();
						Boolean update = nextVertex.relaxDistance( expectedDistance );
						//If upgraded, save predecessor
						if(update) {
							reversePath.put(nextVertex.getIndex().toString(), currentVertex.getIndex().toString());
						}
						vertexQueue.add(nextVertex);
					}
				}
			}
			
			String predecessor = v2.getIndex().toString();
			response += predecessor + " ";
			while(reversePath.get(predecessor) != null) {
				response = reversePath.get(predecessor)  + " " + response;
				predecessor = reversePath.get(predecessor);
			}
		}else {
			return "";
		}
		
		return response;
	}

	@Override
	public String MST() {
		// TODO @Andre
		return null;
	}
	
	@Override
	public String graphRepresentation(RepresentationType rt) throws Exception {
		String resp = "";
		
		if (rt.equals(RepresentationType.ADJACENCYLIST)) {
			resp = representationAdjList();
		}
		else if (rt.equals(RepresentationType.ADJACENCYMATRIX)) {
			resp = representationAdjMatrix();
		}
		else {
			throw new InvalidGraphRepresentationException();
		}
		
		return resp;
	}
	
	private String representationAdjList() {
		String resp = "";
		
		// TODO: Sort vertexes		
		for (Vertex<T> v : vertex) {
			resp += v.getIndex() +" -" + v.getAdjacencyListRepresentation() + "\n";
		}
		
		return resp;
	}
	
	private String representationAdjMatrix() {
		// TODO: Implementation of adjacency matrix
		//       Obs: The type of this class should be Integer
		String resp = "";
		
		int max = maxVertex() + 1;
		float[][] adjMatrix = new float[max][max];
		
		// To each vertex (represented by line), mark the matrix with adjacency list (represented by column)
		for (Vertex<T> v : vertex) {
			int line = v.getIndex().intValue();
			
			for (Edge<T> e : v.getAdjacencyList()) {
				int column = v.getIndex().intValue();
				
				adjMatrix[line][column] = (e.getWeight() == null) ? (1) : e.getWeight();
			}
		}
		
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				resp += ((j == 0) ? "" : " ") + adjMatrix[i][j];
			}
			resp += "\n";
		}
		
		return resp;
	}
	

	private int maxVertex() {
		// TODO: Search the maximum vertex of graph
		//       Obs: Try it to use compareTo
		
		Object[] vertexArray = vertex.toArray();
		int[] values = new int[vertexArray.length];
		
		for (int i = 0; i < vertexArray.length; i++)
			values[i] = ((Vertex<T>) vertexArray[i]).getIndex().intValue();
		
		int max = values[0];
		
		for (int v : values) {
			if (v > max)
				max = v;
		}
		
		return max;
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
