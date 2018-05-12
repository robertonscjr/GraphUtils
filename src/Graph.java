import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph<T> {
	private HashMap<T,Vertex> graph;

	public Graph() {
		this.graph = new HashMap<>();
	}
	
	public void addVertex(T value, LinkedList<Edge> adjList) {
		this.graph.put( value, new Vertex(value,adjList) );
	}
	
	public int getVertexNumber() {
		return this.graph.size();
	}
	
	public int getEdgeNumber() {
		//Correção : o metódo anterior voltava o size de todos os vertices adjacentes (pode ter o risco de ser contado duas vezes, uma para cada nó entre as arestas)
		return 0;
	}
	
	public float getMeanEdge() {
		return 2 * getEdgeNumber() / getVertexNumber();
	}
	
	
	public String BFS( T value ) {
		String response = "";
		if( this.graph.containsKey(value) ) {
			int level = 0;
			response += value.toString() + "-" + level + " - \n";
			Queue<Vertex<T>> vertexQueue = new ArrayDeque<>();
			this.graph.get(value).setStatus(1);
			vertexQueue.add(this.graph.get(value));
			level += 1;
			while (!vertexQueue.isEmpty()) {
				Vertex<T> currentVertex = vertexQueue.remove();
				LinkedList<Edge> adjElements = currentVertex.getAdjList();
				Boolean added = false;
				for(Edge e : adjElements) {
					if( this.graph.get(e.getValue()).getStatus() == 0 ) {
						vertexQueue.add(this.graph.get(e.getValue()));
						this.graph.get(e.getValue()).setStatus(1);
						response += e.getValue() + "-" + level + " " +  currentVertex.getValue() + "\n";
						added = true;
					}
				}
				if(added == true)
					level += 1;
			}
		}
		return response;
	}
	
	
	public String DFS(T value) {
		String response = "";
		if(this.graph.containsKey(value)) {
			this.graph.get(value).setStatus(1);
			response = this.recursiveDFS(this.graph.get(value),0, null, response);
		}
		return response;
	}
	
	
	private String recursiveDFS(Vertex<T> vertex, int level, Vertex<T> father, String string) {
		if(father == null)
			string += vertex.getValue() + " " + level + " " + "- \n";
		else {
			string += vertex.getValue() + " " + level + " " + father.getValue() + "\n";
		}
		Stack<Vertex<T>> vertexStack = new Stack<>();
		for(Edge e: vertex.getAdjList()) {
			if(this.graph.get(e.getValue()).getStatus() == 0) {
				vertexStack.push(this.graph.get(e.getValue()));
			}
		}
		while(!vertexStack.isEmpty()) {
			Vertex<T> adjVertex = vertexStack.pop();
			this.graph.get(adjVertex.getValue()).setStatus(1);
			string = this.recursiveDFS(adjVertex, level + 1, vertex, string);
		}
		return string;	
	}
	
	
	public String SCC() {
		String response = "";
		LinkedList<Component> components = new LinkedList<>();
		T start_value = getUnprocessedVertex();
		while( start_value != null ) {
			components.add( new Component( BFS(start_value) ) );
			start_value = getUnprocessedVertex();
		}
		Collections.sort(components);
		for(int i = 0 ; i < components.size();i++) {
			response += components.get(i);
		}
		return response;
	}
	
	public String shortestPath(T value1, T value2) {
		String response = "";
		if( this.graph.containsKey(value1) && this.graph.containsKey(value2) ) {
			this.graph.get(value1).relaxDistance( 0, null );
			Queue<Vertex<T>> vertexQueue = new ArrayDeque<>();
			vertexQueue.add( this.graph.get(value1) );
			while( !vertexQueue.isEmpty() ) {
				Vertex<T> currentVertex = vertexQueue.remove();
				this.graph.get( currentVertex.getValue() ).setStatus(1);
				LinkedList<Edge> adjElements = currentVertex.getAdjList();
				for(Edge e : adjElements) {
					if( this.graph.get(e.getValue()).getStatus() == 0 ) {
						vertexQueue.add( this.graph.get(e.getValue()) );
						double expected_distance = currentVertex.getDistance();
						expected_distance += e.getWeight();
						this.graph.get( e.getValue()).relaxDistance( expected_distance, currentVertex.getValue() );
					}
				}
			}
			if(this.graph.get(value2).getDistance() != Float.MAX_VALUE / 2) {
				response += this.graph.get(value2).getValue().toString() + "\n";
				T previousNode = (T) this.graph.get(value2).getPredecessor();
				while( previousNode != null) {
					response = previousNode + " " + response;
					previousNode = (T) this.graph.get(previousNode).getPredecessor();
				}
			}else {
				response = "No path to this vertex";
			}
		}else {
			response = "No path to this vertex";
		}
		return response;
	}
	
	private T getUnprocessedVertex() {
		Set<T> keys = (Set<T>) this.graph.keySet();
		for(T key : keys) {
			if(this.graph.get(key).getStatus() == 0)
				return (T) this.graph.get(key).getValue();
		}
		return null;
	}
	
	
	public String graphRepresentation(String type) {
		String result = "";
		if(type == "AL") {
			Set<T> keys = (Set<T>) this.graph.keySet();
			for(T key : keys) {
				result += this.graph.get(key).toString();
			}
		}
		return result;
	}
}