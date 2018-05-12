package main;


public class Main {
	
	public static void main(String[] args) throws Exception {
		Graph<Integer> g = new Graph<>();
		
		//g.readGraph("../GraphUtils/src/main/simpleGraph.txt");
		g.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
		
		System.out.println(g);
		System.out.println(g.getVertex((long) 1));
	}

}
