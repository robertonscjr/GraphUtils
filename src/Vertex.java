import java.util.LinkedList;

public class Vertex<T> {
	private T value;
	private int status;
	private double distance;
	private T previous;
	
	private LinkedList<Edge> adjlist;
	
	public Vertex(T value) {
		this.value = value;
		this.adjlist = new LinkedList<>();
		this.distance = Float.MAX_VALUE / 2;
	}
	
	public Vertex(T value, LinkedList<Edge> adjList) {
		this.value = value;
		this.adjlist = adjList;
		this.distance = Float.MAX_VALUE / 2;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public int getStatus() {
		return this.status;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public void relaxDistance( double value , T previousVertex ) {
		if( this.distance > value) {
			this.distance = value;
			this.previous = previousVertex;
		}
	}
	
	public T getPredecessor() {
		return this.previous;
	}
	
	public void setStatus(int newStatus) {
		this.status = newStatus;
	}
	
	public void setAdjList(LinkedList<Edge> newAdjList) {
		this.adjlist = newAdjList;
	}
	
	public LinkedList<Edge> getAdjList(){
		return this.adjlist;
	}
	
	public Boolean isConnectectedWith(T value) {
		return this.adjlist.contains( new Edge(value) );
	}
	
	@Override
	public String toString() {
		String response = this.value.toString() + " - ";
		for(int i = 0 ; i < this.adjlist.size();i++) {
			response += this.adjlist.get(i).toString() + " ";
		}
		response += "\n";
		return response;
	}
}