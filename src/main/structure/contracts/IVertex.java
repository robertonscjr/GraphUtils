package main.structure.contracts;

import main.structure.components.Edge;

public interface IVertex<T> {
	void addEdge(Edge<T> edge);
	void removeEdge(Edge<T> edge);
}
