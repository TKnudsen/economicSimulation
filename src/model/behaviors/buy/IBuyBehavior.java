package model.behaviors.buy;

import data.exceptions.CapacityOverflowException;
import data.exceptions.CapacityUnderflowException;

public interface IBuyBehavior {

	public void execute() throws CapacityUnderflowException, CapacityOverflowException;
}
