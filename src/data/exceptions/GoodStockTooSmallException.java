package data.exceptions;

public class GoodStockTooSmallException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8928894308419021278L;

	public GoodStockTooSmallException(String source) {
		super(source+": GoodStock to small for the action.");
	}
}
