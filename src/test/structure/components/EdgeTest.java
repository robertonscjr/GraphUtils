package test.structure.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.structure.components.Edge;
import main.structure.components.Vertex;

public class EdgeTest {

	Edge<Integer> edge1;
	Edge<Integer> edge2;
	Vertex<Integer> v1;
	Vertex<Integer> v2;
	Vertex<Integer> v3;
	Vertex<Integer> v4;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		v1 = new Vertex<>((long)1);
		v2 = new Vertex<>((long)2);
		v3 = new Vertex<>((long)3);
		v4 = new Vertex<>((long)4);
		
		edge1 = new Edge<>(v1, v2);
		edge1.setWeight((float) 5.5);
		edge2 = new Edge<>(v2,  v3);
		edge2.setWeight((float) 6.0);
		
		v1.addEdge(edge1);
		v2.addEdge(edge2);
	}

	@Test
	public void testEdgeFloatVertexOfTVertexOfT() {
		assertEquals(new Float(5.5), v1.getAdjacencyList().get(0).getWeight());
		assertEquals(new Float(6.0), v2.getAdjacencyList().get(0).getWeight());
	}

	@Test
	public void testToString() {
		assertEquals("1-->2(5.5)", edge1.toString());
		assertEquals("2-->3(6.0)", edge2.toString());
	}

}
