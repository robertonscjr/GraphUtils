package test.structure.components;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import main.structure.components.Edge;
import main.structure.components.Vertex;
import test.categories.Construction;

public class VertexTest {

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
	}

	@Test
	public void testAddEdge() {
		v1.addEdge(new Edge<>(v1,  v2));
		v1.addEdge(new Edge<>(v2,  v3));
		v1.addEdge(new Edge<>(v3,  v4));
		assertEquals("[1-->2, 2-->3, 3-->4]", v1.getAdjacencyList().toArray());
		
	}

	@Test
	public void testRemoveEdge() {
		Edge<Integer> edge1 = new Edge<>(v1, v2);
		v1.addEdge(edge1);
		v1.removeEdge(edge1);
		assertEquals("[]", v1.getAdjacencyList().toString());
	}

	@Test
	public void testGetAdjacencyListRepresentation() {
		v1.addEdge(new Edge<>(v1,  v2));
		v1.addEdge(new Edge<>(v2,  v3));
		v1.addEdge(new Edge<>(v3,  v4));
		v1.getAdjacencyList().toString();
		assertEquals(" 2 3 4",  v1.getAdjacencyListRepresentation());
	}

	@Test
	public void testToString() {
		assertEquals("1", v1.toString());
		assertEquals("2", v2.toString());
		assertEquals("3", v3.toString());
		assertEquals("4", v4.toString());
	}

}
