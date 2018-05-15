package main;

import main.structure.components.enums.RepresentationType;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Graph<Integer> g = new Graph<>();
		
		g.readGraph("../GraphUtils/src/main/simpleGraph.txt");
		//g.readGraph("../GraphUtils/src/main/simpleGraph3.txt");
		//g.readGraph("../GraphUtils/src/main/simpleGraph2.txt");
		//g.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
		
		//System.out.println(g.DFS(g.getVertex((long) 1)));
		System.out.println(g.BFS(g.getVertex((long) 1)));
		//System.out.println(g.graphRepresentation(RepresentationType.ADJACENCYLIST));
		//System.out.println(g.graphRepresentation(RepresentationType.ADJACENCYMATRIX));
	}

}
