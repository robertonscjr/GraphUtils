package main;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Graph<Integer> g = new Graph<>();
		
		g.readGraph("/home/nicacio/Dropbox/UFCG/APLIC. GRAFOS/workspace/GraphUtils/src/main/simpleGraph.txt");
		System.out.println("OK");
		
	}

}
