package dataStructures.exceptions;

public class CapacityOverflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6936787454045851490L;

	public CapacityOverflowException(double overflowValue, double maxCapacity) {
		super("Capacity of " + maxCapacity + " exceeded by (" + overflowValue + ").");
	}

	public CapacityOverflowException(String message) {
		super(message);
	}
}
