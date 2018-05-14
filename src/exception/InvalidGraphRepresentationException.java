package exception;

public class InvalidGraphRepresentationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public InvalidGraphRepresentationException() {
		super("Invalid graph representation. The representation type should be ADJACENCYMATRIX or ADJACENCYLIST");
	}
}
