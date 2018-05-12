import java.util.LinkedList;

public class Main {

	public static LinkedList<Edge> fillEdges(int[][] array){
		LinkedList<Edge> edges = new LinkedList<>();
		for(int i = 0 ; i < array.length;i++) {
			edges.add(new Edge(array[i][0],array[i][1]));
		}
		return edges;
	}
	
	public static void main(String[] args) {
		int[][] array = {{2,3},{5,1}};
		LinkedList<Edge> edges = fillEdges(array);
		Graph<Integer> graph = new Graph();
		graph.addVertex(1, edges);
		
		int[][] newArray = {{1,3},{5,9},{3,10}};
		edges = fillEdges(newArray);
		graph.addVertex(2, edges);
		
		int[][] newArray1 = {{5,10},{2,10}};
		edges = fillEdges(newArray1);
		graph.addVertex(3, edges);
		
		int[][] newArray2 = {{5,5}};
		edges = fillEdges(newArray2);
		graph.addVertex(4, edges);
		
		int[][] newArray3 = {{1,1},{2,9},{3,7},{4,5}};
		edges = fillEdges(newArray3);
		graph.addVertex(5, edges);

/**
		int[][] newArray4 = {{7,1},{6,9}};
		edges = fillEdges(newArray4);
		graph.addVertex(8, edges);

		int[][] newArray5 = {{6,5},{8,1}};
		edges = fillEdges(newArray5);
		graph.addVertex(7, edges);

		int[][] newArray6 = {{8,9},{6,5}};
		edges = fillEdges(newArray6);
		graph.addVertex(6, edges);

		int[][] newArray7 = {{10,1}};
		edges = fillEdges(newArray7);
		graph.addVertex(11, edges);

		int[][] newArray8 = {{11,1}};
		edges = fillEdges(newArray8);
		graph.addVertex(10, edges);
*/
		//System.out.println(graph.graphRepresentation("AL"));
		//System.out.println(graph.DFS(3));
		System.out.println(graph.shortestPath(1, 3));
	}

}
