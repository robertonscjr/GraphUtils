package test.graph;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.Graph;
import main.structure.components.Edge;
import main.structure.components.Vertex;
import main.structure.components.enums.RepresentationType;

public class GraphTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	Graph<Integer> graph;
	Graph<Integer> weightedGraph;
	@Before
	public void setUp() throws Exception {
		this.graph = new Graph<>();
		this.weightedGraph = new Graph<>();
	}

	@Test
	public void testReadGraph() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph.txt");
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph2.txt");
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph3.txt");
			this.graph.readGraph("../GraphUtils/src/main/wronggraphfile.txt"); // raise exception
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Invalid formatting in input file:\n"
				+ "The formatting should be:\n"
				+ "<graph size number>\n"
				+ "<source> <destination>");
		}
	}

	@Test
	public void testReadWeightedGraph() {
		try {
			this.weightedGraph.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
			this.weightedGraph.readWeightedGraph("../GraphUtils/src/main/simpleGraph.txt"); // raise exception
			fail();
		}catch (Exception e) {
			assertEquals(e.getMessage(), "Invalid formatting in input file:\n"
					+ "The formatting should be:\n"
					+ "<graph size number>\n"
					+ "<source> <destination> <weight>");
		}
	}

	@Test
	public void testAddWeightedEdge() {
		try {
			this.weightedGraph.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
			Vertex<Integer> v = this.weightedGraph.getVertex((long) 3);
			List<Edge<Integer>> edges = v.getAdjacencyList();
			assertEquals(edges.size(), 2);
			this.weightedGraph.addWeightedEdge((float) 3,(long) 3, (long) 2);
			assertEquals( edges.size(), 3);
			
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAddEdge() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph.txt");
			Vertex<Integer> v = this.graph.getVertex((long) 3);
			List<Edge<Integer>> edges = v.getAdjacencyList();
			assertEquals(edges.size(), 1);
			this.graph.addEdge((long) 3, (long) 2);
			assertEquals( edges.size(), 2);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAddVertex() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph.txt");
			this.weightedGraph.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
			
			assertEquals(5, this.graph.getVertexNumber());
			this.graph.addVertex((long) 10);
			assertEquals(6,this.graph.getVertexNumber());
			
			assertEquals(5, this.weightedGraph.getVertexNumber());
			this.weightedGraph.addVertex((long) 10);
			assertEquals(6,this.weightedGraph.getVertexNumber());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetVertex() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph.txt");
			this.weightedGraph.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
			assertEquals(this.graph.getVertex((long) 10), null );
			assertNotEquals(this.graph.getVertex((long) 3),null );
			assertEquals( this.graph.getVertex((long) 3).getIndex(), new Long(3));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetVertexNumber() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph.txt");
			assertEquals(this.graph.getVertexNumber(), 5);
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph2.txt");
			assertEquals(this.graph.getVertexNumber(), 12);
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph3.txt");			
			assertEquals(this.graph.getVertexNumber(), 14);
			
			this.weightedGraph.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
			assertEquals(5, this.weightedGraph.getVertexNumber());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetEdgeNumber() {
		try {
			this.weightedGraph.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
			assertEquals(6,this.weightedGraph.getEdgeNumber());
			
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph.txt");
			assertEquals(5, this.graph.getEdgeNumber());
		} catch (Exception e) {
			fail();
		}
		
	}

	@Test
	public void testBFS() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph.txt");
			String[] result = this.graph.BFS(this.graph.getVertex((long) 1)).split("\n");
			
			assertEquals(true, result[0].equals("1 - - 0"));
			assertEquals(true, result[1].equals("2 - 1 1"));
			assertEquals(true, result[2].equals("5 - 1 1"));
			assertEquals(true, result[3].equals("3 - 2 5"));
			assertEquals(true, result[4].equals("4 - 2 5"));
			
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testDFS() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph2.txt");
			String[] result = this.graph.DFS(this.graph.getVertex((long) 40)).split("\n");
			
			assertEquals(true, result[0].equals("40 - - 0"));
			assertEquals(true, result[1].equals("20 - 40 1"));
			assertEquals(true, result[2].equals("50 - 20 3"));
			assertEquals(true, result[3].equals("70 - 50 3"));
			assertEquals(true, result[4].equals("60 - 20 2"));
			assertEquals(true, result[5].equals("30 - 20 1"));
			assertEquals(true, result[6].equals("10 - 40 0"));
		} catch (Exception e) {
			fail();
		}
		
	}

	@Test
	public void testSCC() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph2.txt");
			assertEquals(true, this.graph.SCC());
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph3.txt");
			assertEquals(false, this.graph.SCC());
		} catch (Exception e) {
			fail();
		}
		
	}

	@Test
	public void testShortestPath() {
		try {
			this.weightedGraph.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
			Vertex<Integer> v1 =  this.weightedGraph.getVertex((long) 1);
			Vertex<Integer> v2 = this.weightedGraph.getVertex((long) 3);
			String[] result = this.weightedGraph.shortestPath(v1, v2).split("\n");
			assertEquals(true,result[0].equals("1 2 5 3 "));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGraphRepresentation() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph.txt");
			this.weightedGraph.readWeightedGraph("../GraphUtils/src/main/weightedGraph.txt");
			
			String[] result = this.graph.graphRepresentation(RepresentationType.ADJACENCYLIST).split("\n");
			
			assertEquals(true, result[0].equals("3 - 5"));
			assertEquals(true, result[1].equals("1 - 2 5"));
			assertEquals(true, result[2].equals("5 - 2 3 4 1"));
			assertEquals(true, result[3].equals("2 - 1 5"));
			assertEquals(true, result[4].equals("4 - 5"));
			
			result = this.weightedGraph.graphRepresentation(RepresentationType.ADJACENCYLIST).split("\n");
			
			assertEquals(true, result[0].equals("3 - 5(5.0) 4(-9.5)"));
			assertEquals(true, result[1].equals("4 - 3(-9.5) 5(2.3)"));
			assertEquals(true, result[2].equals("5 - 2(0.2) 3(5.0) 4(2.3) 1(1.0)"));
			assertEquals(true, result[3].equals("1 - 2(0.1) 5(1.0)"));
			assertEquals(true, result[4].equals("2 - 1(0.1) 5(0.2)"));
			
			
			result = this.weightedGraph.graphRepresentation(RepresentationType.ADJACENCYMATRIX).split("\n");
			
			assertEquals(true, result[0].equals("0.0 0.0 0.0 0.0 0.0 0.0"));
			assertEquals(true, result[1].equals("0.0 1.0 0.0 0.0 0.0 0.0"));
			assertEquals(true, result[2].equals("0.0 0.0 0.2 0.0 0.0 0.0"));
			assertEquals(true, result[3].equals("0.0 0.0 0.0 -9.5 0.0 0.0"));
			assertEquals(true, result[4].equals("0.0 0.0 0.0 0.0 2.3 0.0"));
			assertEquals(true, result[5].equals("0.0 0.0 0.0 0.0 0.0 1.0"));
			
			result = this.graph.graphRepresentation(RepresentationType.ADJACENCYMATRIX).split("\n");
			
			assertEquals(true, result[0].equals("0.0 0.0 0.0 0.0 0.0 0.0"));
			assertEquals(true, result[1].equals("0.0 1.0 0.0 0.0 0.0 0.0"));
			assertEquals(true, result[2].equals("0.0 0.0 1.0 0.0 0.0 0.0"));
			assertEquals(true, result[3].equals("0.0 0.0 0.0 1.0 0.0 0.0"));
			assertEquals(true, result[4].equals("0.0 0.0 0.0 0.0 1.0 0.0"));
			assertEquals(true, result[5].equals("0.0 0.0 0.0 0.0 0.0 1.0"));
			
			
			
		} catch (Exception e) {
			fail();
		}
		
	}

	@Test
	public void testToString() {
		try {
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph.txt");
			String[] result = this.graph.toString().split("\n");
			
			assertEquals(true, result[0].equals("4 - [4-->5]"));
			assertEquals(true, result[1].equals("1 - [1-->2, 1-->5]"));
			assertEquals(true, result[2].equals("2 - [2-->1, 2-->5]"));
			assertEquals(true, result[3].equals("5 - [5-->2, 5-->3, 5-->4, 5-->1]"));
			assertEquals(true, result[4].equals("3 - [3-->5]"));
			
			this.graph.readGraph("../GraphUtils/src/main/simpleGraph2.txt");
			result = this.graph.toString().split("\n");
			
			assertEquals(true, result[0].equals("4 - [4-->5]"));
			assertEquals(true, result[1].equals("40 - [40-->10, 40-->20]"));
			assertEquals(true, result[2].equals("10 - [10-->40, 10-->30, 10-->20]"));
			assertEquals(true, result[3].equals("60 - [60-->20, 60-->30, 60-->70]"));
			assertEquals(true, result[4].equals("50 - [50-->20, 50-->70]"));
			assertEquals(true, result[5].equals("1 - [1-->2, 1-->5]"));
			assertEquals(true, result[6].equals("2 - [2-->1, 2-->5]"));
			assertEquals(true, result[7].equals("20 - [20-->40, 20-->10, 20-->30, 20-->60, 20-->50]"));
			assertEquals(true, result[8].equals("70 - [70-->60, 70-->50]"));
			assertEquals(true, result[9].equals("5 - [5-->2, 5-->3, 5-->4, 5-->1]"));
			assertEquals(true, result[10].equals("3 - [3-->5]"));
			assertEquals(true, result[11].equals("30 - [30-->10, 30-->20, 30-->60]"));
			
			//this.graph.readGraph("../GraphUtils/src/main/wronggraphfile.txt"); // raise exception
			
		}catch (Exception e) {
			fail();
		}
	}
}
