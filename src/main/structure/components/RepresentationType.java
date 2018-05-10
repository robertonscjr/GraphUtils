package main.structure.components;

public enum RepresentationType {
	ADJACENCYMATRIX("AM"),ADJACENCYLIST("AL");

    public String representation;
    
    RepresentationType(String representation) {
        this.representation = representation;
    }
}
