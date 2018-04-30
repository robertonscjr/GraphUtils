package main.structure.contracts;

import main.structure.components.Edge;

public interface IVertex {
	void addEdge(Edge edge);
	void removeEdge(Edge edge);
}
