package data.exceptions;

public class MoneyTooSmallException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2621042921847451095L;

	public MoneyTooSmallException() {
		super("Money to small for the action.");
	}
}
