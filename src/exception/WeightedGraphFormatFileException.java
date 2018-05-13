package exception;

public class WeightedGraphFormatFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public WeightedGraphFormatFileException() {
		super("Invalid formatting in input file:\n"
				+ "The formatting should be:\n"
				+ "<graph size number>\n"
				+ "<source> <destination> <weight>");
	}
}
