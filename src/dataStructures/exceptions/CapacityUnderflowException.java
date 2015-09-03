package dataStructures.exceptions;

public class CapacityUnderflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6936787454045851490L;

	public CapacityUnderflowException(double underflowValue) {
		super("Capacity of lower than zero (" + underflowValue + ").");
	}

	public CapacityUnderflowException(String message) {
		super(message);
	}
}
