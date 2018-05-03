package main;

import main.Graph.RepresentationType;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Graph<Integer> g = new Graph<>();
		
		g.readGraph("/home/josernj/git/GraphUtils/src/main/simpleGraph.txt");
//		g.readWeightedGraph("/home/josernj/git/GraphUtils/src/main/weightedGraph.txt");
		
		System.out.println(g.graphRepresentation(RepresentationType.ADJACENCYMATRIX));
	}

}
